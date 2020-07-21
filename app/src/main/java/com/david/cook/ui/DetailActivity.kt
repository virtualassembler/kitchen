package com.david.cook.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.david.cook.data.local.Recipe


class DetailActivity : AppCompatActivity() {

    //private lateinit var teamRepository: TeamRepository
    //private lateinit var adapter: ArrayAdapter<TeamEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.david.cook.R.layout.detail_activity)
        /*
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val idSoccerLeague = getSoccerLeague(this).getSoccerLeagueDAO().getSoccerLeagueDetail(intent.getIntExtra(ID_SOCCER_LEAGUE, 0))
        bindSoccerLeague(idSoccerLeague)
        requestNextFiveEvents(idSoccerLeague)

         */
    }


    /*
    private fun requestNextFiveEvents(soccerLeague: SoccerLeague) {
        teamRepository = TeamRepository(this)
        //comment adapter = ArrayAdapter(application.applicationContext, android.R.layout.simple_list_item_1, teamRepository.requestTeamReviewList(soccerLeague.idTeam))
        roadReferenceListView.adapter = adapter
    }
    */

    private fun bindSoccerLeague(soccerLeague: Recipe) {
        //comment textViewStrTeam.text = soccerLeague.strTeam
        //comment textViewStrDescriptionEN.text = soccerLeague.strDescriptionEN
        //comment textViewIntFormedYear.text = "Foundated in ${soccerLeague.intFormedYear}"
        //comment textViewWebsite.text = soccerLeague.strWebsite
        /*comment
        if (soccerLeague.strFacebook!!.isBlank() && soccerLeague.strTwitter!!.isBlank() && soccerLeague.strInstagram!!.isBlank())
            textViewSocialNetwork.visibility = View.INVISIBLE

         */
        /*comment
        textViewFacebook.text = soccerLeague.strFacebook
        textViewTwitter.text = soccerLeague.strTwitter
        textViewInstagram.text = soccerLeague.strInstagram
        Glide.with(imageViewStrTeamBadge)
                .load(soccerLeague.strTeamBadge)
                .centerCrop()
                .fitCenter()
                .override(400, 400)
                .into(imageViewStrTeamBadge)
        Glide.with(imageViewStrTeamJersey)
                .load(soccerLeague.strTeamJersey)
                .centerCrop()
                .fitCenter()
                .override(400, 400)
                .into(imageViewStrTeamJersey)
         */
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
