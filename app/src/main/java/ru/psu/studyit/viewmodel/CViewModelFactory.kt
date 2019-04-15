package ru.psu.studyit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/********************************************************************************************************
 * Общая для всех моделей представления фабрика новых объектов в зависимости от типа.                   *
 * @author Селетков И.П. 2018 0830.                                                                     *
 * https://habr.com/post/337320/                                                                        *
 * https://github.com/android10/Android-CleanArchitecture-Kotlin/blob/master/app/src/main/kotlin/       *
 *  com/fernandocejas/sample/core/di/viewmodel/ViewModelFactory.kt                                      *
 *******************************************************************************************************/
@Singleton
class CViewModelFactory
/********************************************************************************************************
 * Конструктор.                                                                                         *
 *******************************************************************************************************/
@Inject constructor
(
    private val creators                    : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
)                                           : ViewModelProvider.Factory
{
    /****************************************************************************************************
     * Создаёт объект модели представления типа [T].                                                    *
     * @return модель представления.                                                                    *
     ***************************************************************************************************/
    override fun <T                         : ViewModel>
        create(modelClass                   : Class<T>
    )                                       : T
    {
        //Выбираем провайдер нужного типа модели представления из общей карты.
        //По точному совпадению типа.
        val creator                         = creators[modelClass] ?:
            //По наследованию.
            creators
                .asIterable()
                .firstOrNull { modelClass.isAssignableFrom(it.key) }?.value ?:
                    throw IllegalArgumentException("Unknown ViewModel class $modelClass")

        //Создаём новый объект модели представления.
        return try
        {
            @Suppress("UNCHECKED_CAST")
            creator.get() as T
        }
        catch (e                            : Exception)
        {
            throw RuntimeException(e)
        }
    }
}