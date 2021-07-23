package com.terry.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.terry.common.base.BaseActivity
import com.terry.transaction.chatlist.ChatListFragment
import com.terry.transaction.databinding.ActivityTransactionMainBinding
import com.terry.transaction.home.HomeFragment
import com.terry.transaction.mypage.MyPageFragment

class TransactionMainActivity :
    BaseActivity<ActivityTransactionMainBinding>(ActivityTransactionMainBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeFragment = HomeFragment()
        val chatListFragment = ChatListFragment()
        val myPageFragment = MyPageFragment()

        val bottomNavigationView = binding.bottomNavigationView

        replaceFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.chatList -> replaceFragment(chatListFragment)
                R.id.myPage -> replaceFragment(myPageFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }

    }
}