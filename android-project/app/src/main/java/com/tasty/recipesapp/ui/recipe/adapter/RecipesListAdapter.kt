package com.tasty.recipesapp.ui.recipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.databinding.RecipeListItemBinding
import com.tasty.recipesapp.repository.recipe.model.InstructionDTO
import com.tasty.recipesapp.repository.recipe.model.InstructionModel
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.profile.ProfileFragment
import com.tasty.recipesapp.ui.recipe.RecipeDetailFragment
import com.tasty.recipesapp.ui.recipe.RecipesFragment

class RecipesListAdapter (
    private var recipeList: List<RecipeModel>,
    private val context: Context,
    private val onItemClickListener: (RecipeModel) -> Unit,  //fuggveny RecipeModel bemenet, Unit kimenet
    private val onItemClickListener2: (RecipeModel) -> Unit = {}, //nem muszaj erteket adni neki konstruktor hivas eseten
    private val onItemLongClickListener: (RecipeModel) -> Unit = {} //nem muszaj erteket adni neki konstruktor hivas eseten
): RecyclerView.Adapter<RecipesListAdapter.RecipeItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListAdapter.RecipeItemViewHolder {
        val binding= RecipeListItemBinding.inflate(LayoutInflater.from(context),parent, false)
        //val binding2= FragmentProfileBinding.inflate(LayoutInflater.from(context),parent, false )
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

        var user_ratings = currentRecipe.user_ratings.score * 10
        holder.recipeItemRatingView.text=user_ratings.toString()

    }

    fun setData(newRecipeList: List<RecipeModel>){
        recipeList = newRecipeList
    }

    inner class RecipeItemViewHolder(binding: RecipeListItemBinding):
            RecyclerView.ViewHolder(binding.root){

                val recipeTitleView: TextView = binding.recipeItemTitleView
                val recipeDescriptionView: TextView = binding.recipeItemDescriptionView
                val recipeImageView: ImageView = binding.recipeImageView
                val recipeItemRatingView: TextView = binding.recipeItemRatingView

            init{
                binding.recipeItemDetails.setOnClickListener {
                    val currentPosition: Int = this.adapterPosition
                    val currentRecipe: RecipeModel = recipeList[currentPosition]

                    onItemClickListener(currentRecipe)
                }

                binding.recipeItemWishlist.setOnClickListener {
                    val currentPosition: Int = this.adapterPosition
                    val currentRecipe: RecipeModel = recipeList[currentPosition]

                    onItemClickListener2(currentRecipe)
                }

                binding.root.setOnLongClickListener{
                    val currentPosition: Int = this.adapterPosition
                    val currentRecipe: RecipeModel = recipeList[currentPosition]

                    onItemLongClickListener(currentRecipe)
                    true
                }

            }
            }
}