package com.dicoding.jetpack.academy.data.source

import com.dicoding.jetpack.academy.data.ContentEntity
import com.dicoding.jetpack.academy.data.CourseEntity
import com.dicoding.jetpack.academy.data.ModuleEntity
import com.dicoding.jetpack.academy.data.source.remote.RemoteDataSource

class FakeAcademyRepository (private val remoteDataSource: RemoteDataSource): AcademyDataSource {

    override fun getAllCourses(): List<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponses) {
            var course : CourseEntity
            response.apply {
                course = CourseEntity(id,title,description,date, false, imagePath)
            }
            courseList.add(course)
        }
        return courseList
    }

    override fun getBookmarkedCourses(): List<CourseEntity> {
        val courseResponse = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponse) {
            val course = CourseEntity(response.id, response.title, response.description,response.date, false, response.imagePath)
            courseList.add(course)
        }
        return courseList
    }

    //mengambil course dengan module didalamnya
    override fun getCourseWithModules(courseId: String): CourseEntity {
        val courseResponse = remoteDataSource.getAllCourses()
        lateinit var course : CourseEntity
        for (response in courseResponse) {
            if (response.id == courseId) {
                course = CourseEntity(response.id,response.title,response.description,response.date,false, response.imagePath)
            }
        }
        return course
    }

    override fun getAllModulesByCourse(courseId: String): List<ModuleEntity> {
        val moduleResponses = remoteDataSource.getModule(courseId)
        val moduleList = ArrayList<ModuleEntity>()
        for(response in moduleResponses) {
            val course = ModuleEntity(response.moduleId,
                response.courseId,
                response.title,
                response.position,
                false)
            moduleList.add(course)
        }
        return moduleList
    }

    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val moduleResponses = remoteDataSource.getModule(courseId)
        lateinit var module: ModuleEntity
        for(response in moduleResponses) {
            if (response.moduleId == moduleId) {
                module = ModuleEntity(response.moduleId,
                    response.courseId,
                    response.title,
                    response.position,
                    false)
                module.contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                break
            }
        }
        return module
    }

}