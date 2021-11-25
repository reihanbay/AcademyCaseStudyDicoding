package com.dicoding.jetpack.academy.ui.reader

import java.text.FieldPosition

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}