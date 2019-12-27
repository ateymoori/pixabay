package com.pixabay.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixabay.utils.ImageLoader
import com.pixabay.R
import com.pixabay.utils.Cons.Companion.ITEM_BUNDLE
import com.pixabay.utils.entities.ImageModel
import com.pixabay.utils.views.ExpandedBottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class DetailFragment : ExpandedBottomSheetDialog() {
    private var item: ImageModel? = null

    @Inject
    lateinit var imageLoader: ImageLoader


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable(ITEM_BUNDLE)
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

        imageLoader.load(url = item?.webformatURL, imageView = img)

    }
}
