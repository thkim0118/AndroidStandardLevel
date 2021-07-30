package com.terry.musicplayer

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.terry.common.LogT
import com.terry.common.base.BaseFragment
import com.terry.common.di.UseCaseDependencies
import com.terry.musicplayer.adapter.PlayListAdapter
import com.terry.musicplayer.databinding.FragmentPlayerBinding
import com.terry.musicplayer.di.DaggerMusicComponent
import com.terry.musicplayer.extensions.mapper
import com.terry.musicplayer.model.MusicModel
import com.terry.musicplayer.viewmodel.MusicViewModel
import dagger.hilt.android.EntryPointAccessors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-28
 */
class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    @Inject
    lateinit var viewModel: MusicViewModel

    private var model: PlayerModel = PlayerModel()
    private var player: SimpleExoPlayer? = null
    private lateinit var playListAdapter: PlayListAdapter

    private val updateSeekRunnable = Runnable {
        LogT.d("updateSeekRunnable")
        updateSeek()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onViewCreated(view, savedInstanceState)

        initPlayView()
        initPlayListButton()
        initSeekBar()
        initPlayControlButton()
        initRecyclerView()

        viewModel.getMusicList()

        observeLiveData()
    }

    private fun initCoreDependentInjection() {
        val useCaseDependencies = EntryPointAccessors.fromApplication(
            requireActivity().applicationContext,
            UseCaseDependencies::class.java
        )

        DaggerMusicComponent.factory().create(
            dependencies = useCaseDependencies,
            activity = requireActivity()
        ).inject(this)
    }

    private fun initPlayView() {
        context?.let {
            player = SimpleExoPlayer.Builder(it).build()
        }

        binding.playerView.player = player

        player?.addListener(object : Player.EventListener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)

                if (isPlaying) {
                    binding.playControlImageView.setImageResource(R.drawable.ic_pause)
                } else {
                    binding.playControlImageView.setImageResource(R.drawable.ic_play_arrow)
                }
            }

            override fun onPlaybackStateChanged(state: Int) {
                super.onPlaybackStateChanged(state)
                LogT.start()

                updateSeek()
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)

                val newIndex = mediaItem?.mediaId ?: return
                model.currentPosition = newIndex.toInt()
                updatePlayerView(model.currentMusicModel())

                playListAdapter.submitList(model.getAdapterModels())
            }
        })
    }

    private fun initPlayListButton() {
        binding.playlistImageView.setOnClickListener {
            if (model.currentPosition == -1) return@setOnClickListener

            binding.playerViewGroup.isVisible = model.isWatchingPlayListView
            binding.playerListGroup.isVisible = model.isWatchingPlayListView.not()

            model.isWatchingPlayListView = !model.isWatchingPlayListView
        }
    }

    private fun initSeekBar() {
        binding.playerSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                player?.seekTo((seekBar.progress * 1000).toLong())
            }

        })

        binding.playlistSeekbar.setOnTouchListener { v, event ->
            false
        }
    }

    private fun initPlayControlButton() {
        binding.playControlImageView.setOnClickListener {
            val player = this.player ?: return@setOnClickListener

            if (player.isPlaying) {
                player.pause()
            } else {
                player.play()
            }
        }

        binding.skipNextImageView.setOnClickListener {
            val nextMusic = model.nextMusic() ?: return@setOnClickListener
            playMusic(nextMusic)
        }

        binding.skipPrevImageView.setOnClickListener {
            val prevMusic = model.prevMusic() ?: return@setOnClickListener
            playMusic(prevMusic)
        }
    }

    private fun updateSeek() {
        LogT.start()

        val player = this.player ?: return
        val duration = if (player.duration >= 0) player.duration else 0
        val position = player.currentPosition

        updateSeekUi(duration, position)

        val state = player.playbackState

        view?.removeCallbacks(updateSeekRunnable)
        if (state != Player.STATE_IDLE && state != Player.STATE_ENDED) {
            LogT.d("postDelayed")
            view?.postDelayed(updateSeekRunnable, 1000)
        }

    }

    private fun updateSeekUi(duration: Long, position: Long) {
        binding.playlistSeekbar.max = (duration / 1000).toInt()
        binding.playlistSeekbar.progress = (position / 1000).toInt()

        binding.playerSeekBar.max = (duration / 1000).toInt()
        binding.playerSeekBar.progress = (position / 1000).toInt()

        binding.playTimeTextView.text = String.format(
            "%02d:%02d",
            TimeUnit.MINUTES.convert(position, TimeUnit.MILLISECONDS),
            (position / 1000) % 60
        )
        binding.totalTimeTextView.text = String.format(
            "%02d:%02d",
            TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS),
            (duration / 1000) % 60
        )
    }

    private fun updatePlayerView(currentMusicModel: MusicModel?) {
        currentMusicModel ?: return

        binding.trackTextView.text = currentMusicModel.track
        binding.artistTextView.text = currentMusicModel.artist
        Glide
            .with(binding.coverImageView.context)
            .load(currentMusicModel.coverUrl)
            .into(binding.coverImageView)
    }

    private fun initRecyclerView() {
        playListAdapter = PlayListAdapter {
            playMusic(it)
        }

        binding.playlistRecyclerView.apply {
            adapter = playListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeLiveData() {
        viewModel.musicList.observe(viewLifecycleOwner) { musicDto ->
            model = musicDto.mapper()

            setMusicList(model.getAdapterModels())
            playListAdapter.submitList(model.getAdapterModels())
        }
    }

    private fun setMusicList(modelList: List<MusicModel>) {
        context?.let {
            player?.addMediaItems(modelList.map { musicModel ->
                MediaItem.Builder()
                    .setUri(musicModel.streamUrl)
                    .setMediaId(musicModel.id.toString())
                    .build()
            })

            player?.prepare()
        }
    }

    private fun playMusic(musicModel: MusicModel) {
        model.updateCurrentPosition(musicModel)
        player?.seekTo(model.currentPosition, 0)
        player?.play()
    }

    override fun onStop() {
        super.onStop()
        player?.pause()
        view?.removeCallbacks(updateSeekRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        view?.removeCallbacks(updateSeekRunnable)
    }

    companion object {
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }

}