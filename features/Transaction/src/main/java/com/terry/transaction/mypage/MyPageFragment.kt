package com.terry.transaction.mypage

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.terry.common.base.BaseFragment
import com.terry.transaction.databinding.FragmentMypageBinding

/*
 * Created by Taehyung Kim on 2021-07-20
 */
class MyPageFragment : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) {

    private val auth by lazy { Firebase.auth }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Snackbar.make(
                            binding.root,
                            "회원 가입에 성공했습니다. 로그인 버튼을 눌러주세요.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.root,
                            "회원 가입에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        binding.signInOutButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (auth.currentUser == null) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            successSignIn()
                        } else {
                            Snackbar.make(
                                binding.root,
                                "로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요.",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                auth.signOut()
                binding.emailEditText.text.clear()
                binding.emailEditText.isEnabled = true
                binding.passwordEditText.text.clear()
                binding.passwordEditText.isEnabled = true

                binding.signInOutButton.text = "로그인"
                binding.signInOutButton.isEnabled = false
                binding.signUpButton.isEnabled = false
            }
        }

        binding.emailEditText.addTextChangedListener {
            val enable =
                binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()
            binding.signInOutButton.isEnabled = enable
            binding.signUpButton.isEnabled = enable
        }

        binding.passwordEditText.addTextChangedListener {
            val enable =
                binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()
            binding.signInOutButton.isEnabled = enable
            binding.signUpButton.isEnabled = enable
        }

    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser == null) {
            binding.emailEditText.text.clear()
            binding.emailEditText.isEnabled = true
            binding.passwordEditText.text.clear()
            binding.passwordEditText.isEnabled = true

            binding.signInOutButton.text = "로그인"
            binding.signInOutButton.isEnabled = false
            binding.signUpButton.isEnabled = false
        } else {
            binding.emailEditText.setText(auth.currentUser?.email)
            binding.emailEditText.isEnabled = false
            binding.passwordEditText.setText("**********")
            binding.passwordEditText.isEnabled = false

            binding.signInOutButton.text = "로그아웃"
            binding.signInOutButton.isEnabled = true
            binding.signUpButton.isEnabled = false
        }
    }

    private fun successSignIn() {
        if (auth.currentUser == null) {
            Snackbar.make(binding.root, "로그인에 실패하였습니다. 다시 시도해주세요.", Snackbar.LENGTH_SHORT).show()
            return
        }

        binding.emailEditText.isEnabled = false
        binding.passwordEditText.isEnabled = false
        binding.signUpButton.isEnabled = false
        binding.signInOutButton.text = "로그아웃"
    }

}