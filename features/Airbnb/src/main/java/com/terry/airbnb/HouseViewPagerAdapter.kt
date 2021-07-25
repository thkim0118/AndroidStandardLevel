package com.terry.airbnb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.terry.airbnb.databinding.ItemHouseDetailBinding
import com.terry.remote.model.airbnb.HouseModel

/*
 * Created by Taehyung Kim on 2021-07-25
 */
class HouseViewPagerAdapter(
    val itemClicked: (HouseModel) -> Unit
) : ListAdapter<HouseModel, HouseViewPagerAdapter.ItemViewHolder>(differ) {

    class ItemViewHolder(
        private val binding: ItemHouseDetailBinding,
        private val itemClicked: (HouseModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(houseModel: HouseModel) {
            binding.titleTextView.text = houseModel.title
            binding.priceTextView.text = houseModel.price

            Glide
                .with(binding.thumbnailImageView.context)
                .load(houseModel.imgUrl)
                .into(binding.thumbnailImageView)

            binding.root.setOnClickListener {
                itemClicked(houseModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemHouseDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClicked
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<HouseModel>() {
            override fun areItemsTheSame(oldItem: HouseModel, newItem: HouseModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HouseModel, newItem: HouseModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}