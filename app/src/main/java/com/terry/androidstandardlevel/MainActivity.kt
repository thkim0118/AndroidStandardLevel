package com.terry.androidstandardlevel

import android.os.Bundle
import com.terry.androidstandardlevel.databinding.ActivityMainBinding
import com.terry.common.base.BaseActivity
import com.terry.common.util.startActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity("com.terry.pomodoro.PomodoroMainActivity")
    }
}