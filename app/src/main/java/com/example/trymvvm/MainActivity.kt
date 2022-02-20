package com.example.trymvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.trymvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val diaryAdapter by lazy { DiaryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.rcvMain.adapter = diaryAdapter
        setData()
    }

    private fun setData() {
        val list = mutableListOf(
            DiaryModel("adadad", "adadad", "2022-02-20"),
            DiaryModel("adadad", "adadad", "2022-02-20"),
            DiaryModel("adadad", "adadad", "2022-02-20")
        )

        diaryAdapter.submitList(list)
    }
}