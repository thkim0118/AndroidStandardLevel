package com.terry.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/*
 * Created by Taehyung Kim on 2021-06-26
 */
abstract class BaseFragment<B : ViewBinding>(
    private val inflate: FragmentInflater<B>
) : Fragment() {

    protected lateinit var binding: B
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate.invoke(inflater, container, false)

        return binding.root
    }
}