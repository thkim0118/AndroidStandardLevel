package com.terry.tinder

import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.terry.common.base.BaseActivity
import com.terry.tinder.databinding.ActivityTinderMainBinding

/*
 * Created by Taehyung Kim on 2021-07-18
 */
class TinderMainActivity :
    BaseActivity<ActivityTinderMainBinding>(ActivityTinderMainBinding::inflate) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, LikeActivity::class.java))
            finish()
        }
    }

}