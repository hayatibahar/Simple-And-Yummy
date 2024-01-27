package com.hayatibahar.simpleandyummy.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hayatibahar.simpleandyummy.databinding.FragmentFavoritesBinding
import com.hayatibahar.simpleandyummy.ui.home.adapter.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavoritesViewModel>()
    private val adapter = RecipesAdapter().apply {
        setOnRecipeItemClickListener {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFavoriteRecipes()
        initAdapter()
        observeData()
    }

    private fun getFavoriteRecipes() {
        viewModel.getFavoriteRecipes()
    }

    private fun observeData() {
        viewModel.favoriteRecipes.observe(viewLifecycleOwner) {
            adapter.updateRecipes(it)
        }
    }

    private fun initAdapter() {
        binding.favoriteRv.adapter = adapter
        val itemTouchHelperCallback = createItemTouchHelperCallback()
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.favoriteRv)
    }

    private fun createItemTouchHelperCallback(): ItemTouchHelper.SimpleCallback {
        return object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                handleSwipe(viewHolder)
            }
        }
    }

    private fun handleSwipe(viewHolder: RecyclerView.ViewHolder) {
        val id = adapter.getItemIdAtPosition(viewHolder.adapterPosition)
        viewModel.updateFavoriteRecipe(id, false)

        Snackbar.make(binding.favoriteRv, "Recipe deleted", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                viewModel.updateFavoriteRecipe(id, true)
            }
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}