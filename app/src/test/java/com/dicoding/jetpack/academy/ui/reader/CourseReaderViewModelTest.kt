package com.dicoding.jetpack.academy.ui.reader

import com.dicoding.jetpack.academy.data.ContentEntity
import com.dicoding.jetpack.academy.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class CourseReaderViewModelTest {
    private lateinit var vm : CourseReaderViewModel

    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    @Before
    fun setUp() {
        vm = CourseReaderViewModel()
        vm.setSelectedCourse(courseId)
        vm.setsSelectedModule(moduleId)

        //setting content pada dummy ModulesEntity.ContentEntity
        val dummyModule = dummyModules[0]
        dummyModule.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">"+dummyModule.title+"</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")

    }

    @Test
    fun getModules() {
        val moduleEntities = vm.getModules()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }

    @Test
    fun getSelectedModule() {
        val moduleEntity = vm.getSelectedModule()
        assertNotNull(moduleEntity)
        val contentEntity = moduleEntity.contentEntity
        assertNotNull(contentEntity)
        val content = contentEntity?.content
        assertNotNull(content)
        assertEquals(content, dummyModules[0].contentEntity?.content)
    }
}