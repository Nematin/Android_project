package ru.psu.studyit.view.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.psu.studyit.utils.IDisposable

/********************************************************************************************************
 * Базовый класс для всех фрагментов, содержит реализацию общих технических методов.                    *
 * @author Селетков И.П. 2019 0423.                                                                     *
 *******************************************************************************************************/
abstract class CFragmentBase                :
    DaggerFragment(),
    IDisposable
{
    private var compositeDisposable         = CompositeDisposable()

    /****************************************************************************************************
     * При остановке активности отменяет все реактивные подписки, которые были зарегистрированы         *
     * в ходе выполнения этой активности.                                                               *
     ***************************************************************************************************/
    override fun onPause()
    {
        super.onPause()
        dispose()
        return
    }
    /****************************************************************************************************
     * Отписка от всех реактивных потоков.                                                              *
     ***************************************************************************************************/
    override fun dispose()
    {
        compositeDisposable.clear()
    }
    /****************************************************************************************************
     * Регистрация подписки на реактивный поток.                                                        *
     * @param disposable - подписка, от которой необходимо отписаться в конце.                          *
     ***************************************************************************************************/
    override fun registerDisposable(
        disposable                          : Disposable
    )
    {
        compositeDisposable.add(disposable)
    }

    /****************************************************************************************************
     * Возвращает модель представления типа [T] и выполняет от неё кусок кода [body].                   *
     * @param factory - фабрика, позволяющая создавать данный тип модели представления.                 *
     * @param body - кусок кода, который будет выполнен от имени модели представления.                  *
     * @return модель представления или null, если фрагмент не привязан к активности.                   *
     ***************************************************************************************************/
    inline fun <reified T                   : ViewModel>
        viewModel (
        factory                             : ViewModelProvider.Factory,
        body                                : T.() -> Unit
    )                                       : T?
    {
        activity?.run {
            val vm                          = ViewModelProviders.of(this, factory)[T::class.java]
            vm.body()
            return vm
        }
        return null

    }
}