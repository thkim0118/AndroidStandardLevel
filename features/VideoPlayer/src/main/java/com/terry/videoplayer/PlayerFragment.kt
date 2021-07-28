package com.terry.videoplayer

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.terry.common.base.BaseFragment
import com.terry.common.di.UseCaseDependencies
import com.terry.videoplayer.adapter.VideoAdapter
import com.terry.videoplayer.databinding.FragmentPlayerBinding
import com.terry.videoplayer.di.DaggerVideoComponent
import com.terry.videoplayer.viewmodel.VideoViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject
import kotlin.math.abs

/*
 * Created by Taehyung Kim on 2021-07-26
 */
class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    private lateinit var videoAdapter: VideoAdapter

    private var player: SimpleExoPlayer? = null

    @Inject
    lateinit var viewModel: VideoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onViewCreated(view, savedInstanceState)

        initMotionLayoutEvent()
        initRecyclerView()
        initPlayer()
        initControlButton()

        viewModel.getVideoList()

        observeLiveData()
    }

    override fun onStop() {
        super.onStop()

        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()

        player?.release()
//        binding = null
    }

    private fun initCoreDependentInjection() {
        val useCaseDependencies = EntryPointAccessors.fromApplication(
            requireContext().applicationContext,
            UseCaseDependencies::class.java
        )

        DaggerVideoComponent.factory().create(
            dependentModule = useCaseDependencies,
            activity = requireActivity()
        ).inject(this)
    }

    private fun initMotionLayoutEvent() {
        binding.playerMotionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                if (activity is VideoMainActivity) {
                    activity.also { mainActivity ->
                        mainActivity?.findViewById<MotionLayout>(R.id.mainMotionLayout)?.progress =
                            abs(progress)
                    }
                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {}

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}

        })
    }

    private fun initRecyclerView() {
        videoAdapter = VideoAdapter(callback = { url, title ->
            playVideoWithUrl(url, title)
        })

        binding.recyclerView.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initPlayer() {
        context?.let {
            player = SimpleExoPlayer.Builder(it).build()
        }

        binding.playerView.player = player
        player?.addListener(object : Player.EventListener {// TODO : Listener 변경(Deprecated)
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)

                if (isPlaying) {
                    binding.bottomPlayerControlButton.setImageResource(R.drawable.ic_pause)
                } else {
                    binding.bottomPlayerControlButton.setImageResource(R.drawable.ic_play_arrow)
                }
            }
        })
    }

    private fun initControlButton() {
        binding.bottomPlayerControlButton.setOnClickListener {
            val player = player ?: return@setOnClickListener

            if (player.isPlaying) {
                player.pause()
            } else {
                player.play()
            }
        }
    }

    private fun observeLiveData() {
        observeVideoList()
    }

    private fun observeVideoList() {
        viewModel.videoList.observe(viewLifecycleOwner) { videoDto ->
            videoAdapter.submitList(videoDto.videos)
            videoAdapter.notifyDataSetChanged()
        }
    }

    fun playVideoWithUrl(url: String, title: String) {
        context?.let {
            val dataSourceFactory = DefaultDataSourceFactory(it)
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
            player?.setMediaSource(mediaSource)
            player?.prepare()
            player?.play()
        }

        binding.playerMotionLayout.transitionToEnd()
        binding.bottomTitleTextView.text = title
    }
}