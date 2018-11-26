package com.droidbond.loadingbuttonsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var show = 0

        buttonTap.setOnClickListener {
            if (show == 0) {
                custombtn.showLoading()
                normal.showLoading()
                show = 1
            } else if(show == 1 && custombtn.isLoading()){
                    custombtn.showSuccess()
                    normal.showError()
                show = 2
            }else{
                custombtn.hideLoading()
                normal.hideLoading()
                show = 0
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
