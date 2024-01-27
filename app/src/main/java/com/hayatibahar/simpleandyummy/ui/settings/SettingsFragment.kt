package com.hayatibahar.simpleandyummy.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hayatibahar.simpleandyummy.databinding.FragmentSettingsBinding
import com.hayatibahar.simpleandyummy.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        getDarkMode()
    }

    private fun getDarkMode() {
        viewModel.isDarkMode.observe(viewLifecycleOwner) {
            binding.themeSwitch.isChecked = it
        }
    }

    private fun initListeners() {
        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveDarkModeState(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}