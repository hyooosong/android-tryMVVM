package com.example.trymvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trymvvm.data.converter.DateTypeConverter
import com.example.trymvvm.data.dao.DiaryDao
import com.example.trymvvm.data.entity.DiaryEntity

@Database(entities = [DiaryEntity::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class DiaryDataBase : RoomDatabase() {

    abstract fun getDiaryDao(): DiaryDao

    companion object {
        @JvmStatic
        fun newInstance(context: Context): DiaryDataBase {
            return Room.databaseBuilder(
                context,
                DiaryDataBase::class.java,
                "Diary"
            ).build()
        }
    }

}