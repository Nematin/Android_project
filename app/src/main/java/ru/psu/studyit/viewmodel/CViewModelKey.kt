package ru.psu.studyit.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/********************************************************************************************************
 * Аннотация позволяет осуществлять мультибайндинг моделей представления в специальном модуле.          *
 * @author Селетков И.П. 2018 0830.                                                                     *
 *******************************************************************************************************/
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
//internal
annotation class CViewModelKey(
    val value                               : KClass<out ViewModel>)