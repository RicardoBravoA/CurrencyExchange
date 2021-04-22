package com.rba.currency.exchange.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rba.currency.exchange.common.SharedViewModel
import com.rba.currency.exchange.databinding.FragmentMainBinding
import com.rba.currency.exchange.util.Constant
import com.rba.currency.exchangeview.listener.ItemListener
import com.rba.currency.exchangeview.listener.SwapListener
import com.rba.currency.exchangeview.model.ChangeModel

class MainFragment : Fragment(), SwapListener, ItemListener {

    private var binding: FragmentMainBinding? = null
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(requireActivity().application)).get(
            MainViewModel::class.java
        )
    }
    private val sharedViewModel: SharedViewModel by activityViewModels()

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

        sharedViewModel.update.observe(viewLifecycleOwner, {
            Log.i("z- changeModel", it.toString())
            mainViewModel.getExchange(it.origin, it.destination)
        })

        binding?.exchangeView?.swapListener = this
        binding?.exchangeView?.itemListener = this

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onSwapClickListener(origin: String, destination: String) {
        mainViewModel.getExchange(origin, destination)
    }

    override fun onPressed(model: ChangeModel) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToCountryFragment(
                model
            )
        )
    }

}