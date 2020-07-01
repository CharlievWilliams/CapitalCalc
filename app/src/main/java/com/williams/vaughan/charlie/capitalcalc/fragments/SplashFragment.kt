package com.williams.vaughan.charlie.capitalcalc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.williams.vaughan.charlie.capitalcalc.R
import com.williams.vaughan.charlie.capitalcalc.databinding.FragmentSplashBinding
import com.williams.vaughan.charlie.capitalcalc.extensions.observeEvent
import com.williams.vaughan.charlie.capitalcalc.viewmodels.SplashViewModel
import com.williams.vaughan.charlie.capitalcalc.viewstates.SplashNavigationEffect.NavigateToCalculatorEffect
import com.williams.vaughan.charlie.capitalcalc.viewstates.SplashViewEvent.ScreenLoadEvent
import com.williams.vaughan.charlie.capitalcalc.viewstates.SplashViewEvent.SplashButtonPressedEvent
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
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
        viewModel.viewState().observe(viewLifecycleOwner, Observer {
            binding.splashButton.setOnClickListener { viewModel.onEvent(SplashButtonPressedEvent) }
        })
    }

    private fun setupViewEvents() {
        viewModel.onEvent(ScreenLoadEvent)
    }

    private fun setupNavigationEffects() {
        viewModel.getNavigationEffect().observeEvent(viewLifecycleOwner, Observer {
            when (it) {
                is NavigateToCalculatorEffect -> navigateToCalculator()
            }
        })
    }

    private fun navigateToCalculator() {
        findNavController().navigate(R.id.action_splashFragment_to_calculatorFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}