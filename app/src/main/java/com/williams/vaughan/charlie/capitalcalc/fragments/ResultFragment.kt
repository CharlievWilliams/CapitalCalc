package com.williams.vaughan.charlie.capitalcalc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.williams.vaughan.charlie.capitalcalc.databinding.FragmentResultBinding
import com.williams.vaughan.charlie.capitalcalc.viewmodels.ResultViewModel
import com.williams.vaughan.charlie.capitalcalc.viewstates.ResultViewEvent.ScreenLoadEvent

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ResultViewModel by viewModels()

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
        setupViewState()
        setupViewEvents()
    }

    private fun setupViewState() {
        viewModel.viewState().observe(viewLifecycleOwner, Observer {

        })
    }

    private fun setupViewEvents() {
        viewModel.onEvent(ScreenLoadEvent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}