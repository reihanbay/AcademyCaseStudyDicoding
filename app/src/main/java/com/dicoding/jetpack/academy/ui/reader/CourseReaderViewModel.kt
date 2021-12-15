package com.dicoding.jetpack.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.academy.data.ContentEntity
import com.dicoding.jetpack.academy.data.ModuleEntity
import com.dicoding.jetpack.academy.data.source.AcademyRepository
import com.dicoding.jetpack.academy.utils.DataDummy

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setsSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
    fun getSelectedModule(): LiveData<ModuleEntity> = academyRepository.getContent(courseId, moduleId)
}