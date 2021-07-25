package com.terry.videoplayer

import android.os.Bundle
import com.terry.common.base.BaseActivity
import com.terry.videoplayer.databinding.ActivityVideoMainBinding

class VideoMainActivity :
    BaseActivity<ActivityVideoMainBinding>(ActivityVideoMainBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PlayerFragment())
            .commit()
    }
}