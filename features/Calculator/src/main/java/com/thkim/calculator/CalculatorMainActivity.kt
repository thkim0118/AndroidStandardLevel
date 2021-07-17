package com.thkim.calculator

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.terry.common.LogT
import com.terry.common.base.BaseActivity
import com.terry.common.di.UseCaseDependencies
import com.terry.local.model.History
import com.thkim.calculator.databinding.ActivityCalculatorMainBinding
import com.thkim.calculator.di.DaggerCalculatorComponent
import com.thkim.calculator.extension.isNumber
import com.thkim.calculator.viewmodel.CalculatorViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class CalculatorMainActivity :
    BaseActivity<ActivityCalculatorMainBinding>(ActivityCalculatorMainBinding::inflate) {

    private var isOperator = false
    private var hasOperator = false

    private val historyLayout: View by lazy { binding.layoutHistory }

    private val historyLinearLayout: LinearLayout by lazy { binding.linearLayoutHistory }

    @Inject
    lateinit var viewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)
    }

    private fun initCoreDependentInjection() {
        val useCaseDependencies = EntryPointAccessors.fromApplication(
            applicationContext,
            UseCaseDependencies::class.java
        )

        DaggerCalculatorComponent.factory().create(
            dependentModule = useCaseDependencies,
            activity = this
        ).inject(this)
    }

    fun buttonClicked(v: View) {
        when (v.id) {
            R.id.btNumber0 -> numberButtonClicked("0")
            R.id.btNumber1 -> numberButtonClicked("1")
            R.id.btNumber2 -> numberButtonClicked("2")
            R.id.btNumber3 -> numberButtonClicked("3")
            R.id.btNumber4 -> numberButtonClicked("4")
            R.id.btNumber5 -> numberButtonClicked("5")
            R.id.btNumber6 -> numberButtonClicked("6")
            R.id.btNumber7 -> numberButtonClicked("7")
            R.id.btNumber8 -> numberButtonClicked("8")
            R.id.btNumber9 -> numberButtonClicked("9")
            R.id.btPlus -> operatorButtonClicked("+")
            R.id.btMinus -> operatorButtonClicked("-")
            R.id.btMulti -> operatorButtonClicked("*")
            R.id.btDivider -> operatorButtonClicked("/")
            R.id.btModulo -> operatorButtonClicked("%")
        }
    }

    private fun numberButtonClicked(number: String) {
        if (isOperator) binding.tvExpression.append(" ")

        isOperator = false

        val expressionText = binding.tvExpression.text.split(" ")

        if (expressionText.isNotEmpty() && expressionText.last().length >= 15) {
            Snackbar.make(binding.root, "15자리 까지만 사용할 수 있습니다.", Snackbar.LENGTH_SHORT).show()
            return
        } else if (expressionText.last().isEmpty() && number == "0") {
            Snackbar.make(binding.root, "0은 제일 앞에 올 수 없습니다.", Snackbar.LENGTH_SHORT).show()
            return
        }

        binding.tvExpression.append((number))

        binding.tvResult.text = calculateExpression()
    }

    private fun operatorButtonClicked(operator: String) {
        if (binding.tvExpression.text.isEmpty()) return

        when {
            isOperator -> {
                val text = binding.tvExpression.text.toString()
                binding.tvExpression.text = text.dropLast(1) + operator
            }

            hasOperator -> {
                Snackbar.make(binding.root, "연산자는 한 번만 사용할 수 있습니다.", Snackbar.LENGTH_SHORT).show()
                return
            }

            else -> binding.tvExpression.append(" $operator")
        }

        val ssb = SpannableStringBuilder(binding.tvExpression.text)
        ssb.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.buttonGreen)),
            binding.tvExpression.text.length - 1,
            binding.tvExpression.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.tvExpression.text = ssb

        isOperator = true
        hasOperator = true
    }

    fun resultButtonClicked(v: View) {
        val expressionTexts = binding.tvExpression.text.split(" ")

        if (binding.tvExpression.text.isEmpty() || expressionTexts.size == 1) return

        if (expressionTexts.size != 3 && hasOperator) {
            Snackbar.make(binding.root, "완성되지 않은 수식입니다.", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()) {
            Snackbar.make(binding.root, "오류가 발생했습니다.", Snackbar.LENGTH_SHORT).show()
            return
        }

        val expressionText = binding.tvExpression.text.toString()
        val resultText = calculateExpression()

        Thread {
            LogT.start()
            insertHistoryData(History(null, expressionText, resultText))
        }.start()

        binding.tvResult.text = ""
        binding.tvExpression.text = resultText

        isOperator = false
        hasOperator = false
    }

    private fun insertHistoryData(history: History) = viewModel.insertHistory(history)

    private fun calculateExpression(): String {
        val expressionTexts = binding.tvExpression.text.split(" ")

        if (hasOperator.not() || expressionTexts.size != 3) {
            return ""
        } else if (expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()) {
            return ""
        }

        val exp1 = expressionTexts[0].toBigInteger()
        val exp2 = expressionTexts[2].toBigInteger()
        val op = expressionTexts[1]

        return when (op) {
            "+" -> (exp1 + exp2).toString()
            "-" -> (exp1 - exp2).toString()
            "*" -> (exp1 * exp2).toString()
            "/" -> (exp1 / exp2).toString()
            "%" -> (exp1 % exp2).toString()
            else -> ""
        }
    }

    fun clearButtonClicked(v: View) {
        binding.tvExpression.text = ""
        binding.tvResult.text = ""
        isOperator = false
        hasOperator = false
    }

    fun historyButtonClicked(v: View) {
        LogT.start()

        historyLayout.isVisible = true
        historyLinearLayout.removeAllViews()

        getAllHistoryData()
    }

    private fun getAllHistoryData() {
        LogT.start()
        viewModel.getAllHistory.observe(this) { historyList ->
            historyList.reversed().forEach {
                val historyView =
                    LayoutInflater.from(this).inflate(R.layout.history_row, null, false)
                historyView.findViewById<TextView>(R.id.tvExpression).text = it.expression
                historyView.findViewById<TextView>(R.id.tvResult).text = "= ${it.result}"

                LogT.d("${it.expression}, ${it.result}")

                historyLinearLayout.addView(historyView)
            }
        }
    }

    fun closeHistoryButtonClicked(v: View) {
        historyLayout.isVisible = false
    }

    fun historyClearButtonClicked(v: View) {
        historyLinearLayout.removeAllViews()

        Thread {
            deleteAllHistoryData()
        }.start()
    }

    private fun deleteAllHistoryData() = viewModel.deleteAllHistory()
}