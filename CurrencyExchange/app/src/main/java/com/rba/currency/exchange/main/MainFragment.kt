package com.rba.currency.exchange.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.rba.currency.exchange.databinding.FragmentMainBinding
import com.rba.currency.exchange.util.Constant
import com.rba.currency.exchangeview.SwapListener

class MainFragment : Fragment(), SwapListener {

    private var binding: FragmentMainBinding? = null
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(requireActivity().application)).get(
            MainViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        mainViewModel.getExchange(Constant.ORIGIN_CURRENCY, Constant.DESTINATION_CURRENCY)

        mainViewModel.apiError.observe(viewLifecycleOwner, { error ->
            error.getContentIfNotHandled()?.let { model ->
                binding?.containerLayout?.let {
                    Snackbar.make(it, model.message, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        })

        mainViewModel.error.observe(viewLifecycleOwner, { error ->
            error.getContentIfNotHandled()?.let { message ->
                binding?.containerLayout?.let {
                    Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        })

        mainViewModel.data.observe(viewLifecycleOwner, { data ->
            data.getContentIfNotHandled()?.let {
                Log.i("z- data", it.toString())
                binding?.exchangeView?.model = it
            }
        })

        mainViewModel.buySellValue.observe(viewLifecycleOwner, { value ->
            value.getContentIfNotHandled()?.let {
                binding?.buySellTextView?.text = it
            }
        })

        binding?.exchangeView?.listener = this

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onSwapClickListener(origin: String, destination: String) {
        mainViewModel.getExchange(origin, destination)
    }

}