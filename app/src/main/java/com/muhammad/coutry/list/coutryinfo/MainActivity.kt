package com.muhammad.coutry.list.coutryinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muhammad.coutry.list.coutryinfo.view_model.FeedViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FeedViewModel(application).refreshData()
    }
}