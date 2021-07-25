package com.terry.airbnb

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.terry.airbnb.databinding.ItemHouseListBinding
import com.terry.remote.model.airbnb.HouseModel

/*
 * Created by Taehyung Kim on 2021-07-25
 */
class HouseListAdapter :
    ListAdapter<HouseModel, HouseListAdapter.ItemViewHolder>(differ) {

    class ItemViewHolder(private val binding: ItemHouseListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(houseModel: HouseModel) {
            binding.titleTextView.text = houseModel.title
            binding.priceTextView.text = houseModel.price

            Glide
                .with(binding.thumbnailImageView.context)
                .load(houseModel.imgUrl) // load 이후에는 bitmap 형식으로 데이터를 가져온다.
                .transform(
                    CenterCrop(),
                    RoundedCorners(dpToPx(binding.thumbnailImageView.context, 12))
                )
                .into(binding.thumbnailImageView)
        }

        private fun dpToPx(context: Context, dp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemHouseListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
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