package com.pixabay.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pixabay.utils.ImageLoader
import com.pixabay.R
import com.pixabay.ui.dashboard.DashboardViewModel
import com.pixabay.utils.Cons.Companion.ITEM_BUNDLE
import com.pixabay.utils.Cons.Companion.TAGS_DELIMITER
import com.pixabay.utils.adapters.TagsAdapter
import com.pixabay.domain.entities.ImageModel
import com.pixabay.utils.views.ExpandedBottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class DetailFragment : ExpandedBottomSheetDialog(), TagsAdapter.TagClickListener {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var tagsAdapter: TagsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DashboardViewModel

    lateinit var item: ImageModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        activity?.run {
            viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)
        }
        arguments?.let {
            item = it.getParcelable(ITEM_BUNDLE)!!
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)

        imageLoader.load(url = item.webformatURL, imageView = img)
        username.text = item.user
        viewsCount.setCount(item.views)
        favoritesCount.setCount(item.favorites)

        tagsView.adapter = tagsAdapter.apply {
            tags = item.tags!!.split(TAGS_DELIMITER)
            tagClick = this@DetailFragment
        }
    }

    override fun onTagClicked(tag: String) {
        viewModel.setTag(tag)
         dismiss()
    }
}