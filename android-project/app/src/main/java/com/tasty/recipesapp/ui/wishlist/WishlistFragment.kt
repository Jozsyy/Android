package com.tasty.recipesapp.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentWishlistBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.toRecipeEntity
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.profile.ProfileFragment
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory
import com.tasty.recipesapp.ui.recipe.adapter.MyRecipesListAdapter
import com.tasty.recipesapp.ui.wishlist.viewmodel.WishlistViewModel
import com.tasty.recipesapp.ui.wishlist.viewmodel.factory.WishlistViewModelFactory

class WishlistFragment : Fragment(){
    companion object{
        private val TAG: String? = ProfileFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var recipesAdapter: MyRecipesListAdapter
    private lateinit var binding: FragmentWishlistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_profile, container, false)

        binding = FragmentWishlistBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application as App
        val recipeRepository = app.repository

        val viewModelFactory = WishlistViewModelFactory(recipeRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(WishlistViewModel::class.java)


        //Megnezi, hogy a context null vagy nem
        context?.let{
            viewModel.fetchRecipeData(it)
        }
        viewModel.myRecipeList.observe(viewLifecycleOwner){myRecipes ->

            initRecyclerView()
            recipesAdapter.setData(myRecipes)
            recipesAdapter.notifyItemRangeChanged(0,myRecipes.lastIndex)

        }

    }


    private fun initRecyclerView(){
        recipesAdapter = MyRecipesListAdapter(ArrayList<RecipeModel>(),
            requireContext(),
            onItemClickListener = {
                    recipe -> navigateToRecipeDetail(recipe)
            }
            ,onItemClickListener2 = {
                    recipe, position -> deleteFromMyList(recipe, position)

            }
        )
        //ha nincs findViewById akkor itt kell binding.recycler_view.adapter=....
        binding.recyclerView.adapter=recipesAdapter
        binding.recyclerView.layoutManager= LinearLayoutManager(context)
    }

    private fun navigateToRecipeDetail(recipe: RecipeModel){
        findNavController().navigate(
            R.id.action_wishlistFragment_to_recipeDetailFragment,
            bundleOf(BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id)
        )
    }


    private fun deleteFromMyList(recipeModel: RecipeModel, position: Int){
        //val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val app = requireActivity().application as App
        val recipeRepository = app.repository

        val viewModelFactory = WishlistViewModelFactory(recipeRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(WishlistViewModel::class.java)
        viewModel.deleteRecipe(recipeModel)


        val updatedRecipeList = viewModel.myRecipeList.value.orEmpty().toMutableList()
        updatedRecipeList.remove(recipeModel)
        recipesAdapter.setData(updatedRecipeList)
        //recipesAdapter.notifyDataSetChanged()
        recipesAdapter.notifyItemRemoved(position)
    }

}