package com.example.trymvvm.presentation.diary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trymvvm.databinding.ItemDiaryListBinding
import com.example.trymvvm.domain.Diary

class DiaryAdapter(private val onClick: ((Diary) -> Unit)? = null) :
    ListAdapter<Diary, DiaryAdapter.DiaryViewHolder>(DiaryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DiaryViewHolder(
            ItemDiaryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class DiaryViewHolder(private val binding: ItemDiaryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Diary, onClick: ((Diary) -> Unit)? = null) {
            binding.diary = model
            binding.root.setOnClickListener { onClick?.invoke(model) }
        }
    }

    class DiaryDiffUtil : DiffUtil.ItemCallback<Diary>() {
        override fun areItemsTheSame(oldItem: Diary, newItem: Diary) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Diary, newItem: Diary) =
            oldItem == newItem
    }

}