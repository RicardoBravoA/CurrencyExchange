package com.rba.currency.exchange.country

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.rba.currency.domain.model.CountryModel
import com.rba.currency.exchange.common.SharedViewModel
import com.rba.currency.exchange.databinding.FragmentCountryBinding
import com.rba.currency.exchange.main.MainViewModel
import com.rba.currency.exchange.main.MainViewModelFactory
import com.rba.currency.exchangeview.model.ChangeModel

class CountryFragment : Fragment() {

    private var binding: FragmentCountryBinding? = null
    private val adapter: CountryAdapter by lazy {
        CountryAdapter(::itemClick)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val countryViewModel: CountryViewModel by lazy {
        ViewModelProvider(this, CountryViewModelFactory(requireActivity().application)).get(
            CountryViewModel::class.java
        )
    }
    private val args by navArgs<CountryFragmentArgs>()
    private lateinit var changeModel: ChangeModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(layoutInflater)

        changeModel = args.model
        if (args.model.updateOrigin) {
            countryViewModel.get(args.model.origin)
        } else {
            countryViewModel.get(args.model.destination)
        }

        binding?.countryRecyclerView?.adapter = adapter

        countryViewModel.apiError.observe(viewLifecycleOwner, { error ->
            error.getContentIfNotHandled()?.let { model ->
                binding?.countryContainerLayout?.let {
                    Snackbar.make(it, model.message, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        })

        countryViewModel.error.observe(viewLifecycleOwner, { error ->
            error.getContentIfNotHandled()?.let { message ->
                binding?.countryContainerLayout?.let {
                    Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        })

        countryViewModel.data.observe(viewLifecycleOwner, { data ->
            data.getContentIfNotHandled()?.let {
                Log.i("z- data", it.toString())
                adapter.submitList(it)
            }
        })

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun itemClick(model: CountryModel) {
        if (changeModel.updateOrigin) {
            changeModel.origin = model.value
        } else {
            changeModel.destination = model.value
        }
        sharedViewModel.update(changeModel)

        findNavController().popBackStack()
    }

}