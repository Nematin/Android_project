package ru.psu.studyit.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.psu.studyit.utils.IDisposable

/********************************************************************************************************
 * Базовый класс для всех объектов модели представления.                                                *
 * @author Селетков И.П. 2018 0830.                                                                     *
 *******************************************************************************************************/
abstract class CViewModelBase               :
    ViewModel(),
    IDisposable
{
    private val compositeDisposable         = CompositeDisposable()

    override fun onCleared()
    {
        dispose()
        super.onCleared()
    }
    /****************************************************************************************************
     * Отписка от всех реактивных потоков.                                                              *
     ***************************************************************************************************/
    override fun dispose()
    {
        compositeDisposable.dispose()
    }
    /****************************************************************************************************
     * Регистрация подписки на реактивный поток.                                                        *
     * @param disposable - подписка, от которой необходимо отписаться в конце.                          *
     ***************************************************************************************************/
    override fun registerDisposable(
        disposable                          : Disposable)
    {
        compositeDisposable.add(disposable)
    }
}
/********************************************************************************************************
 * Добавляем к стандартному классу LifecycleOwner новый метод observe, позволяющий проще устанавливать  *
 * обозревателей к объектам живых данных.                                                               *
 * @author Селетков И.П. 2018 0830.                                                                     *
 *******************************************************************************************************/
fun <
    T                                       : Any,
    L                                       : LiveData<T>
    > LifecycleOwner.observe(
        liveData                            : L,
        body                                : (T?) -> Unit)
{
    liveData.observe(this, Observer(body))
}