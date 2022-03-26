package com.example.trymvvm.presentation.write

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.trymvvm.databinding.ActivityWriteBinding
import java.util.*

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private val writeDiaryViewModel : WriteDiaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = writeDiaryViewModel
        binding.lifecycleOwner = this

        writeDiaryViewModel.loadDiary(getDiaryId())
        binding.btnWriteComplete.setOnClickListener {
            writeDiaryViewModel.saveDiary()
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun getDiaryId(): String? = intent.getStringExtra(KEY_DIARY_ID)

    companion object {
        const val KEY_DIARY_ID = "KEY_DIARY_ID"
    }
}