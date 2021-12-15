package com.dicoding.jetpack.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.academy.data.CourseEntity
import com.dicoding.jetpack.academy.data.source.AcademyRepository
import com.dicoding.jetpack.academy.utils.DataDummy

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses() : LiveData<List<CourseEntity>> = academyRepository.getAllCourses()
}