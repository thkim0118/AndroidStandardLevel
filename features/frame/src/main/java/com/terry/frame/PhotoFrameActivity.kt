package com.terry.frame

import android.net.Uri
import android.os.Bundle
import com.terry.common.base.BaseActivity
import com.terry.frame.databinding.ActivityPhotoFrameBinding
import kotlin.concurrent.timer

class PhotoFrameActivity :
    BaseActivity<ActivityPhotoFrameBinding>(ActivityPhotoFrameBinding::inflate) {

    private val photoList = mutableListOf<Uri>()

    private var currentPosition = 0

    private val photoImageView by lazy { binding.photoImageView }

    private val backgroundPhotoImageView by lazy { binding.backgroundPhotoImageView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPhotoUriFromIntent()

        startTimer()
    }

    private fun getPhotoUriFromIntent() {
        val size = intent.getIntExtra("photoListSize", 0)
        for (i in 0..size) {
            intent.getStringExtra("photo$i")?.let {
                photoList.add(Uri.parse(it))
            }
        }
    }

    private fun startTimer() {
        timer(period = 5 * 1000) {
            runOnUiThread {
                val current = currentPosition
                val next = if (photoList.size <= currentPosition + 1) 0 else current + 1

                backgroundPhotoImageView.setImageURI(photoList[current])

                photoImageView.alpha = 0f
                photoImageView.setImageURI(photoList[next])
                photoImageView.animate()
                    .alpha(1.0f)
                    .setDuration(1000)
                    .start()

                currentPosition = next
            }
        }
    }
}