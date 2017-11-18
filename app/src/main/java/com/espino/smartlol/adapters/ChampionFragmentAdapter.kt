package com.espino.smartlol.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.espino.smartlol.fragments.ChampionAbilitiesFragment
import com.espino.smartlol.fragments.ChampionInfoFragment
import com.espino.smartlol.fragments.ChampionSkinsFragment


class ChampionFragmentAdapter(fragmentManager: FragmentManager, private var tabs: Array<String>) : FragmentPagerAdapter(fragmentManager){

    //index of tab elements
    private val INFO = 0
    private val ABILITIES = 1
    private val SKINS = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            INFO-> fragment = ChampionInfoFragment.newinstance()
            ABILITIES-> fragment = ChampionAbilitiesFragment.newInstance()
            SKINS-> fragment = ChampionSkinsFragment.newInstance()
        }

        return fragment!!
    }

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabs[position]
    }
}