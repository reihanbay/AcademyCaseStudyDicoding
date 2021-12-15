package com.dicoding.jetpack.academy.data.source

import androidx.lifecycle.LiveData
import com.dicoding.jetpack.academy.data.CourseEntity
import com.dicoding.jetpack.academy.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses() : LiveData<List<CourseEntity>>

    fun getBookmarkedCourses() : LiveData<List<CourseEntity>>
    fun getCourseWithModules(courseID: String): LiveData<CourseEntity>
    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>>
    fun getContent(courseId: String, moduleId:String): LiveData<ModuleEntity>
}