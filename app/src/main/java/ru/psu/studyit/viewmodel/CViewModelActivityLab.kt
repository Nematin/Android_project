package ru.psu.studyit.viewmodel


import androidx.lifecycle.MutableLiveData
import me.tatarka.bindingcollectionadapter2.*
import ru.psu.studyit.BR
import ru.psu.studyit.R
import ru.psu.studyit.data.repositories.IRepositoryLab
import ru.psu.studyit.data.repositories.IRepositorySubject
import ru.psu.studyit.model.CSubject
import javax.inject.Inject

/********************************************************************************************************
 * Модель представления ждя активности лабораторной работы.                                             *
 * @author Селетков И.П. 2019 0413.                                                                     *
 *******************************************************************************************************/
class CViewModelActivityLab
/********************************************************************************************************
 * Конструктор.                                                                                         *
 *******************************************************************************************************/
@Inject
constructor
(
    private val repositorySubject           : IRepositorySubject,
    private val repositoryLab               : IRepositoryLab
)                                           : CViewModelBase()
{
    val subjects                            = MutableLiveData<List<CSubject>>()
    val singleSubjectBinding                = ItemBinding.of<CSubject>(BR.subject, R.layout.spinner_subject_selected_item)
    val subjectIds                          =
        BindingListViewAdapter.ItemIds<Any> { position, _ -> position.toLong() }
    val selectedSubjectPosition             = MutableLiveData<Int>()

    init
    {

        //При создании модели представления инициируем запрос в репозиторий дисциплин,
        //который ретранслируется на сервер и в БД.
        registerDisposable(
            repositorySubject
                .getResource()
                //Обработка результатов запроса - рассылаем всем элементам интерфейса
                //информацию об обновлении списка дисциплин.
                .subscribe {
                    resource ->
                    subjects.postValue(resource.data)

                    //if (resource.data?.isNotEmpty()==true)
                    //    selectedSubjectPosition.postValue(0)
                }
        )
    }
    fun onSubjectChange()
    {
        return
    }
}