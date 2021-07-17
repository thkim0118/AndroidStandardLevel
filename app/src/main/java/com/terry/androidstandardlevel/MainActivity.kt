package com.terry.androidstandardlevel

import android.os.Bundle
import com.terry.androidstandardlevel.databinding.ActivityMainBinding
import com.terry.common.base.BaseActivity
import com.terry.common.util.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        startActivity("com.thkim.calculator.CalculatorMainActivity")
//        startActivity("com.terry.pomodoro.PomodoroMainActivity")
//        startActivity("com.terry.recorder.RecorderMainActivity")
//        startActivity("com.terry.webviewer.WebViewerMainActivity")
//        startActivity("com.terry.notification.NotificationMainActivity")
//        startActivity("com.terry.remoteconfig.SayingMainActivity")
//        startActivity("com.terry.alram.AlarmMainActivity")
//        startActivity("com.terry.books.BooksMainActivity")
        startActivity("com.terry.tinder.TinderMainActivity")
    }
}