package com.espino.smartlol

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.MenuItem
import com.espino.smartlol.fragments.CurrentGameFragment
import com.espino.smartlol.fragments.SummonerInfoFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.application_toolbar.*


class HomeActivity : AppCompatActivity(), SummonerInfoFragment.IFragmentCallback {

    private var currentGameFragment: CurrentGameFragment? = null
    private var summInfoFragment: SummonerInfoFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(app_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_navmenu)

        if(savedInstanceState == null){
            currentGameFragment = CurrentGameFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.home_container, currentGameFragment, CurrentGameFragment.TAG).commit()
        }

        navigation_navview.setCheckedItem(R.id.navigation_menuitem_currentgame)
        navigation_navview.setNavigationItemSelectedListener {
            if(!it.isChecked){
                when(it.itemId){
                    R.id.navigation_menuitem_currentgame ->{
                        currentGameFragment = CurrentGameFragment.newInstance()
                        supportFragmentManager.beginTransaction().replace(R.id.home_container, currentGameFragment, CurrentGameFragment.TAG).commit()
                    }
                    R.id.navigation_menuitem_summoner -> {
                        if(summInfoFragment == null)
                            summInfoFragment = SummonerInfoFragment.newInstance()
                        supportFragmentManager.beginTransaction().replace(R.id.home_container, summInfoFragment, SummonerInfoFragment.TAG).commit()
                    }
                    else->Log.v("HomeActivity:", "onNavigationItemSelected: ${it.title}")
                }
            }

            navigation_drawerlayout.closeDrawers()

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->navigation_drawerlayout.openDrawer(GravityCompat.START)
        }

        return true
    }

    //TODO transaction doesn't work
    override fun loadData(args: Bundle) {
        ViewCompat.setTransitionName(findViewById(R.id.linear_search), "layout_search")
        ViewCompat.setTransitionName(findViewById(R.id.summinfo_txi_search), "txi_search")
        ViewCompat.setTransitionName(findViewById(R.id.summinfo_btn_search), "btn_search")

        summInfoFragment = SummonerInfoFragment.newInstance(args)
        summInfoFragment?.sharedElementEnterTransition = ChangeBounds()

        supportFragmentManager.beginTransaction()
                .addSharedElement(findViewById(R.id.linear_search), "layout_search")
                .addSharedElement(findViewById(R.id.summinfo_txi_search), "txi_search")
                .addSharedElement(findViewById(R.id.summinfo_btn_search), "btn_search")
                .replace(R.id.home_container, summInfoFragment, SummonerInfoFragment.TAG)
                .commit()
    }



}
