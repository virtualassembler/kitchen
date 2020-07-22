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
    private lateinit var recipeRepository: RecipeRepository
    private lateinit var recipeListAdapter: RecipeListAdapter
    //private lateinit var adapter: ArrayAdapter<Recipe>

    private val soccerLeagueViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(RecipeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //launchDialogFragment(R.string.app_name, R.drawable.soccer_leagues)
        soccerLeagueViewModel.callApi("Spanish La Liga")
        observeResponseData()


    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var cru = 1+1;

        //val soccerLeagueLiveData = MutableLiveData <List<Recipe>>()
        //RecipeRepository(applicationContext).requestMovieReviewList("",soccerLeagueLiveData)
        soccerLeagueViewModel.callApi("Spanish La Liga")
        observeResponseData()
    }
    */

    private fun observeResponseData() {
        soccerLeagueViewModel.recipeLiveData.observe(this, Observer { data ->
            //if (hasConnection()) {
                RecipeDatabase.getRecipe(applicationContext).getRecipeDAO().deleteAllSoccerLeague()
                val recipeDao: RecipeDao = RecipeDatabase.getRecipe(application.applicationContext).getRecipeDAO()
                for (recipe: Recipe in data) {
                    recipeDao.insertRecipe(recipe)
                }
                recipeListAdapter = RecipeListAdapter(this)
                gridLayoutManager = GridLayoutManager(this, 2)
                recyclerView.layoutManager = gridLayoutManager
                recyclerView.adapter = recipeListAdapter



                recipeRepository = RecipeRepository(this@MainActivity)
                recipeListAdapter.addAll(RecipeDatabase.getRecipe(application.applicationContext).getRecipeDAO().getRecipeList())

            //search
            searchInput.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(charSequence: CharSequence, s: Int, b: Int, c: Int) {
                    Log.e("a004",""+charSequence.toString());
                    if (charSequence.isNotEmpty()){
                        soccerLeagueViewModel.getFilteredRecipeList(charSequence.toString())
                        //observeResponseData()
                    }else{
                        Log.e("a004","charsequense is empty");
                        soccerLeagueViewModel.callApi()
                    }
                        //recipeListAdapter.addAll(RecipeDatabase.getRecipe(application.applicationContext).getRecipeDAO().getFilteredRecipeList(charSequence.toString()))

                    recipeListAdapter.notifyDataSetChanged()
                }
                override fun afterTextChanged(editable: Editable) {}
                override fun beforeTextChanged(cs: CharSequence, i: Int, j: Int, k: Int) {}
            })
        //}
            /*
            else{
                soccerLeagueListAdapter = RecipeListAdapter(this)
                gridLayoutManager = GridLayoutManager(this, 2)
                recyclerView.layoutManager = gridLayoutManager
                recyclerView.adapter = soccerLeagueListAdapter
                soccerLeagueRepository = RecipeRepository(this@MainActivity)
                soccerLeagueListAdapter.addAll(RecipeDatabase.getRecipe(application.applicationContext).getRecipeDAO().getRecipeList())
            }
            */
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.spanish_league -> {
                soccerLeagueViewModel.callApi("Spanish La Liga")
                return true
            }
            R.id.german_bundesliga -> {
                soccerLeagueViewModel.callApi("German Bundesliga")
                return true
            }
            R.id.english_league -> {
                soccerLeagueViewModel.callApi("English Premier League")
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
