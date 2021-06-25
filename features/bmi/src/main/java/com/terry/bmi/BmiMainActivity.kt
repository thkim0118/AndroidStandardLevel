package com.terry.bmi

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.terry.bmi.databinding.ActivityBmiMainBinding
import com.terry.common.base.BaseActivity

class BmiMainActivity : BaseActivity<ActivityBmiMainBinding>(ActivityBmiMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btCheck.setOnClickListener {
            val height = binding.etHeight.text.toString()
            val weight = binding.etWeight.text.toString()

            if (height.isEmpty() || weight.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "빈 값이 있습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }
        }
    }
}