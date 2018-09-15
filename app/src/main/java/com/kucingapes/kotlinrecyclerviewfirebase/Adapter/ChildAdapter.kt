package com.kucingapes.kotlinrecyclerviewfirebase.Adapter

import android.content.Context
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kucingapes.kotlinrecyclerviewfirebase.R
import kotlinx.android.synthetic.main.item_image.view.*

class ChildAdapter(var context: Context, var list: List<String>) : RecyclerView.Adapter<ChildAdapter.ImageHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ImageHolder {
        context = p0.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, p0, false)
        return ImageHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ImageHolder, p1: Int) {
        val url = list.get(p1)
        val circularProgressDrawable = CircularProgressDrawable(context)
        val requestOptions = RequestOptions.placeholderOf(circularProgressDrawable)

        Glide.with(context).load(url).apply(requestOptions).into(p0.image)
    }


    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.image
    }
}