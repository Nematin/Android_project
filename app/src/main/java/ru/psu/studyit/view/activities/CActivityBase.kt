package ru.psu.studyit.view.activities

import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.psu.studyit.utils.IDisposable

/********************************************************************************************************
 * Базовый класс для всех активностей, содержит реализацию общих технических методов.                   *
 * @author Балышев А.М. 2019 0323.                                                                  *
 *******************************************************************************************************/
abstract class CActivityBase                :
    DaggerAppCompatActivity(),
    IDisposable
{
    private var compositeDisposable         = CompositeDisposable()

    /****************************************************************************************************
     * Отображает сообщение.                                                                            *
     ***************************************************************************************************/
    fun showMessage(message                 : String)
    {
        val toast                           = Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
        return
    }

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
        disposable                          : Disposable)
    {
        compositeDisposable.add(disposable)
    }

    /****************************************************************************************************
     * Возвращает модель представления типа T.                                                          *
     * @param factory - фабрика, позволяющая создавать данный тип модели представления.                 *
     * @param body - кусок кода, который будет выполнен от имени модели представления.                  *
     ***************************************************************************************************/
    inline fun <reified T                   : ViewModel>
        viewModel (
        factory                             : ViewModelProvider.Factory,
        body                                : T.() -> Unit
    )                                       : T
    {
        val vm                              = ViewModelProviders.of(this, factory)[T::class.java]
        vm.body()
        return vm
    }
}
