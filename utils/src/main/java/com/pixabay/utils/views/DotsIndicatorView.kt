package com.pixabay.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.pixabay.utils.R


class DotsIndicatorView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleRes: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleRes) {

    private var viewLong: Int = 6
    private var enabledSize: Int = 0
    init {
         init(enabledSize)
    }

    public fun setEnabledCount(value:Int){
        init(value)
    }

    private fun init(enabledSize:Int){
        removeAllViews()
        for (i in 1..viewLong) {
            val view = View(context)
            view.layoutParams = LayoutParams( dpToPixel(10),  dpToPixel(10))
            view.background = ContextCompat.getDrawable(context, R.drawable.enabled_disabled_circle)
            //first dot not needs margin left
            if (i > 1) {
                val lp = view.layoutParams as LinearLayoutCompat.LayoutParams
                lp.setMargins( dpToPixel(25), 0, 0, 0)
                view.layoutParams = lp
            }
            view.isEnabled = i <= enabledSize
            addView(view)

        }

    }

    private fun dpToPixel(dp: Int): Int {
        val density =context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}