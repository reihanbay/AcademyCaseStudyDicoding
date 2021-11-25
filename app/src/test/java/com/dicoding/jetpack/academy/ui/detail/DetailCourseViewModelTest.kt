package com.dicoding.jetpack.academy.ui.detail

import com.dicoding.jetpack.academy.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class DetailCourseViewModelTest {
    private lateinit var vm : DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId

    @Before
    fun setUp() {
        vm = DetailCourseViewModel()
        vm.setSelectedCourse(courseId)
    }

    @Test
    fun setSelectedCourse() {
    }

    @Test
    fun getCourse() {
        vm.setSelectedCourse(dummyCourse.courseId)//sebenarnya ga perlu gapapa karna sudah di setUp
        val courseEntity = vm.getCourse()
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.desc, courseEntity.desc)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModules() {
        val moduleEntities = vm.getModules()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }
}