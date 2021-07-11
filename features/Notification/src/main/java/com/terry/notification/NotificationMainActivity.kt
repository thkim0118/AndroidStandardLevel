package com.terry.notification

import android.os.Bundle
import com.google.firebase.messaging.FirebaseMessaging
import com.terry.common.base.BaseActivity
import com.terry.notification.databinding.ActivityNotificationMainBinding

class NotificationMainActivity :
    BaseActivity<ActivityNotificationMainBinding>(ActivityNotificationMainBinding::inflate) {

    private val resultTextView by lazy {
        binding.resultTextView
    }

    private val firebaseTokenTextView by lazy {
        binding.firebaseTokenTextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFirebase()
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseTokenTextView.text = task.result
                }
            }
    }
}