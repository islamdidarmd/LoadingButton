package com.droidbond.loadingbutton

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Build
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.content.res.ResourcesCompat
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

/**
 * This class is a compound view includes a button, loader , success view and error view
 *
 * Use this class in your xml descriptor and edit attrs
 *
 * @param context The Context
 * @param attrs the AttributeSet
 * @param defStyleAttr the default Style
 */
class LoadingButton @JvmOverloads constructor(
    @NonNull context: Context, @Nullable attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val TAG = "LoadingButton"

    private lateinit var progressBar: ProgressBar
    private lateinit var img: ImageView
    private lateinit var view: View
    private lateinit var tvText: TextView
    private var array: TypedArray =
        context.theme.obtainStyledAttributes(attrs, R.styleable.app, defStyleAttr, 0)
    private var bg: Int = R.drawable.ic_bg_blue_buttons_style
    private var successBg: Int = R.drawable.ic_bg_blue_buttons_style
    private var errorBg: Int = R.drawable.ic_bg_red_buttons_style
    private var successIcon: Int = R.drawable.ic_done_white_24dp
    private var errorIcon: Int = R.drawable.ic_warning

    private var shouldShowBoldText = false

    init {
        initView()
    }

    /**
     * Hides loader and shows text on button
     */
    fun hideLoading() {
        view.setBackgroundResource(bg)
        progressBar.hideSelf()
        img.hideSelf()
        tvText.showSelf()
        Log.d(TAG, "stopLoading: ")
    }

    /**
     * Hides text, loader and shows success icon
     */
    fun showSuccess() {
        tvText.hideSelf()
        view.setBackgroundResource(successBg)

        if (Build.VERSION.SDK_INT >= 19) {
            TransitionManager.beginDelayedTransition(view as ViewGroup, Fade(Fade.IN))
        }
        progressBar.hideSelf()
        img.showSelf()
        img.setImageResource(successIcon)
    }

    /**
     * Hides text, loader and shows error icon with a red background
     */
    fun showError() {
        tvText.hideSelf()

        view.setBackgroundResource(errorBg)

        if (Build.VERSION.SDK_INT >= 19) {
            TransitionManager.beginDelayedTransition(view as ViewGroup, Fade(Fade.IN))
        }

        progressBar.hideSelf()
        img.showSelf()
        img.setImageResource(errorIcon)
    }

    /**
     * Shows loading animation
     */
    fun showLoading() {
        view.setBackgroundResource(bg)
        progressBar.showSelf()
        tvText.hideSelf()
        img.hideSelf()
        Log.d(TAG, "startLoading: ")
    }

    /**
     * Function isLoading() return type Boolean
     * @return the state of loader.
     */
    fun isLoading(): Boolean {
        return progressBar.isShown
    }

    private fun initView() {

        view = inflate(context, R.layout.layout_loading_button_lb, null)
        progressBar = view.findViewById(R.id.pb)
        img = view.findViewById(R.id.img)
        tvText = view.findViewById(R.id.tvText)

        addView(view)

        updateView()
        updateLoader()
        updatedText()

        array.recycle()
    }

    private fun updateView() {

        view.apply {
            val customBg = array.getResourceId(R.styleable.app_background, 0)
            if (customBg != 0) {
                setBackgroundResource(customBg)
                bg = customBg
            }

            if (array.getResourceId(R.styleable.app_successBackground, 0) != 0) {
                successBg = array.getResourceId(R.styleable.app_successBackground, 0)
            }
            if (array.getResourceId(R.styleable.app_errorBackground, 0) != 0) {
                errorBg = array.getResourceId(R.styleable.app_errorBackground, 0)
            }

            if (array.getResourceId(R.styleable.app_successIcon, 0) != 0) {
                successIcon = array.getResourceId(R.styleable.app_successIcon, 0)
            }
            if (array.getResourceId(R.styleable.app_errorIcon, 0) != 0) {
                errorIcon = array.getResourceId(R.styleable.app_errorIcon, 0)
            }
        }
    }

    private fun updateLoader() {
        progressBar.apply {

            var pbSize: Int = array.getInteger(R.styleable.app_progressBarSize, 32)
            pbSize = Math.round(pbSize * (Resources.getSystem().displayMetrics.density))

            val pbParams = LayoutParams(WRAP_CONTENT, pbSize)
            pbParams.gravity = Gravity.CENTER

            indeterminateDrawable?.setColorFilter(
                array.getColor(R.styleable.app_progressColor, resources.getColor(R.color.white)),
                PorterDuff.Mode.SRC_ATOP
            )
            layoutParams = pbParams
        }
    }

    private fun updatedText() {
        tvText.apply {
            var customText = "Loading Button"
            var size = array.getDimension(
                R.styleable.app_textSize,
                14f * Resources.getSystem().displayMetrics.density
            )
            var tf: Typeface? = null

            if (array.getText(R.styleable.app_text) != null) {
                customText = array.getText(R.styleable.app_text).toString()
            }
            size /= Resources.getSystem().displayMetrics.density

            text = customText
            setTextColor(
                array.getColor(
                    R.styleable.app_textColor,
                    resources.getColor(R.color.white)
                )
            )
            textSize = size

            shouldShowBoldText = array.getBoolean(R.styleable.app_boldText, false)

            if (array.hasValue(R.styleable.app_customFontFamily)) {
                val fontId = array.getResourceId(R.styleable.app_customFontFamily, -1)
                tf = ResourcesCompat.getFont(context, fontId)
            }
            setTypeFace(tf)
        }
    }

    /**
     * sets a custom [Typeface] for text.
     */
    fun setTypeFace(typeface: Typeface?) {
        if (shouldShowBoldText) {
            tvText.setTypeface(typeface, Typeface.BOLD)
        } else {
            tvText.typeface = typeface
        }
    }

}
