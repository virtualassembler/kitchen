package com.david.cook.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.david.cook.R
import com.david.cook.data.local.*
import com.david.cook.utils.ID_RECIPE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecipeEvents {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var recipeListAdapter: RecipeListAdapter

    val recipeViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(RecipeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recipeListAdapter = RecipeListAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = recipeListAdapter
        recipeViewModel.getRecipeList()
        observeResponseData()
        search()
    }

    private fun observeResponseData() {
        recipeViewModel.recipeLiveData.observe(this, Observer { data ->
            recipeListAdapter.addAll(data)
        })
    }

    private fun search(){
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                if (charSequence.isNotEmpty()){
                    recipeViewModel.getFilteredRecipeList(charSequence.toString())
                }else{
                    recipeViewModel.getRecipeList()
                }
            }
            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(cs: CharSequence, i: Int, j: Int, k: Int) {}
        })
    }

    override fun onItemClicked(recipe: Recipe) {
        val intent: Intent = DetailActivity.createIntent(this@MainActivity)
        intent.putExtra(ID_RECIPE, recipe.id)
        startActivity(intent)
    }
}
