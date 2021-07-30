package com.terry.musicplayer.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.terry.musicplayer.databinding.ItemMusicBinding
import com.terry.musicplayer.model.MusicModel


/*
 * Created by Taehyung Kim on 2021-07-30
 */
class PlayListAdapter(
    private val callback: (MusicModel) -> Unit
) : ListAdapter<MusicModel, PlayListAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(
        private val binding: ItemMusicBinding,
        private val callback: (MusicModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MusicModel) {
            binding.itemTrackTextView.text = item.track
            binding.itemArtistTextView.text = item.artist

            Glide
                .with(binding.itemCoverImageView.context)
                .load(item.coverUrl)
                .into(binding.itemCoverImageView)

            if (item.isPlaying) {
                itemView.setBackgroundColor(Color.GRAY)
            } else {
                itemView.setBackgroundColor(Color.TRANSPARENT)
            }

            itemView.setOnClickListener {
                callback(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callback
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList[position].also { musicModel ->
            holder.bind(musicModel)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MusicModel>() {
            override fun areItemsTheSame(oldItem: MusicModel, newItem: MusicModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MusicModel, newItem: MusicModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}