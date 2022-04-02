package com.example.trymvvm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trymvvm.data.entity.DiaryEntity

@Dao
interface DiaryDao {

    @Query("SELECT * FROM DiaryEntity")
    fun getAllDiaries() : List<DiaryEntity>

    @Query("SELECT * FROM DiaryEntity WHERE id=:diaryId")
    fun getDiary(diaryId: String): DiaryEntity?

    @Insert
    fun insertDiary(diaryEntity: DiaryEntity)
}