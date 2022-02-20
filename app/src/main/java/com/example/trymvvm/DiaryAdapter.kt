package com.example.trymvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trymvvm.databinding.ItemDiaryListBinding

class DiaryAdapter : ListAdapter<DiaryModel, DiaryAdapter.DiaryViewHolder>(DiaryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DiaryViewHolder(
            ItemDiaryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiaryViewHolder(private val binding: ItemDiaryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: DiaryModel) {
            binding.textViewTitle.text = model.title
            binding.textViewContent.text = model.content
            binding.textViewDate.text = model.date
        }
    }

    class DiaryDiffUtil : DiffUtil.ItemCallback<DiaryModel>() {
        override fun areItemsTheSame(oldItem: DiaryModel, newItem: DiaryModel) =
            oldItem.content == newItem.content

        override fun areContentsTheSame(oldItem: DiaryModel, newItem: DiaryModel) =
            oldItem.content == newItem.content
    }

}