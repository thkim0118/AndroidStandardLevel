package com.terry.lotto

import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.terry.common.LogT
import com.terry.common.base.BaseActivity
import com.terry.lotto.databinding.ActivityLottoMainBinding

class LottoMainActivity :
    BaseActivity<ActivityLottoMainBinding>(ActivityLottoMainBinding::inflate) {

    private val numberTextViewList: List<TextView> by lazy {
        with(binding) {
            listOf(
                tvFirstNumber,
                tvSecondNumber,
                tvThirdNumber,
                tvFourthNumber,
                tvFifthNumber,
                tvSixthNumber
            )
        }
    }

    private var didRun = false

    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNumberPicker()

        initRunButton()

        initAddButton()

        initClearButton()
    }

    private fun initNumberPicker() {
        with(binding.numberPicker) {
            minValue = 1
            maxValue = 45
        }
    }

    private fun initRunButton() {
        binding.btRun.setOnClickListener {
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed { index, number ->
                val textView = numberTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number, textView)
            }

            LogT.d(list.toString())
        }
    }

    private fun setNumberBackground(number: Int, textView: TextView) {
        when (number) {
            in 1..10 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_yellow)
            in 11..20 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_blue)
            in 21..30 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 31..40 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_gray)
            else -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_green)
        }
    }

    private fun initClearButton() {
        with(binding) {
            btClear.setOnClickListener {
                pickNumberSet.clear()

                numberTextViewList.forEach { it.isVisible = false }

                didRun = false
            }
        }
    }

    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for (i in 1..45) {
                if (pickNumberSet.contains(i)) continue

                this.add(i)
            }
        }

        numberList.shuffle()

        return (pickNumberSet.toList() + numberList.subList(0, 6 - pickNumberSet.size)).sorted()
    }

    private fun initAddButton() {
        binding.btAdding.setOnClickListener {
            if (didRun) {
                Snackbar.make(binding.root, "초기화 후 시도해주세요.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickNumberSet.size >= 5) {
                Snackbar.make(binding.root, "번호는 5개까지만 선택할 수 있습니다.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickNumberSet.contains(binding.numberPicker.value)) {
                Snackbar.make(binding.root, "이미 선택한 번호입니다.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text = binding.numberPicker.value.toString()

            setNumberBackground(binding.numberPicker.value, textView)

            pickNumberSet.add(binding.numberPicker.value)
        }
    }
}