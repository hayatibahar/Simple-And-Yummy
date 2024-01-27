package com.hayatibahar.simpleandyummy.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hayatibahar.simpleandyummy.R
import com.hayatibahar.simpleandyummy.core.common.loadFromUrlByGlide
import com.hayatibahar.simpleandyummy.core.common.makeInvisible
import com.hayatibahar.simpleandyummy.core.common.makeVisible
import com.hayatibahar.simpleandyummy.core.common.showToast
import com.hayatibahar.simpleandyummy.core.domain.model.Ingredient
import com.hayatibahar.simpleandyummy.core.domain.model.RecipeDetail
import com.hayatibahar.simpleandyummy.databinding.FragmentDetailBinding
import com.hayatibahar.simpleandyummy.ui.detail.adapter.IngredientsAdapter
import com.hayatibahar.simpleandyummy.ui.detail.adapter.InstructionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailViewModel>()
    private val ingredientsAdapter = IngredientsAdapter()
    private val instructionAdapter = InstructionAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonListeners()
        getRecipeDetail()
        initAdapter()
        observeData()
        showIngredientsOrRecipe()
    }

    private fun getRecipeDetail() {
        viewModel.getRecipeDetail(args.id)
    }

    private fun observeData() {
        viewModel.detailUiState.observe(viewLifecycleOwner) {
            when (it) {
                is DetailUiState.Error -> {
                    handleError(it.errorMessage)
                }

                is DetailUiState.Loading -> {
                    handleLoading()
                }

                is DetailUiState.Success -> {
                    handleSuccess(it.data)
                }
            }
        }
    }

    private fun handleError(errorMessage: String) {
        binding.progressBar.makeInvisible()
        findNavController().popBackStack()
        showToast(requireContext(), errorMessage)
    }

    private fun handleLoading() {
        binding.progressBar.makeVisible()
    }

    private fun handleSuccess(recipeDetail: RecipeDetail) {
        with(binding) {
            favoriteIv.setImageResource(if (recipeDetail.isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
            favoriteIv.setOnClickListener { recipeDetail.onFavorite.invoke() }
            recipeNameTv.text = recipeDetail.title
            "Ready in ${recipeDetail.readyInMinutes} min".also { timeTv.text = it }
            recipeIv.loadFromUrlByGlide(recipeDetail.image)
            summaryTv.text = recipeDetail.summary
            ingredientsAdapter.updateIngredients(recipeDetail.ingredients)
            instructionAdapter.updateInstructions(recipeDetail.instructions)
            progressBar.makeInvisible()

            addToGroceriesBtn.setOnClickListener {
                showAlertDialog(recipeDetail.ingredients)
            }
        }
    }

    private fun showAlertDialog(ingredients : List<Ingredient>) {
        AlertDialog.Builder(requireContext())
            .setTitle("Add Ingredients to Grocery List")
            .setMessage("Choose an option")
            .setPositiveButton("Replace with Cart") { _, _ ->
                viewModel.deleteGroceries()
                viewModel.addToGroceries(ingredients)
            }
            .setNegativeButton("Add to Cart") { _, _ ->
                viewModel.addToGroceries(ingredients)
            }
            .setNeutralButton("Cancel", null)
            .show()
    }

    private fun initAdapter() {
        binding.ingredientsRv.adapter = ingredientsAdapter
        binding.instructionVp.adapter = instructionAdapter
    }

    private fun buttonListeners() {
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showIngredientsOrRecipe() {
        with(binding) {
            ingredientsTv.setOnClickListener {
                instructionVp.makeInvisible()
                addToGroceriesBtn.makeVisible()
                ingredientsRv.makeVisible()
            }
            recipeTv.setOnClickListener {
                ingredientsRv.makeInvisible()
                addToGroceriesBtn.makeInvisible()
                instructionVp.makeVisible()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}