package com.droidbond.loadingbutton

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView


class LoadingButton @JvmOverloads constructor(
    @NonNull context: Context, @Nullable attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val TAG = "LoadingButton"

    private var progressBar: ProgressBar? = null
    private var tvText: TextView? = null
    private var array: TypedArray? = null
    private lateinit var clickListener: OnClickListener

    init {
        array = context.theme.obtainStyledAttributes(attrs, R.styleable.app, defStyleAttr, 0)
        initView()
    }

    fun hideLoading() {
        if (progressBar != null) {
            progressBar!!.visibility = View.INVISIBLE
        }

        if (tvText != null) {
            tvText!!.visibility = View.VISIBLE
        }
        Log.d(TAG, "stopLoading: ")
    }

    fun showLoading() {
        if (progressBar != null) {
            progressBar!!.visibility = View.VISIBLE
        }

        if (tvText != null) {
            tvText!!.visibility = View.INVISIBLE
        }
        Log.d(TAG, "startLoading: ")
    }

    override fun setOnClickListener(listener: OnClickListener) {
        clickListener = listener
    }

    private fun initView() {

        val view = inflate(context, R.layout.layout_loading_button_lb, null)
        addView(view)

        view.setOnClickListener {
            clickListener.onClick(view)
        }

        val bg = array?.getResourceId(R.styleable.app_background, 0)

        if (bg != 0) {
            view.setBackgroundResource(bg!!)
        }

        progressBar = view.findViewById(R.id.pb)
        tvText = view.findViewById(R.id.tvText)

        progressBar?.indeterminateDrawable?.setColorFilter(
            array!!.getColor(R.styleable.app_progressColor, resources.getColor(R.color.white)),
            PorterDuff.Mode.SRC_ATOP
        )

        var pbSize: Int = array?.getInteger(R.styleable.app_progressBarSize, 32)!!
        pbSize = Math.round(pbSize * (Resources.getSystem().displayMetrics.density))

        val pbParams = LayoutParams(WRAP_CONTENT, pbSize)
        pbParams.gravity = Gravity.CENTER
        progressBar?.layoutParams = pbParams


        var text = "Loading Button"
        if (array!!.getText(R.styleable.app_text) != null) {
            text = array!!.getText(R.styleable.app_text).toString()
        }

        tvText!!.text = text
        tvText!!.setTextColor(array!!.getColor(R.styleable.app_textColor, resources.getColor(R.color.white)))

        if (array!!.getBoolean(R.styleable.app_boldText, false)) {
            tvText!!.setTypeface(null, Typeface.BOLD)
        }

        var size = array!!.getDimension(R.styleable.app_textSize, 14f * Resources.getSystem().displayMetrics.density)
        size /= Resources.getSystem().displayMetrics.density

        tvText!!.textSize = size

        array?.recycle()
    }

}
