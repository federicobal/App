package com.project.app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.project.app.databinding.FragmentHomeItemBinding
import com.project.app.model.RecyclerData

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var mItems: List<RecyclerData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentHomeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    fun setUpdateList(mDeveloperModel: List<RecyclerData>) {
        this.mItems = mDeveloperModel
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = mItems!![position]
//        holder.tvName.text = item.name
//        holder.tvDescrption.text = item.description
//        Glide.with(holder.ivImagen)
//            .load(item.owner?.avatar_url)
//            .apply(RequestOptions.centerCropTransform())
//            .into(holder.ivImagen)
        val item = mItems!![position]
        holder.tvName.text = item.name
        holder.tvDescrption.text = item.observation
        Glide.with(holder.ivImagen)
            .load(item.urlimg)
            .apply(RequestOptions.centerCropTransform())
            .into(holder.ivImagen)

    }

    override fun getItemCount(): Int = if (mItems == null) 0 else mItems!!.size

    inner class ViewHolder(binding: FragmentHomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivImagen: ImageView = binding.ivImagen
        val tvName: TextView = binding.tvName
        val tvDescrption: TextView = binding.tvDescription
        override fun toString(): String {
            return super.toString() + " '" + tvName.text + "'"
        }
    }

}