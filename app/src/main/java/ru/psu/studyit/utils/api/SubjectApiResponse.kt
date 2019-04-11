package ru.psu.studyit.utils.api

import ru.psu.studyit.data.entity.SubjectEntity

data class SubjectApiResponse(val page: Long,
                              val results: List<SubjectEntity>,
                              val total_results: Long,
                              val total_pages: Long)