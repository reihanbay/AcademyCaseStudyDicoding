package com.dicoding.jetpack.academy.ui.bookmark

import com.dicoding.jetpack.academy.data.CourseEntity
import com.dicoding.jetpack.academy.data.source.AcademyRepository
import com.dicoding.jetpack.academy.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var vm  : BookmarkViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        vm = BookmarkViewModel(academyRepository)
    }
    @Test
    fun getBookmarks() {
        Mockito.`when`<List<CourseEntity>>(academyRepository.getBookmarkedCourses()).thenReturn(DataDummy.generateDummyCourses() as List<CourseEntity>)
        val courseEntities = vm.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}