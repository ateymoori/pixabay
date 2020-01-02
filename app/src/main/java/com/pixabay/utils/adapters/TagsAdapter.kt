package com.pixabay.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pixabay.utils.ImageLoader
import com.pixabay.R
import com.pixabay.utils.tools.listen
import com.pixabay.utils.views.MyTextView
import kotlinx.android.synthetic.main.tag_item.view.*
import javax.inject.Inject

class TagsAdapter @Inject constructor() :
    RecyclerView.Adapter<TagsAdapter.Holder>() {
    private lateinit var ctx: Context
    lateinit var tagClick: TagClickListener

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        ctx = parent.context
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tag_item,
                parent,
                false
            )
        ).listen { pos, _ ->
            tags[pos].let { tagClick.onTagClicked(it) }
        }
    }

    var tags = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface TagClickListener {
        fun onTagClicked(tag: String)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = tags[position]
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val title: MyTextView = view.title
    }

}