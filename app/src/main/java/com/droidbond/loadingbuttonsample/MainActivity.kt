package com.droidbond.loadingbuttonsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var show = true

        buttonTap.setOnClickListener {
            if (show) {
                custombtn.showLoading()
                normal.showLoading()
            } else {
                custombtn.hideLoading()
                normal.hideLoading()
            }

            show = !show
        }

        custombtn.setOnClickListener {
            Log.d("hhh", "hhhh")
        }

        normal.setOnClickListener {
            Log.d("hhh", "hhhh")
        }
    }

}
