package com.dicoding.jetpack.academy.ui.detail

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
class DetailCourseViewModelTest {
    private lateinit var vm : DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId


    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        vm = DetailCourseViewModel(academyRepository)
        vm.setSelectedCourse(courseId)
    }

    @Test
    fun setSelectedCourse() {
    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse)
//        vm.setSelectedCourse(dummyCourse.courseId)//sebenarnya ga perlu gapapa karna sudah di setUp

        val courseEntity = vm.getCourse()
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.desc, courseEntity.desc)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModules() {
        `when`<List<ModuleEntity>>(academyRepository.getAllModulesByCourse(courseId)).thenReturn(DataDummy.generateDummyModules(courseId))
        val moduleEntities = vm.getModules()
        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }
}