package com.williams.vaughan.charlie.capitalcalcremastered.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.robinhood.spark.SparkView.OnScrubListener
import com.williams.vaughan.charlie.capitalcalcremastered.MySparkAdapter
import com.williams.vaughan.charlie.capitalcalcremastered.R
import com.williams.vaughan.charlie.capitalcalcremastered.databinding.FragmentResultBinding
import com.williams.vaughan.charlie.capitalcalcremastered.extensions.observeEvent
import com.williams.vaughan.charlie.capitalcalcremastered.viewmodels.ResultViewModel
import com.williams.vaughan.charlie.capitalcalcremastered.viewstates.ResultNavigationEffect.NavigateToCalculatorEffect
import com.williams.vaughan.charlie.capitalcalcremastered.viewstates.ResultViewEvent.ReturnPressedEvent
import com.williams.vaughan.charlie.capitalcalcremastered.viewstates.ResultViewEvent.ScreenLoadEvent
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
        setupChart()
    }

    private fun setupViewState() {
        arguments?.let {
            val safeArgs = ResultFragmentArgs.fromBundle(it)
            viewModel.viewState().observe(viewLifecycleOwner, Observer {
                binding.splashButton.setOnClickListener { viewModel.onEvent(ReturnPressedEvent) }
                binding.totalTextView.text = getString(R.string.scrub_format, safeArgs.amount)
                binding.totalTimePeriodTextView.text =
                    getString(R.string.time_period_format, safeArgs.calculationPeriod)
                binding.sparkView.adapter = MySparkAdapter(safeArgs.chartData)
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

    private fun setupChart() {
        binding.sparkView.scrubListener = OnScrubListener {
            when (it == null) {
                true -> binding.scrubText.text = ""
                false -> binding.scrubText.text = getString(R.string.scrub_format, it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}