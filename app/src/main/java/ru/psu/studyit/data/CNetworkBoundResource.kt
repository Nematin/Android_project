package ru.psu.studyit.data

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/********************************************************************************************************
 * Класс содержит алгоритмы работы со списками, которые хранятся одновременно и на сервере и в          *
 * локальной БД.                                                                                        *
 * @author Плюснин А., Епанов А., Золотарёв М. 2019 0411.                                               *
 *******************************************************************************************************/
abstract class CNetworkBoundResource<ResultType, RequestType>
{
    val flowable                            : Flowable<CResource<ResultType>>
        @MainThread
        get()
        {
            val source                      : Flowable<CResource<ResultType>>
            if (shouldFetch())
            {

                source                      = createCall()
                    .subscribeOn(Schedulers.io())
                    .doOnNext {
                        saveCallResult(processResponse(it)!!)
                    }

                    .flatMap {
                        loadFromDB()
                            .map {
                                CResource.success(it)
                            }
                    }

                    .doOnError {
                        onFetchFailed()
                    }

                    .onErrorResumeNext { t  : Throwable
                                            ->
                        loadFromDB()
                            .map {
                                CResource.error(t.message!!, it)
                            }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
            }
            else
            {
                source                      = loadFromDB()
                    .map { CResource.success(it) }
            }

            return Flowable.concat(
                loadFromDB()
                    .map { CResource.loading(it) }
                    .take(1),
                source
            )
        }
    @MainThread
    private fun onFetchFailed() {}

    @WorkerThread
    protected fun processResponse(
        response                            : CResource<RequestType>
    )                                       : RequestType?
    {
        return response.data
    }

    @WorkerThread
    protected abstract fun saveCallResult(
        item                                : RequestType
    )

    @MainThread
    protected abstract fun shouldFetch()    : Boolean

    @MainThread
    protected abstract fun loadFromDB()     : Flowable<ResultType>

    @MainThread
    protected abstract fun createCall()     : Flowable<CResource<RequestType>>
}