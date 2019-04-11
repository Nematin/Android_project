package ru.psu.studyit.data.services

import io.reactivex.Flowable
import ru.psu.studyit.data.dao.SubjectDao
import ru.psu.studyit.utils.api.IServiceServerAPI
import javax.inject.Singleton
import io.reactivex.Observable;
import ru.psu.studyit.data.entity.SubjectEntity
import ru.psu.studyit.utils.api.SubjectApiResponse
import ru.psu.studyit.data.NetworkBoundResource;
import ru.psu.studyit.data.Resource;

@Singleton
class SubjectsRepository(
        private val subjectDao: SubjectDao,
        private val apiService: IServiceServerAPI
) {

    fun loadSubjects(): Observable<Resource<List<SubjectEntity>>> {
        return object : NetworkBoundResource<List<SubjectEntity>, SubjectApiResponse>() {

            override fun saveCallResult(item: SubjectApiResponse) {
                subjectDao.insertDisciplines(item.results)
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override fun loadFromDb(): Flowable<List<SubjectEntity>> {
                val subjects = subjectDao.getDisciplines()
                return if (subjects == null || subjects.isEmpty()) {
                    Flowable.empty()
                } else Flowable.just(subjects)
            }

            override fun createCall(): Observable<Resource<SubjectApiResponse>> {
                return apiService.fetchSubjects()
                        .flatMap { apiResponse ->
                            Observable.just(
                                    if (apiResponse == null) Resource.error("", SubjectApiResponse(1, emptyList(), 0, 1))
                                    else Resource.success(apiResponse)
                            )
                        }
            }
        }.getAsObservable()
    }
}