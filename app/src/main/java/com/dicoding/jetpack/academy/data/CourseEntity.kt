package com.dicoding.jetpack.academy.data

data class CourseEntity(
    var courseId : String,
    var title : String,
    var desc: String,
    var deadline : String,
    var bookmarked: Boolean = false,
    var imagePath : String
)
