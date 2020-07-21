package com.david.cook.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.david.cook.R
import com.david.cook.data.local.*
import com.david.cook.repository.RecipeRepository
import com.david.cook.utils.ID_SOCCER_LEAGUE
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity(), SoccerLeagueEvents {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var soccerLeagueRepository: RecipeRepository
    private lateinit var soccerLeagueListAdapter: RecipeListAdapter

    private val soccerLeagueViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(RecipeViewModel(application.applicationContext)::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var cru = 1+1;
        //launchDialogFragment(R.string.app_name, R.drawable.soccer_leagues)
        val soccerLeagueLiveData = MutableLiveData <List<Recipe>>()
        RecipeRepository(applicationContext).requestMovieReviewList("",soccerLeagueLiveData)
        //soccerLeagueViewModel.callApi("Spanish La Liga")
        //comment observeResponseData()
    }

    private fun observeResponseData() {
        soccerLeagueViewModel.soccerLeagueLiveData.observe(this, Observer { data ->
            if (hasConnection()) {
                RecipeDatabase.getRecipe(applicationContext).getRecipeDAO().deleteAllSoccerLeague()
                val recipeDao: RecipeDao = RecipeDatabase.getRecipe(application.applicationContext).getRecipeDAO()
                for (recipe: Recipe in data) {
                    recipeDao.insertRecipe(recipe)
                }
                soccerLeagueListAdapter = RecipeListAdapter(this)
                gridLayoutManager = GridLayoutManager(this, 2)
                recyclerView.layoutManager = gridLayoutManager
                recyclerView.adapter = soccerLeagueListAdapter
                soccerLeagueRepository = RecipeRepository(this@MainActivity)
                soccerLeagueListAdapter.addAll(RecipeDatabase.getRecipe(application.applicationContext).getRecipeDAO().getRecipeList())
            }else{
                soccerLeagueListAdapter = RecipeListAdapter(this)
                gridLayoutManager = GridLayoutManager(this, 2)
                recyclerView.layoutManager = gridLayoutManager
                recyclerView.adapter = soccerLeagueListAdapter
                soccerLeagueRepository = RecipeRepository(this@MainActivity)
                soccerLeagueListAdapter.addAll(RecipeDatabase.getRecipe(application.applicationContext).getRecipeDAO().getRecipeList())
            }
        })
    }

    /*
    private fun launchDialogFragment(name: Int, image: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        //val dialogFragment = SplashViewDialogFragment(application)
        //val dialogFragmentBundle = Bundle()
        //dialogFragmentBundle.putInt("name", name)
        //dialogFragmentBundle.putInt("image", image)
        //dialogFragment.arguments = dialogFragmentBundle
        //dialogFragment.show(fragmentTransaction, "dialog")
    }
    */

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
