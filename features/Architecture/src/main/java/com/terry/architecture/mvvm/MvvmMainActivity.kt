package com.terry.architecture.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.terry.architecture.databinding.ActivityCommonMainBinding

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class MvvmMainActivity : AppCompatActivity() {

    private var binding: ActivityCommonMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonMainBinding.inflate(layoutInflater)

        binding?.let { binding ->
            setContentView(binding.root)
        }
    }

}