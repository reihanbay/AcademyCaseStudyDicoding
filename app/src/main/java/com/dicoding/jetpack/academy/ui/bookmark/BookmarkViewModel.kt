package com.dicoding.jetpack.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.academy.data.CourseEntity
import com.dicoding.jetpack.academy.data.source.AcademyRepository
import com.dicoding.jetpack.academy.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks() : LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}