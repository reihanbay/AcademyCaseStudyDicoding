package com.dicoding.jetpack.academy.data.source

import com.dicoding.jetpack.academy.data.CourseEntity
import com.dicoding.jetpack.academy.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses() : List<CourseEntity>

    fun getBookmarkedCourses() : List<CourseEntity>
    fun getCourseWithModules(courseID: String): CourseEntity
    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>
    fun getContent(courseId: String, moduleId:String): ModuleEntity
}