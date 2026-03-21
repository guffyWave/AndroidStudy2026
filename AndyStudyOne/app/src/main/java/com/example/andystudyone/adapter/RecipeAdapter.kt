package com.example.andystudyone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andystudyone.R
import com.example.andystudyone.model.Recipe

class RecipeAdapter(private var recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.ivRecipe)
        val recipeName: TextView = itemView.findViewById(R.id.tvName)
        val recipeInstructions: TextView = itemView.findViewById(R.id.tvInstructions)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return (RecipeViewHolder(view))
    }

    override fun onBindViewHolder(
        holder: RecipeViewHolder, position: Int
    ) {
        val recipe = recipes[position];
        holder.recipeName.text = recipe.name
        holder.recipeInstructions.text = recipe.instructions[0]
        Glide.with(holder.itemView.context).load(recipe.image).into(holder.recipeImage)
    }

    override fun getItemCount(): Int =
        recipes.size

    fun updateRecipes(newRecipes: List<Recipe>) {
        recipes = newRecipes;
        notifyDataSetChanged();
    }

}