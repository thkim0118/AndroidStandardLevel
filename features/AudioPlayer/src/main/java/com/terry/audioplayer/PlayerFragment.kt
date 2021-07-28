package com.terry.audioplayer

import android.os.Bundle
import android.view.View
import com.terry.audioplayer.databinding.FragmentPlayerBinding
import com.terry.common.base.BaseFragment

/*
 * Created by Taehyung Kim on 2021-07-28
 */
class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }

}