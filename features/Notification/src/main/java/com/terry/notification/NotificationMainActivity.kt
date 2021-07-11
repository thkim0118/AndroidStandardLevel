package com.terry.notification

import android.annotation.SuppressLint
import android.content.Intent
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
        updateResult()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        updateResult(true)
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseTokenTextView.text = task.result
                }
            }
    }

    @SuppressLint("SetTextI18n")
    private fun updateResult(isNewIntent: Boolean = false) {
        resultTextView.text = (intent.getStringExtra("notificationType") ?: "앱 런처") +
                if (isNewIntent) {
                    "(으)로 갱신했습니다."
                } else {
                    "(으)로 실행했습니다."
                }
    }
}