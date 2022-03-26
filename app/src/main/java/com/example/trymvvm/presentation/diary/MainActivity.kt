package com.example.trymvvm.presentation.diary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.trymvvm.databinding.ActivityMainBinding
import com.example.trymvvm.domain.Diary
import com.example.trymvvm.presentation.write.WriteActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val diaryAdapter by lazy { DiaryAdapter(::onDiaryClick) }
    private lateinit var writeDiaryLauncher: ActivityResultLauncher<Intent>

    private val diariesViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        writeDiaryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())  {
            onWriteFinished(it)
        }

        diariesViewModel.diaries.observe(this) {
            diaryAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        diariesViewModel.loadDiaries()
    }

    private fun initRecyclerView() {
        binding.rcvMain.adapter = diaryAdapter
    }

    private fun onWriteFinished(result: ActivityResult) {
        return when(result.resultCode) {
            Activity.RESULT_OK -> showToast("작성 완료!")
            Activity.RESULT_CANCELED -> showToast("작성이 취소되었어요!")
            else -> showToast("알 수 없는 오류 : $result")
        }
    }

    private fun onDiaryClick(diary: Diary) {
        deployWriteActivity(diary)
        showToast(diary.title)
    }

    private fun deployWriteActivity(diary: Diary? = null) {
        Intent(this, WriteActivity::class.java).apply {
            putExtra(WriteActivity.KEY_DIARY_ID, diary?.id)
            writeDiaryLauncher.launch(this)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}