package com.droidbond.loadingbuttonsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val typeface = ResourcesCompat.getFont(this, R.font.roboto)
        custombtn.setTypeFace(typeface)

        var show = 0

        buttonTap.setOnClickListener {
            show = if (show == 0) {
                custombtn.showLoading()
                normal.showLoading()
                1
            } else if (show == 1 && custombtn.isLoading()) {
                custombtn.showSuccess()
                normal.showError()
                2
            } else {
                custombtn.hideLoading()
                normal.hideLoading()
                0
            }
        }

        custombtn.setOnClickListener {
            Log.d("hhh", "hhhh")
        }

        normal.setOnClickListener {
            Log.d("hhh", "hhhh")
        }
    }

}
