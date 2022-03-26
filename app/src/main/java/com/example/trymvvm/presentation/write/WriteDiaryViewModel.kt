package com.example.trymvvm.presentation.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trymvvm.data.DiaryMemory
import com.example.trymvvm.domain.Diary
import java.lang.IllegalStateException
import java.util.*

class WriteDiaryViewModel : ViewModel() {
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    private val _createDate = MutableLiveData(Date())
    val createDate: LiveData<Date> = _createDate

    fun loadDiary(diaryId: String?) {
        val diary = DiaryMemory.getDiary(diaryId ?: return)
        title.value = diary.title
        content.value = diary.content
        _createDate.value = diary.date
    }

    fun saveDiary() {
        val title = this.title.value.orEmpty()
        val content = this.content.value.orEmpty()
        val createDate = this._createDate.value ?: throw IllegalStateException("create date cannot be null")

        val diary = Diary(UUID.randomUUID().toString(), title, content, createDate)
        DiaryMemory.saveDiary(diary)
    }
}