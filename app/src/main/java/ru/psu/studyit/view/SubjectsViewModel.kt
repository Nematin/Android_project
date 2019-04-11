package ru.psu.studyit.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.psu.studyit.data.Resource
import ru.psu.studyit.data.dao.SubjectDao
import ru.psu.studyit.data.entity.SubjectEntity
import ru.psu.studyit.data.services.SubjectsRepository
import ru.psu.studyit.utils.api.IServiceServerAPI
import javax.inject.Inject

class SubjectsViewModel @Inject constructor(
        subjectDao: SubjectDao,
        apiService: IServiceServerAPI) : ViewModel() {

    private val subjectsRepository: SubjectsRepository = SubjectsRepository(subjectDao, apiService)

    private val subjectListLiveData = MutableLiveData<Resource<List<SubjectEntity>>>()

    fun loadSubjects() {
        subjectsRepository.loadSubjects()
                .subscribe { resource -> getSubjectsLiveData().postValue(resource) }
    }

    fun getSubjectsLiveData() = subjectListLiveData
}