package com.david.cook.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david.cook.R
import com.david.cook.data.local.Recipe

/**
 * SoccerLeagueListAdapter
 *
 * Provides access to the SoccerLeague data items, makes a View for each SoccerLeague item
 *
 * @author david.mazo
 */

class RecipeListAdapter(private val soccerLeagueEvents: SoccerLeagueEvents) :
        RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private var listSoccerLeague: List<Recipe> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listSoccerLeague.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listSoccerLeague[position], soccerLeagueEvents)
    }

    fun addAll(listSoccerLeague: List<Recipe>) {
        this.listSoccerLeague = listSoccerLeague
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(soccerLeague: Recipe, listener: SoccerLeagueEvents) {
            //comment itemView.textViewName.text = soccerLeague.strTeam
            /*comment
            itemView.textViewStadium.text = "Stadium: ${soccerLeague.strStadium}"
            Glide.with(itemView)
                    .load(soccerLeague.strTeamBadge)
                    .centerCrop()
                    .fitCenter()
                    .override(1000, 1000)
                    .into(itemView.imageViewTeamBadge)
            */
            view.setOnClickListener { listener.onItemClicked(soccerLeague) }
        }
    }
}
