package com.david.cook.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david.cook.R
import com.david.cook.data.local.Recipe
import kotlinx.android.synthetic.main.list_item.view.*

class RecipeListAdapter(private val recipeEvents: RecipeEvents) :
        RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private var listRecipe: List<Recipe> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listRecipe.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listRecipe[position], recipeEvents)
    }

    fun addAll(listRecipe: List<Recipe>) {
        this.listRecipe = listRecipe
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(recipe: Recipe, listener: RecipeEvents) {
            itemView.textViewName.text = recipe.title
            view.setOnClickListener { listener.onItemClicked(recipe) }
        }
    }
}
