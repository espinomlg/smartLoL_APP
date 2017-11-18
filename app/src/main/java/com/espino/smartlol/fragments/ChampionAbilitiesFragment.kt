package com.espino.smartlol.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.espino.smartlol.HomeActivity
import com.espino.smartlol.R
import com.espino.smartlol.databinding.FragmentChampionAbilitiesBinding
import com.espino.smartlol.viewmodels.ChampionViewModel


class ChampionAbilitiesFragment : Fragment(){

    private lateinit var viewmodel: ChampionViewModel
    private lateinit var binder: FragmentChampionAbilitiesBinding

    companion object {
        val TAG = "champion_abilities"

        fun newInstance(args: Bundle? = null) : ChampionAbilitiesFragment{
            val fragment = ChampionAbilitiesFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_champion_abilities, container, false)

        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val homeActivity = activity as HomeActivity
        viewmodel = ViewModelProviders.of(homeActivity.getChampionFragment()!!).get(ChampionViewModel::class.java)

        viewmodel.data?.observe(this@ChampionAbilitiesFragment, Observer {
            if(it?.count() == 1){
                binder.champion = it.first()
            }
        })
    }
}