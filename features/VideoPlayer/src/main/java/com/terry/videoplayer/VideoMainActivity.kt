package com.terry.videoplayer

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.terry.common.base.BaseActivity
import com.terry.common.di.UseCaseDependencies
import com.terry.videoplayer.adapter.VideoAdapter
import com.terry.videoplayer.databinding.ActivityVideoMainBinding
import com.terry.videoplayer.di.DaggerVideoComponent
import com.terry.videoplayer.viewmodel.VideoViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class VideoMainActivity :
    BaseActivity<ActivityVideoMainBinding>(ActivityVideoMainBinding::inflate) {

    private lateinit var videoAdapter: VideoAdapter

    @Inject
    lateinit var viewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PlayerFragment())
            .commit()

        videoAdapter = VideoAdapter(callback = { url, title ->
            supportFragmentManager.fragments.find { it is PlayerFragment }?.let {
                (it as PlayerFragment).playVideoWithUrl(url, title)
            }
        })

        binding.mainRecyclerView.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.getVideoList()

        observeLiveData()
    }

    private fun initCoreDependentInjection() {
        val useCaseDependencies = EntryPointAccessors.fromApplication(
            applicationContext,
            UseCaseDependencies::class.java
        )

        DaggerVideoComponent.factory().create(
            dependentModule = useCaseDependencies,
            activity = this
        ).inject(this)
    }

    private fun observeLiveData() {
        observeVideoList()
    }

    private fun observeVideoList() {
        viewModel.videoList.observe(this) { videoDto ->
            videoAdapter.submitList(videoDto.videos)
            videoAdapter.notifyDataSetChanged()
        }
    }
}