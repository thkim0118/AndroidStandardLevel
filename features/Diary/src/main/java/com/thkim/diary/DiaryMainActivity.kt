package com.thkim.diary

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.google.android.material.snackbar.Snackbar
import com.terry.common.base.BaseActivity
import com.thkim.diary.databinding.ActivityDiaryMainBinding

class DiaryMainActivity :
    BaseActivity<ActivityDiaryMainBinding>(ActivityDiaryMainBinding::inflate) {

    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNumberPicker()

        with(binding) {
            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)


            btOpen.setOnClickListener {
                if (changePasswordMode) {
                    Snackbar.make(root, "비밀번호 변경 중입니다.", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val passwordFromUser =
                    "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

                if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                    // 패스워드 성공
                    startActivity(Intent(this@DiaryMainActivity, DiaryActivity::class.java))
                } else {
                    showErrorAlertDialog()
                }
            }

            btChangePassword.setOnClickListener {
                val passwordFromUser =
                    "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

                if (changePasswordMode) {
                    passwordPreference.edit(commit = true) {
                        putString("password", passwordFromUser)
                    }

                    changePasswordMode = false
                    btChangePassword.setBackgroundColor(Color.BLACK)
                } else {
                    if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {

                        changePasswordMode = true
                        Snackbar.make(root, "변경할 패스워드를 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                        btChangePassword.setBackgroundColor(Color.RED)

                    } else {
                        showErrorAlertDialog()
                    }
                }
            }
        }
    }

    private fun showErrorAlertDialog() =
        AlertDialog.Builder(this@DiaryMainActivity)
            .setTitle("실패")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("확인") { _, _ -> }
            .create()
            .show()

    private fun initNumberPicker() {
        with(binding.numberPicker1) {
            minValue = 0
            maxValue = 9
        }

        with(binding.numberPicker2) {
            minValue = 0
            maxValue = 9
        }

        with(binding.numberPicker3) {
            minValue = 0
            maxValue = 9
        }
    }
}