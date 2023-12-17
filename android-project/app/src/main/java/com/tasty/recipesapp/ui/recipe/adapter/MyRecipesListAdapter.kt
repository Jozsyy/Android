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
import com.tasty.recipesapp.databinding.MyrecipeListItemBinding
import com.tasty.recipesapp.databinding.RecipeListItemBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class MyRecipesListAdapter (
    private var recipeList: List<RecipeModel>,
    private val context: Context,
    private val onItemClickListener: (RecipeModel) -> Unit,  //fuggveny RecipeModel bemenet, Unit kimenet
    private val onItemClickListener2: (RecipeModel, Int) -> Unit = { recipeModel: RecipeModel, i: Int -> }, //nem muszaj erteket adni neki konstruktor hivas eseten
    //private val onItemLongClickListener: (RecipeModel) -> Unit = {} //nem muszaj erteket adni neki konstruktor hivas eseten
): RecyclerView.Adapter<MyRecipesListAdapter.MyRecipeItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecipesListAdapter.MyRecipeItemViewHolder {
        val binding= MyrecipeListItemBinding.inflate(LayoutInflater.from(context),parent, false )
        return MyRecipeItemViewHolder(binding)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: MyRecipesListAdapter.MyRecipeItemViewHolder, position: Int) {
        val currentRecipe = recipeList[position]

        holder.recipeTitleView.text=currentRecipe.name
        val description = currentRecipe.description ?: ""
        val shortDescription = if (description.length > 200) {
            "${description.substring(0, 200)}..."
        } else {
            description
        }
        holder.recipeDescriptionView.text = shortDescription

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

    inner class MyRecipeItemViewHolder(binding: MyrecipeListItemBinding):
        RecyclerView.ViewHolder(binding.root){

        val recipeTitleView: TextView = binding.recipeItemTitleView
        val recipeDescriptionView: TextView = binding.recipeItemDescriptionView
        val recipeImageView: ImageView = binding.recipeImageView
        val recipeItemRatingView: TextView = binding.recipeItemRatingView
        val recipeDelete: TextView = binding.recipeItemDelete

        init{
            binding.recipeItemDetails.setOnClickListener {
                val currentPosition: Int = this.adapterPosition
                val currentRecipe: RecipeModel = recipeList[currentPosition]

                onItemClickListener(currentRecipe)
            }

            binding.recipeItemDelete.setOnClickListener {
                val currentPosition: Int = this.adapterPosition
                val currentRecipe: RecipeModel = recipeList[currentPosition]

                onItemClickListener2(currentRecipe, currentPosition)
            }

//            binding.root.setOnLongClickListener{
//                val currentPosition: Int = this.adapterPosition
//                val currentRecipe: RecipeModel = recipeList[currentPosition]
//
//                onItemLongClickListener(currentRecipe)
//                true
//            }

        }
    }
}