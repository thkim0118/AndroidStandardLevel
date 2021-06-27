package com.thkim.diary

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import com.terry.common.LogT
import com.terry.common.base.BaseActivity
import com.thkim.diary.databinding.ActivityDiaryBinding

class DiaryActivity : BaseActivity<ActivityDiaryBinding>(ActivityDiaryBinding::inflate) {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

        binding.etDiary.setText(detailPreferences.getString("detail", ""))

        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                putString("detail", binding.etDiary.text.toString())
            }

            LogT.d("Data Saved :: ${binding.etDiary.text}")
        }

        binding.etDiary.addTextChangedListener {
            LogT.d("TextChanged :: $it")
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }
    }

}