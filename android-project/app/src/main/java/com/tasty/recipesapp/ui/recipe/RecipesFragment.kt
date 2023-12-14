package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.recipe.adapter.RecipesListAdapter
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel

class RecipesFragment : Fragment() {

    companion object{
        private val TAG: String? = RecipesFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var recipesAdapter: RecipesListAdapter
    private lateinit var recycler_view: RecyclerView
    private lateinit var binding: FragmentRecipesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_recipes, container, false)
        binding = FragmentRecipesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        context?.let {
//            val recipes = RecipeRepository.getRecipes(it)
//            Log.d(TAG, "Number of recipes= " + recipes.size)
//
//            for(recipe in recipes){
//                Log.d(TAG, "Recipe name: ${recipe.name}")
//            }
//        }

        val viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        //Megnezi, hogy a context null vagy nem
        context?.let{
            //viewModel.fetchRecipeData(it)
            viewModel.getAllRecipesFromApi()
        }

        viewModel.recipeList.observe(viewLifecycleOwner){recipes ->

//            for(recipe: RecipeModel in recipes){
//                Log.d(TAG, "Recipe Name: ${recipe.name}")
//                Log.d(TAG,"Recipe Description: ${recipe.description}")
//                Log.d(TAG, "----------------------------------------")
//            }

            //Create adapter
            //recipesAdapter = RecipesListAdapter(recipes, requireContext())
            recycler_view=view.findViewById(R.id.recyclerView)
            initRecyclerView()
            recipesAdapter.setData(recipes)

            //Notify adapter about the source/data change
            //recipesAdapter.notifyDataSetChanged() //this or that
            recipesAdapter.notifyItemRangeChanged(0,recipes.lastIndex)

        }

    }

    private fun initRecyclerView(){
        recipesAdapter = RecipesListAdapter(ArrayList<RecipeModel>(),
            requireContext(),
            onItemClickListener = {
                recipe -> navigateToRecipeDetail(recipe)
            }
            //Add to wishlist
            ,onItemClickListener2 ={
                recipe -> insertToMyList(recipe)
            }
        )
        //ha nincs findViewById akkor itt kell binding.recycler_view.adapter=....
        //binding = FragmentRecipesBinding.inflate(layoutInflater)

        recycler_view.adapter=recipesAdapter
        recycler_view.layoutManager=LinearLayoutManager(context)
    }

    private fun navigateToRecipeDetail(recipe: RecipeModel){
        findNavController().navigate(
            R.id.action_recipesFragment_to_recipeDetailFragment,
            bundleOf(BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id)
        )
    }

    private fun insertToMyList(recipe: RecipeModel){
        RecipeRepository.insertRecipe(recipe)
    }
}