package com.example.imagesearchapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.R
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.imagesearchapp.data.Photo
import com.example.imagesearchapp.databinding.ImageListItemBinding

class PhotoAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<Photo, PhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ImageListItemBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

  inner class PhotoViewHolder(private val binding : ImageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(photo: Photo){
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(com.example.imagesearchapp.R.drawable.ic_baseline_highlight_off_24)
                    .into(imageView)

                userName.text = photo.user.username
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(photo: Photo)
    }

    companion object{
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem == newItem
        }
    }
}

