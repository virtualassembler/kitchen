package com.david.cook.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.david.cook.R
import com.david.cook.data.local.*
import com.david.cook.repository.RecipeRepository
import com.david.cook.utils.ID_SOCCER_LEAGUE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecipeEvents {

    private lateinit var gridLayoutManager: GridLayoutManager
    //private lateinit var recipeRepository: RecipeRepository
    private lateinit var recipeListAdapter: RecipeListAdapter
    //private lateinit var adapter: ArrayAdapter<Recipe>

    private val recipeViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(RecipeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //recipeViewModel.getRecipeList()
        recipeListAdapter = RecipeListAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = recipeListAdapter
        observeResponseData()
        search()
    }

    private fun observeResponseData() {
        recipeViewModel.recipeLiveData.observe(this, Observer { data ->
            recipeListAdapter.addAll(data)
            //recipeRepository = RecipeRepository(this@MainActivity)
            //recipeListAdapter.addAll(RecipeDatabase.getRecipe(application.applicationContext).getRecipeDAO().getRecipeList())
        })
    }

    private fun search(){
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                Log.e("a004",""+charSequence.toString());
                if (charSequence.isNotEmpty()){
                    recipeViewModel.getFilteredRecipeList(charSequence.toString())
                }else{
                    Log.e("a004","charsequense is empty");
                    recipeViewModel.getRecipeList()
                    //recipeListAdapter.addAll(recipeViewModel.getRecipeListLiveData())
                }


            }
            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(cs: CharSequence, i: Int, j: Int, k: Int) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.spanish_league -> {
                recipeViewModel.getRecipeList()
                return true
            }
            R.id.german_bundesliga -> {
                recipeViewModel.getRecipeList()
                return true
            }
            R.id.english_league -> {
                recipeViewModel.getRecipeList()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClicked(recipe: Recipe) {
        val intent: Intent = DetailActivity.createIntent(this@MainActivity)
        intent.putExtra(ID_SOCCER_LEAGUE, recipe.id)
        startActivity(intent)
    }

    private fun hasConnection(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
