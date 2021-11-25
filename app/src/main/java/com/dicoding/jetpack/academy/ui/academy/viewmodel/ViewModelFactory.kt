package com.dicoding.jetpack.academy.ui.academy.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.dicoding.jetpack.academy.data.source.AcademyRepository

class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) :ViewModelProvider.NewInstanceFactory(){
}