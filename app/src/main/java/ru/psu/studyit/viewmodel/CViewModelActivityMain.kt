package ru.psu.studyit.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.psu.studyit.data.repositories.IRepositoryLab
import ru.psu.studyit.model.CLab

import javax.inject.Inject

/********************************************************************************************************
 * Модель основного представления.                                                                      *
 * @author Селетков И.П. 2018 0925.                                                                     *
 *******************************************************************************************************/
class CViewModelActivityMain
/********************************************************************************************************
 * Конструктор.                                                                                         *
 *******************************************************************************************************/
@Inject
constructor
(
    val repositoryLab                       : IRepositoryLab
)                                           : CViewModelBase()
{
    //Список дисциплин, отображаемых на главной активности.
    val labs                                = MutableLiveData<List<CLab>>()
    //Признак того, что происходит актуализация данных.
    //Влияет на видимость индикатора.
    val loading                             = MutableLiveData<Boolean>()

    //Начальные значения всех полей.
    init
    {
        labs.postValue(ArrayList())
        loading.value                       = false
    }
}