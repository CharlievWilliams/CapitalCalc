package com.williams.vaughan.charlie.capitalcalc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.williams.vaughan.charlie.capitalcalc.R
import com.williams.vaughan.charlie.capitalcalc.databinding.FragmentCalculatorBinding
import com.williams.vaughan.charlie.capitalcalc.extensions.observeEvent
import com.williams.vaughan.charlie.capitalcalc.usecases.UseCaseResults
import com.williams.vaughan.charlie.capitalcalc.viewmodels.CalculatorViewModel
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorNavigationEffect.NavigateToResultEffect
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEffect.ShowToastEffect
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEvent.CalculateButtonPressed
import com.williams.vaughan.charlie.capitalcalc.viewstates.CalculatorViewEvent.ScreenLoadEvent
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
        setupViewState()
        setupViewEvents()
        setupViewEffects()
        setupNavigationEffects()
    }

    private fun setupViewState() {
        viewModel.viewState().observe(viewLifecycleOwner, Observer {
            binding.calculateButton.setOnClickListener {
                viewModel.onEvent(
                    CalculateButtonPressed(
                        binding.principalAmountEditText.text.toString(),
                        binding.annualInterestRateEditText.text.toString(),
                        binding.calculationPeriodEditText.text.toString(),
                        binding.radioGroup.checkedRadioButtonId
                    )
                )
            }
        })
    }

    private fun setupViewEvents() {
        viewModel.onEvent(ScreenLoadEvent)
    }

    private fun setupViewEffects() {
        viewModel.getViewEffect().observeEvent(viewLifecycleOwner, Observer {
            when (it) {
                is ShowToastEffect -> showToast()
            }
        })
    }

    private fun showToast() {
        Toast.makeText(context, getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
    }

    private fun setupNavigationEffects() {
        viewModel.getNavigationEffect().observeEvent(viewLifecycleOwner, Observer {
            when (it) {
                is NavigateToResultEffect -> navigateToResult(it.useCaseResults)
            }
        })
    }

    private fun navigateToResult(useCaseResults: UseCaseResults) {
        with(useCaseResults) {
            findNavController().navigate(
                R.id.action_calculatorFragment_to_resultFragment,
                ResultFragmentArgs(totalAmount, timePeriod, chartData).toBundle()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}