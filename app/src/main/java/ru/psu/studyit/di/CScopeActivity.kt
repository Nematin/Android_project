package ru.psu.studyit.di

import javax.inject.Scope

/********************************************************************************************************
 * Период жизни объектов "Пока работает активность".                                                    *
 * @author Селетков И.П. 2018 0830.                                                                     *
 *******************************************************************************************************/
@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CScopeActivity