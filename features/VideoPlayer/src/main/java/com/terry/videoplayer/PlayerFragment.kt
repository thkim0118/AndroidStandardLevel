package com.terry.videoplayer

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.terry.common.base.BaseFragment
import com.terry.videoplayer.databinding.FragmentPlayerBinding
import kotlin.math.abs

/*
 * Created by Taehyung Kim on 2021-07-26
 */
class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playerMotionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                if (activity is VideoMainActivity) {
                    activity.also { mainActivity ->
                        mainActivity?.findViewById<MotionLayout>(R.id.mainMotionLayout)?.progress = abs(progress)
                    }
                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {}

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}

        })
    }

    override fun onDestroy() {
        super.onDestroy()

//        binding = null
    }
}