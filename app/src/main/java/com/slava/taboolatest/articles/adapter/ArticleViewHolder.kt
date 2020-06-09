package com.slava.taboolatest.articles.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.slava.taboolatest.R
import com.slava.taboolatest.articles.entities.ArticleDataModel

class ArticleViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val image = itemView.findViewById<ImageView>(R.id.image)

    fun bind(articleDataModel: ArticleDataModel) {
        title.text = articleDataModel.name
        description.text = articleDataModel.description

        Glide.with(image.context).load(articleDataModel.thumbnail)
            .transition(DrawableTransitionOptions.withCrossFade()).into(image)
    }
}