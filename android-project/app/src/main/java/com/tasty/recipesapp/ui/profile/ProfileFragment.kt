package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.recipe.adapter.RecipesListAdapter
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel

class ProfileFragment : Fragment(){
    companion object{
        private val TAG: String? = ProfileFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var recipesAdapter: RecipesListAdapter
    private lateinit var recycler_view: RecyclerView
    //private lateinit var binding: FragmentRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
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

        val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        //Megnezi, hogy a context null vagy nem
        context?.let{
            viewModel.fetchRecipeData(it)
        }

        viewModel.myRecipeList.observe(viewLifecycleOwner){myRecipes ->

//            for(recipe: RecipeModel in recipes){
//                Log.d(TAG, "Recipe Name: ${recipe.name}")
//                Log.d(TAG,"Recipe Description: ${recipe.description}")
//                Log.d(TAG, "----------------------------------------")
//            }

            //Create adapter
            //recipesAdapter = RecipesListAdapter(recipes, requireContext())
            recycler_view=view.findViewById(R.id.recyclerView)
            initRecyclerView()
            recipesAdapter.setData(myRecipes)

            //Notify adapter about the source/data change
            //recipesAdapter.notifyDataSetChanged() //this or that
            recipesAdapter.notifyItemRangeChanged(0,myRecipes.lastIndex)

        }

    }

    private fun initRecyclerView(){
        recipesAdapter = RecipesListAdapter(ArrayList<RecipeModel>(),
            requireContext(),
            onItemClickListener = {
                    recipe -> navigateToRecipeDetail(recipe)
            },
            onNewRecipeButtonClickListener = {
                navigateToAddNewRecipe()
            }
//            onItemLongClickListener = { recipe ->
//                viewModel.delete(recipe)
//            }
        )
        //ha nincs findViewById akkor itt kell binding.recycler_view.adapter=....
        recycler_view.adapter=recipesAdapter
        recycler_view.layoutManager= LinearLayoutManager(context)
    }

    private fun navigateToRecipeDetail(recipe: RecipeModel){
        findNavController().navigate(
            R.id.action_recipesFragment_to_recipeDetailFragment,
            bundleOf(BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id)
        )
    }

    private fun navigateToAddNewRecipe(){
        findNavController().navigate(
            R.id.action_profileFragment_to_newRecipeFragment
        )
    }

}