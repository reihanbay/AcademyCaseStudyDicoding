package com.dicoding.jetpack.academy.ui.reader

import com.dicoding.jetpack.academy.data.ContentEntity
import com.dicoding.jetpack.academy.data.ModuleEntity
import com.dicoding.jetpack.academy.data.source.AcademyRepository
import com.dicoding.jetpack.academy.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CourseReaderViewModelTest {
    private lateinit var vm : CourseReaderViewModel

    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    @Mock
    private lateinit var academyRepository: AcademyRepository
    @Before
    fun setUp() {
        vm = CourseReaderViewModel(academyRepository)
        vm.setSelectedCourse(courseId)
        vm.setsSelectedModule(moduleId)

        //setting content pada dummy ModulesEntity.ContentEntity
        val dummyModule = dummyModules[0]
        dummyModule.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">"+dummyModule.title+"</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")

    }

    @Test
    fun getModules() {
        `when`<List<ModuleEntity>>(academyRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules)
        val moduleEntities = vm.getModules()
        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }

    @Test
    fun getSelectedModule() {
        `when`(academyRepository.getContent(courseId,moduleId)).thenReturn(dummyModules[0])
        val moduleEntity = vm.getSelectedModule()
        verify(academyRepository).getContent(courseId, moduleId)
        assertNotNull(moduleEntity)
        val contentEntity = moduleEntity.contentEntity
        assertNotNull(contentEntity)
        val content = contentEntity?.content
        assertNotNull(content)
        assertEquals(content, dummyModules[0].contentEntity?.content)
    }
}