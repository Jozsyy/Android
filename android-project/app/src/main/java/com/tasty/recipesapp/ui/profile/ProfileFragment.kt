package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.databinding.RecipeListItemBinding
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.toRecipeEntity
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.recipe.adapter.RecipesListAdapter
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(){
    companion object{
        private val TAG: String? = ProfileFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var recipesAdapter: RecipesListAdapter
    private lateinit var recycler_view: RecyclerView
    //private val onNewRecipeButtonClickListener: ()->Unit = {} //nem muszaj erteket adni neki konstruktor hivas eseten
    private lateinit var binding: FragmentProfileBinding
    private lateinit var floatingButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    // return inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.inflate(inflater,container, false)
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

        val app = requireActivity().application as App
        val recipeRepository = app.repository

        val viewModelFactory = ProfileViewModelFactory(recipeRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        //val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)


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


            floatingButton=view.findViewById(R.id.newRecipeButton)
            floatingButton.setOnClickListener {
                navigateToAddNewRecipe()
            }


//            viewLifecycleOwner.lifecycleScope.launch {
//                recipesAdapter.setData(myRecipes)
//                recipesAdapter.notifyItemRangeChanged(0, myRecipes.lastIndex)
//            }
//            var delete_button: TextView = view.findViewById(R.id.recipeItemWishlist)
//            if( delete_button != null){
//                delete_button.text="Delete"
//            }
        }

    }


        private fun initRecyclerView(){
        recipesAdapter = RecipesListAdapter(ArrayList<RecipeModel>(),
            requireContext(),
            onItemClickListener = {
                    recipe -> navigateToRecipeDetail(recipe)
            }
            ,onItemClickListener2 = {
                recipe -> deleteFromMyList(recipe)
            }
//            , onItemLongClickListener = {
//                recipe -> deleteFromMyList(recipe)
//            }
        )
        //ha nincs findViewById akkor itt kell binding.recycler_view.adapter=....
        recycler_view.adapter=recipesAdapter
        recycler_view.layoutManager= LinearLayoutManager(context)
    }

    private fun navigateToRecipeDetail(recipe: RecipeModel){
        findNavController().navigate(
            R.id.action_profileFragment_to_recipeDetailFragment,
            bundleOf(BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id)
        )
    }

    private fun navigateToAddNewRecipe(){
        findNavController().navigate(
            R.id.action_profileFragment_to_newRecipeFragment
        )
    }

    private fun deleteFromMyList(recipeModel: RecipeModel){
        val recipeEntity = recipeModel.toRecipeEntity()
        //val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val app = requireActivity().application as App
        val recipeRepository = app.repository

        val viewModelFactory = ProfileViewModelFactory(recipeRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        viewModel.deleteRecipe(recipeEntity)


        val updatedRecipeList = viewModel.myRecipeList.value.orEmpty().toMutableList()
        updatedRecipeList.remove(recipeModel)
        recipesAdapter.setData(updatedRecipeList)
        recipesAdapter.notifyDataSetChanged()
    }

}