package com.terry.tinder

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.terry.common.base.BaseActivity
import com.terry.tinder.databinding.ActivityLoginBinding

/*
 * Created by Taehyung Kim on 2021-07-18
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager

    private val emailEditText by lazy { binding.emailEditText }
    private val passwordEditText by lazy { binding.passwordEditText }
    private val loginButton by lazy { binding.loginButton }
    private val signUpButton by lazy { binding.signUpButton }
    private val facebookLoginButton by lazy { binding.facebookLoginButton }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create()

        initButtonClickListener()
        initEmailAndPasswordEditText()
        initFacebookLoginButton()
    }

    private fun initButtonClickListener() {
        loginButton.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        handleSuccessLogin()
                    } else {
                        Snackbar.make(binding.root, "로그인에 실패하였습니다.", Snackbar.LENGTH_SHORT).show()
                    }
                }
        }

        signUpButton.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Snackbar.make(binding.root, "회원가입 성공 !", Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(binding.root, "회원가입 실패...", Snackbar.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun initEmailAndPasswordEditText() {
        emailEditText.addTextChangedListener {
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }

        passwordEditText.addTextChangedListener {
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }
    }

    private fun initFacebookLoginButton() {
        facebookLoginButton.setPermissions("email", "public_profile")
        facebookLoginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    val credential = FacebookAuthProvider.getCredential(result.accessToken.token)
                    auth.signInWithCredential(credential)
                        .addOnCompleteListener(this@LoginActivity) { task ->
                            if (task.isSuccessful) {
                                handleSuccessLogin()
                            } else {
                                Snackbar
                                    .make(binding.root, "페이스북 로그인 실패...", Snackbar.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }

                override fun onCancel() {}

                override fun onError(error: FacebookException?) {
                    Snackbar.make(binding.root, "페이스북 로그인 실패...", Snackbar.LENGTH_SHORT).show()
                }

            })
    }

    private fun getInputEmail(): String {
        return emailEditText.text.toString()
    }

    private fun getInputPassword(): String {
        return passwordEditText.text.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleSuccessLogin() {
        if (auth.currentUser == null) {
            Snackbar.make(binding.root, "로그인 실패...", Snackbar.LENGTH_SHORT).show()
            return
        }

        val userId = auth.currentUser?.uid.orEmpty()
        val currentUserDB = Firebase.database.reference.child("Users").child(userId)
        val user = mutableMapOf<String, Any>()
        user[DBKey.USER_ID] = userId
        currentUserDB.updateChildren(user)

        finish()
    }
}