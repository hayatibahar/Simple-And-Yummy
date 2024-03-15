package com.hayatibahar.simpleandyummy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hayatibahar.simpleandyummy.core.common.hideKeyboard
import com.hayatibahar.simpleandyummy.core.common.makeGone
import com.hayatibahar.simpleandyummy.core.common.makeVisible
import com.hayatibahar.simpleandyummy.core.common.showToast
import com.hayatibahar.simpleandyummy.databinding.FragmentHomeBinding
import com.hayatibahar.simpleandyummy.ui.home.adapter.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<HomeViewModel>()
    private val adapter = RecipesAdapter().apply {
        setOnRecipeItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeData()
        handleSearch()
        swipeRefreshListener()
        handleFilter()
    }

    private fun swipeRefreshListener() {
        binding.swipeRefresh.setOnRefreshListener {
            binding.searchBox.text?.clear()
            viewModel.getAllRecipes()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun handleFilter() {
        binding.filterBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFilterFragment()
            findNavController().navigate(action)
        }

    }

    private fun handleSearch() {
        binding.searchBox.setOnEditorActionListener { textView, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getRecipesWithSearch(textView.text.toString())
                clearSearchAndHideKeyboard()
            }
            true
        }

        binding.searchContainer.setEndIconOnClickListener {
            binding.searchBox.text?.clear()
            clearSearchAndHideKeyboard()
        }

    }

    private fun clearSearchAndHideKeyboard() {
        binding.searchBox.hideKeyboard(requireContext())
        binding.searchBox.clearFocus()
    }

    private fun observeData() {
        viewModel.homeUiState.observe(viewLifecycleOwner) {
            when (it) {
                is HomeUiState.Error -> {
                    handleError(it.errorMessage)
                }

                is HomeUiState.Loading -> {
                    handleLoading()
                }

                is HomeUiState.Success -> {
                    handleSuccess(it.data)
                }
            }
        }
    }

    private fun handleError(errorMessage: String) {
        binding.progressBar.makeGone()
        showToast(requireContext(), errorMessage)
    }

    private fun handleLoading() {
        binding.progressBar.makeVisible()
    }

    private fun handleSuccess(homeData: HomeData) {
        homeData.scrollIfNeeded(binding.recipeRv)
        homeData.showToastIfNeeded(requireContext())
        adapter.updateRecipes(homeData.recipes)
        binding.progressBar.makeGone()
    }

    private fun initAdapter() {
        binding.recipeRv.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}