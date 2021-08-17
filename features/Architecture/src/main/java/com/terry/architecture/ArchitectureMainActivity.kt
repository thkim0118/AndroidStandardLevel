package com.terry.architecture

import android.content.Intent
import android.os.Bundle
import com.terry.architecture.databinding.ActivityArchitectureMainBinding
import com.terry.architecture.mvc.MvcMainActivity
import com.terry.architecture.mvp.MvpMainActivity
import com.terry.architecture.mvvm.MvvmMainActivity
import com.terry.common.base.BaseActivity

class ArchitectureMainActivity :
    BaseActivity<ActivityArchitectureMainBinding>(ActivityArchitectureMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViews()
    }

    private fun bindViews() {
        binding.mvcButton.setOnClickListener {
            startActivity(Intent(this, MvcMainActivity::class.java))
        }

        binding.mvpButton.setOnClickListener {
            startActivity(Intent(this, MvpMainActivity::class.java))
        }

        binding.mvvmButton.setOnClickListener {
            startActivity(Intent(this, MvvmMainActivity::class.java))
        }
    }
}