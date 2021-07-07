package com.terry.pomodoro

import android.annotation.SuppressLint
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.TextView
import com.terry.common.base.BaseActivity
import com.terry.pomodoro.databinding.ActivityPomodoroMainBinding

class PomodoroMainActivity :
    BaseActivity<ActivityPomodoroMainBinding>(ActivityPomodoroMainBinding::inflate) {

    private val remainMinutesTextView: TextView by lazy {
        binding.remainMinutesTextView
    }

    private val remainSecondsTextView: TextView by lazy {
        binding.remainSecondsTextView
    }

    private val seekBar: SeekBar by lazy {
        binding.seekBar
    }

    private val soundPool: SoundPool = SoundPool.Builder().build()

    private var currentCountDownTimer: CountDownTimer? = null
    private var tickingSoundId: Int? = null
    private var bellSoundId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViews()

        initSounds()
    }

    override fun onResume() {
        super.onResume()
        soundPool.autoResume()
    }

    override fun onPause() {
        super.onPause()
        soundPool.autoPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

    private fun bindViews() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateRemainingTime(progress * 60 * 1000L)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                stopCountDown()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar ?: return

                if (seekBar.progress == 0) {
                    stopCountDown()
                } else {
                    startCountDown()
                }
            }

        })
    }

    private fun startCountDown() {
        currentCountDownTimer = createCountDownTimer(seekBar.progress * 60 * 1000L)
        currentCountDownTimer?.start()

        tickingSoundId?.let { soundId ->
            soundPool.play(soundId, 1F, 1F, 0, -1, 1F)
        }
    }

    private fun stopCountDown() {
        currentCountDownTimer?.cancel()
        currentCountDownTimer = null
        soundPool.autoPause()
    }

    private fun createCountDownTimer(initialMills: Long) =
        object : CountDownTimer(initialMills, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateRemainingTime(millisUntilFinished)
                updateSeekBar(millisUntilFinished)
            }

            override fun onFinish() {
                completeCountDown()
            }
        }

    private fun completeCountDown() {
        updateRemainingTime(0)
        updateSeekBar(0)

        soundPool.autoPause()
        bellSoundId?.let { soundId ->
            soundPool.play(soundId, 1F, 1F, 0, 0, 1F)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateRemainingTime(remainMills: Long) {
        val remainSeconds = remainMills / 1000

        remainMinutesTextView.text = "%02d'".format(remainSeconds / 60)
        remainSecondsTextView.text = "%02d".format(remainSeconds % 60)
    }

    private fun updateSeekBar(remainMills: Long) {
        seekBar.progress = (remainMills / 1000 / 60).toInt()
    }

    private fun initSounds() {
        tickingSoundId = soundPool.load(this, R.raw.timer_ticking, 1)
        bellSoundId = soundPool.load(this, R.raw.timer_bell, 1)
    }
}