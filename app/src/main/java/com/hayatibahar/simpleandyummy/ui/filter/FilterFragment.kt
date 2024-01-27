package com.hayatibahar.simpleandyummy.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.hayatibahar.simpleandyummy.core.common.ApiConstants.cuisines
import com.hayatibahar.simpleandyummy.core.common.ApiConstants.diet
import com.hayatibahar.simpleandyummy.core.common.ApiConstants.mealType
import com.hayatibahar.simpleandyummy.databinding.FragmentFilterBinding
import com.hayatibahar.simpleandyummy.ui.filter.adapter.FilterAdapter
import com.hayatibahar.simpleandyummy.ui.filter.adapter.FilterItem
import com.hayatibahar.simpleandyummy.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<HomeViewModel>()
    private val filterLists =
        listOf(cuisines, diet, mealType).map { it -> it.map { FilterItem(it) } }
    private val adapters = filterLists.map { list ->
        FilterAdapter().apply {
            setOnFilterItemClickListener {
                updateFilters(changeSelectedItem(list, it))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        initListeners()
    }

    private fun changeSelectedItem(
        items: List<FilterItem>,
        filterItem: FilterItem,
    ): List<FilterItem> {
        return items.map {
            if (it.name == filterItem.name) {
                it.isSelected = !filterItem.isSelected
            } else {
                it.isSelected = false
            }
            it
        }
    }

    private fun initListeners() {
        binding.applyBtn.setOnClickListener {
            viewModel.getRecipesWithSearch(
                cuisine = filterLists[0].firstOrNull { it.isSelected }?.name,
                diet = filterLists[1].firstOrNull { it.isSelected }?.name,
                type = filterLists[2].firstOrNull { it.isSelected }?.name,
            )
            findNavController().popBackStack()
        }

        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapters() {
        listOf(
            binding.cuisineRv,
            binding.dietRv,
            binding.mealRv
        ).forEachIndexed { index, recyclerView ->
            val flexLayout = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
            recyclerView.adapter = adapters[index]
            adapters[index].updateFilters(filterLists[index])
            recyclerView.layoutManager = flexLayout
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}