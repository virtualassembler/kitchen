package com.david.cook.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.david.cook.utils.ID_RECIPE
import kotlinx.android.synthetic.main.detail_activity.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.david.cook.R.layout.detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val recipe = RecipeViewModel(application).getRecipeById(intent.getIntExtra(ID_RECIPE, 0))
        titulo.text = recipe.title
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
        }
    }
}
