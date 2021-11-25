package com.dicoding.jetpack.academy.ui.bookmark

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class BookmarkViewModelTest {

    private lateinit var vm  : BookmarkViewModel
    @Before
    fun setUp() {
        vm = BookmarkViewModel()
    }
    @Test
    fun getBookmarks() {
        val courseEntities = vm.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}