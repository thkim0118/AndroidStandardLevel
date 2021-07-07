package com.terry.bmi

import android.os.Bundle
import com.terry.bmi.databinding.ActivityResultBinding
import com.terry.common.base.BaseActivity
import kotlin.math.pow

/*
 * Created by Taehyung Kim on 2021-06-26
 */
class ResultActivity : BaseActivity<ActivityResultBinding>(ActivityResultBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        val bmi = weight / (height / 100.0).pow(2.0)

        val resultText = when {
            bmi > 35.0 -> "고도 비만"
            bmi > 30.0 -> "중경도 비만"
            bmi > 25.0 -> "경도 비만"
            bmi > 23.0 -> "과체중"
            bmi > 18.5 -> "정상체중"
            else -> "저체중"
        }

        binding.tvBmiResult.text = bmi.toString()
        binding.tvBmiResultText.text = resultText
    }
}