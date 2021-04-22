package com.rba.currency.exchangeview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.rba.currency.exchangeview.databinding.LayoutExchangeViewBinding
import com.rba.currency.exchangeview.model.ExchangeViewModel
import com.rba.currency.exchangeview.util.onTextChange
import com.rba.currency.exchangeview.util.rotate
import com.rba.currency.exchangeview.util.toMoney
import java.lang.Exception

class ExchangeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutExchangeViewBinding =
        LayoutExchangeViewBinding.inflate(LayoutInflater.from(context), this)

    var model: ExchangeViewModel? = null
        set(value) {
            field = value
            binding.originValueTextView.text = value?.originCurrencyName
            binding.destinationValueTextView.text = value?.destinationCurrencyName
            generateValue()
        }

    var listener: SwapListener? = null

    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        background = ContextCompat.getDrawable(context, R.drawable.shape_background)

        binding.changeImageView.setOnClickListener { view ->

            model?.let {
                val origin = it.destinationCurrencyValue
                val destination = it.originCurrencyValue
                listener?.onSwapClickListener(origin, destination)

            } ?: run { throw Exception("Need send parameter to model of type ExchangeViewModel") }

            view.rotate()
        }

        binding.originEditText.onTextChange {
            generateValue()
        }
    }

    private fun generateValue() {
        val originValue = binding.originEditText.text.toString()
        try {
            model?.let {
                val value: Float = if (it.multiply) {
                    originValue.toFloat() * it.buy.toFloat()
                } else {
                    originValue.toFloat() / it.buy.toFloat()
                }

                binding.destinationEditText.setText(value.toMoney())
            }

        } catch (exception: Exception) {
            binding.destinationEditText.setText("")
        }
    }

}