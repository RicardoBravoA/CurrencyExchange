package com.rba.currency.exchangeview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.rba.currency.exchangeview.databinding.LayoutExchangeViewBinding
import com.rba.currency.exchangeview.model.ExchangeViewModel
import com.rba.currency.exchangeview.util.rotate
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
    }

}