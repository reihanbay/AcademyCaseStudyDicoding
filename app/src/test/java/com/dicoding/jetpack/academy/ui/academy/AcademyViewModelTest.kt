package com.dicoding.jetpack.academy.ui.academy

import com.dicoding.jetpack.academy.data.CourseEntity
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
class AcademyViewModelTest {

    private lateinit var viewModel : AcademyViewModel

    @Mock
    private lateinit var academyRepository : AcademyRepository

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        `when`(academyRepository.getAllCourses()).thenReturn(DataDummy.generateDummyCourses() as ArrayList<CourseEntity>)
        val courseEntities = viewModel.getCourses()
        verify<AcademyRepository>(academyRepository).getAllCourses() //verify digunakan untuk mengecek apakah metode getAllCourse akan terpanggil jika Anda memanggil viewModel.getCourses().
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}