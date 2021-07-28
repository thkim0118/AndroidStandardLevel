package com.terry.videoplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.terry.remote.model.videoplayer.VideoModel
import com.terry.videoplayer.databinding.ItemVideoBinding

/*
 * Created by Taehyung Kim on 2021-07-26
 */
class VideoAdapter(
    private val callback: (String, String) -> Unit
) : ListAdapter<VideoModel, VideoAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(
        private val binding: ItemVideoBinding,
        private val callback: (String, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VideoModel) {
            binding.titleTextView.text = item.title
            binding.subTitleTextView.text = item.subtitle

            Glide
                .with(binding.thumbnailImageView.context)
                .load(item.thumb)
                .into(binding.thumbnailImageView)

            binding.root.setOnClickListener {
                callback(item.sources, item.title)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<VideoModel>() {
            override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callback
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

}