package com.pixabay.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pixabay.utils.ImageLoader
import com.pixabay.R
import com.pixabay.utils.entities.ImageModel
import com.pixabay.utils.tools.listen
import com.pixabay.utils.views.ViewCountsView
import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class ResultsAdapter @Inject constructor() :
    RecyclerView.Adapter<ResultsAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var itemClick: OnItemClick

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        ctx = parent.context
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        ).listen { pos, _ ->
            items[pos].let { itemClick.onItemClick(it) }
        }
    }

    var items = listOf<ImageModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface OnItemClick {
        fun onItemClick(item: ImageModel)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]

        holder.username.text = item.user
        holder.tags.text = item.tags
        holder.viewsCount.setCount(item.views)
        holder.favoritesCount.setCount(item.favorites)

        imageLoader.load(
            preLoadUrl = item.previewURL,
            url = item.webformatURL,
            imageView = holder.img
        )

        imageLoader.load(
            url = item.userImageURL,
            imageView = holder.avatar
        )

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.img
        val avatar: ImageView = view.avatar
        val username: TextView = view.username
        val tags: TextView = view.tags
        val viewsCount: ViewCountsView = view.viewsCount
        val favoritesCount: ViewCountsView = view.favoritesCount
    }

}