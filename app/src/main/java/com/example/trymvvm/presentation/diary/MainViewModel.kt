package com.example.trymvvm.presentation.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trymvvm.data.DiaryMemory
import com.example.trymvvm.domain.Diary

class MainViewModel : ViewModel() {

    private val _diaries = MutableLiveData<List<Diary>>()
    val diaries : LiveData<List<Diary>> = _diaries

    fun loadDiaries() {
        _diaries.value = DiaryMemory.getAllDiaries()
    }

}