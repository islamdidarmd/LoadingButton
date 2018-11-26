package com.droidbond.loadingbutton

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.v4.content.res.ResourcesCompat
import android.transition.Fade
import android.transition.TransitionManager
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView


class LoadingButton @JvmOverloads constructor(
    @NonNull context: Context, @Nullable attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val TAG = "LoadingButton"

    private lateinit var progressBar: ProgressBar
    private lateinit var img: ImageView
    private lateinit var view: View
    private lateinit var tvText: TextView
    private var array: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.app, defStyleAttr, 0)
    private var bg: Int = R.drawable.ic_bg_blue_buttons_style

    init {
        initView()
    }

    fun hideLoading() {
        view.setBackgroundResource(bg)
        progressBar.visibility = View.INVISIBLE
        img.visibility = View.INVISIBLE
        tvText.visibility = View.VISIBLE
        Log.d(TAG, "stopLoading: ")
    }

    fun showSuccess() {
        tvText.visibility = View.INVISIBLE
        view.setBackgroundResource(bg)

        if (Build.VERSION.SDK_INT >= 19) {
            TransitionManager.beginDelayedTransition(view as ViewGroup, Fade(Fade.IN))
        }
        progressBar.visibility = View.INVISIBLE
        img.visibility = View.VISIBLE
        img.setImageResource(R.drawable.ic_done_white_24dp)
    }

    fun showError() {
        tvText.visibility = View.INVISIBLE

        view.setBackgroundResource(R.drawable.ic_bg_red_buttons_style)

        if (Build.VERSION.SDK_INT >= 19) {
            TransitionManager.beginDelayedTransition(view as ViewGroup, Fade(Fade.IN))
        }

        progressBar.visibility = View.INVISIBLE
        img.visibility = View.VISIBLE
        img.setImageResource(R.drawable.ic_warning)
    }

    fun showLoading() {
        view.setBackgroundResource(bg)
        progressBar.visibility = View.VISIBLE
        tvText.visibility = View.INVISIBLE
        img.visibility = View.INVISIBLE
        Log.d(TAG, "startLoading: ")
    }

    fun isLoading():Boolean{
        return progressBar.isShown
    }

    private fun initView() {

        view = inflate(context, R.layout.layout_loading_button_lb, null)
        addView(view)

        val bg = array.getResourceId(R.styleable.app_background, 0)

        if (bg != 0) {
            view.setBackgroundResource(bg)
            this.bg = bg
        }

        progressBar = view.findViewById(R.id.pb)
        img = view.findViewById(R.id.img)

        tvText = view.findViewById(R.id.tvText)

        progressBar.indeterminateDrawable?.setColorFilter(
            array.getColor(R.styleable.app_progressColor, resources.getColor(R.color.white)),
            PorterDuff.Mode.SRC_ATOP
        )

        var pbSize: Int = array.getInteger(R.styleable.app_progressBarSize, 32)
        pbSize = Math.round(pbSize * (Resources.getSystem().displayMetrics.density))

        val pbParams = LayoutParams(WRAP_CONTENT, pbSize)
        pbParams.gravity = Gravity.CENTER
        progressBar.layoutParams = pbParams


        var text = "Loading Button"
        if (array.getText(R.styleable.app_text) != null) {
            text = array.getText(R.styleable.app_text).toString()
        }

        tvText.text = text
        tvText.setTextColor(array.getColor(R.styleable.app_textColor, resources.getColor(R.color.white)))

        if (array.getBoolean(R.styleable.app_boldText, false)) {
            tvText.setTypeface(null, Typeface.BOLD)
        }

        var size = array.getDimension(R.styleable.app_textSize, 14f * Resources.getSystem().displayMetrics.density)
        size /= Resources.getSystem().displayMetrics.density

        tvText.textSize = size

        if (array.hasValue(R.styleable.app_customFontFamily)) {
            val fontId = array.getResourceId(R.styleable.app_customFontFamily, -1)
            val typeface = ResourcesCompat.getFont(context, fontId)
            tvText.typeface = typeface
        } else {
            tvText.typeface = null
        }

        array.recycle()
    }

}
