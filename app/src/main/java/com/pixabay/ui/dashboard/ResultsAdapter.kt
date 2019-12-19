package com.pixabay.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pixabay.R
import com.pixabay.utils.entities.ImageModel
import com.pixabay.utils.tools.listen
import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class ResultsAdapter @Inject constructor() :
    RecyclerView.Adapter<ResultsAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var itemClick: OnItemClick
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
        Glide.with(ctx).load(
            item.webformatURL
        ).placeholder(R.drawable.test)
            .into(holder.img)

        Glide.with(ctx).load(
            item.userImageURL
        ).placeholder(R.drawable.ic_person_black)
            .into(holder.avatar)

    }


    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.img
        val avatar: ImageView = view.avatar
        val username: TextView = view.username
    }

}