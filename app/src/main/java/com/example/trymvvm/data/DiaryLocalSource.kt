package com.example.trymvvm.data

import com.example.trymvvm.data.dao.DiaryDao
import com.example.trymvvm.data.entity.DiaryEntity
import com.example.trymvvm.data.executor.DispatchExecutors
import com.example.trymvvm.domain.Diary

class DiaryLocalSource(
    private val diaryDao: DiaryDao,
    private val dispatchExecutors: DispatchExecutors = DispatchExecutors.getInstance()
) {

    fun getDiary(
        diaryId: String,
        onSuccess: (Diary?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        dispatchExecutors.ioThread.execute {

            try {
                val diaryEntity = diaryDao.getDiary(diaryId)
                val diary = diaryEntity.takeIf { it == null }
                    ?.let {
                        Diary(
                            it.id, it.title, it.content, it.date
                        )
                    }

                dispatchExecutors.mainThread.execute {
                    onSuccess(diary)
                }
            } catch (e: Exception) {
                dispatchExecutors.mainThread.execute {
                    onFailure(e)
                }
            }

        }
    }


    fun getDiary(
        diaryId: String,
        onResult: (Result<Diary?>) -> Unit
    ) {
        dispatchExecutors.ioThread.execute {
            runCatching { diaryDao.getDiary(diaryId) }
                .map { it.toDiary() }
                .also {
                    dispatchExecutors.mainThread.execute { onResult(it) }
                }
        }
    }

    fun saveDiary(
        diary: Diary,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        dispatchExecutors.ioThread.execute {

            try {
                diaryDao.insertDiary(diary.toDiaryEntity())

                dispatchExecutors.mainThread.execute {
                    onSuccess()
                }
            } catch (e: Exception) {
                dispatchExecutors.mainThread.execute {
                    onFailure(e)
                }
            }
        }
    }

    private fun DiaryEntity?.toDiary(): Diary? {
        return Diary(
            this?.id ?: return null,
            this?.title ?: return null,
            this?.content ?: return null,
            this?.date ?: return null
        )
    }


    private fun Diary.toDiaryEntity(): DiaryEntity {
        return DiaryEntity(
            this.title,
            this.content,
            this.date,
            this.id
        )
    }
}