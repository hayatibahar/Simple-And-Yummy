package com.hayatibahar.simpleandyummy.ui.grocery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.hayatibahar.simpleandyummy.core.common.makeGone
import com.hayatibahar.simpleandyummy.core.common.makeVisible
import com.hayatibahar.simpleandyummy.databinding.FragmentGroceryBinding
import com.hayatibahar.simpleandyummy.ui.grocery.adapter.GroceriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroceryFragment : Fragment() {

    private var _binding: FragmentGroceryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<GroceryViewModel>()
    private val groceryAdapter = GroceriesAdapter().apply {
        onGroceryCheckBoxListener { id, isChecked ->
            viewModel.updateGroceryCheckStatusUseCase(id, isChecked)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGroceryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getGroceries()
        initAdapter()
        initListeners()
        observeData()
    }

    private fun initListeners() {
        binding.deleteFab.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Groceries")
            .setMessage("Which groceries would you like to delete?")
            .setPositiveButton("Delete All") { _, _ ->
                viewModel.deleteGroceries()
            }
            .setNegativeButton("Delete Checked") { _, _ ->
                viewModel.deleteCheckedGroceries()
            }
            .setNeutralButton("Cancel", null)
            .show()
    }

    private fun getGroceries() {
        viewModel.getGroceries()
    }

    private fun observeData() {
        viewModel.groceries.observe(viewLifecycleOwner) {
            groceryAdapter.updateGroceries(it)
            if (it.isNotEmpty()) makeInvisibleNoDataTv() else makeVisibleNoDataTv()
        }
    }

    private fun initAdapter() {
        binding.groceriesRv.adapter = groceryAdapter
    }

    private fun makeInvisibleNoDataTv() {
        binding.nothingToBuyTv.makeGone()
        binding.browseTv.makeGone()
    }

    private fun makeVisibleNoDataTv() {
        binding.nothingToBuyTv.makeVisible()
        binding.browseTv.makeVisible()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}