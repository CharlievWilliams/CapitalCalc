package com.williams.vaughan.charlie.capitalcalc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.williams.vaughan.charlie.capitalcalc.databinding.FragmentResultBinding
import com.williams.vaughan.charlie.capitalcalc.extensions.observeEvent
import com.williams.vaughan.charlie.capitalcalc.viewmodels.ResultViewModel
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultNavigationEffect.NavigateToCalculatorEffect
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewEvent.ReturnPressedEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewEvent.ScreenLoadEvent
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
        setupViewState()
        setupViewEvents()
        setupNavigationEffects()
    }

    private fun setupViewState() {
        arguments?.let {
            val safeArgs = ResultFragmentArgs.fromBundle(it)
            viewModel.viewState().observe(viewLifecycleOwner, Observer {
                binding.splashButton.setOnClickListener { viewModel.onEvent(ReturnPressedEvent) }
                binding.totalTextView.text = safeArgs.amount
                binding.totalTimePeriodTextView.text = safeArgs.calculationPeriod
            })
        }
    }

    private fun setupViewEvents() {
        viewModel.onEvent(ScreenLoadEvent)
    }

    private fun setupNavigationEffects() {
        viewModel.getNavigationEffect().observeEvent(viewLifecycleOwner, Observer {
            when (it) {
                is NavigateToCalculatorEffect -> findNavController().popBackStack()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}