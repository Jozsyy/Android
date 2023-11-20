package com.tasty.recipesapp.ui.recipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.RecipeListItemBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipesListAdapter (
    private var recipeList: List<RecipeModel>,
    private val context: Context
    //private val onItemClickListener: (RecipeModel) -> Unit
): RecyclerView.Adapter<RecipesListAdapter.RecipeItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListAdapter.RecipeItemViewHolder {
        val binding= RecipeListItemBinding.inflate(LayoutInflater.from(context),parent, false)
        return RecipeItemViewHolder(binding)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecipesListAdapter.RecipeItemViewHolder, position: Int) {
        val currentRecipe = recipeList[position]

        holder.recipeTitleView.text=currentRecipe.name
        holder.recipeDescriptionView.text=currentRecipe.description

        //Download image into imageView using Glide
        Glide.with(context)
            .load(currentRecipe.thumbnail_url)
            .centerCrop() //
            .placeholder(R.drawable.ic_launcher_background) //betolteskor zold kocka meg gondolkozik
            .fallback(R.drawable.ic_launcher_background) //Ha nem tudja letolteni a kepet
            .into(holder.recipeImageView)

        //val user_ratings =
    }

    fun setData(newList: List<RecipeModel>){
        recipeList=newList
    }

    inner class RecipeItemViewHolder(binding: RecipeListItemBinding):
            RecyclerView.ViewHolder(binding.root){
                val recipeTitleView: TextView = binding.recipeItemTitleView
                val recipeDescriptionView: TextView = binding.recipeItemDescriptionView
                val recipeImageView: ImageView = binding.recipeImageView
            }
}