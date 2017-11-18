package com.espino.smartlol.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.espino.smartlol.HomeActivity
import com.espino.smartlol.R
import com.espino.smartlol.adapters.ChampionSkinsAdapter
import com.espino.smartlol.viewmodels.ChampionViewModel
import kotlinx.android.synthetic.main.fragment_champion_skins.*


class ChampionSkinsFragment : Fragment(){

    private lateinit var viewmodel: ChampionViewModel
    private lateinit var adapter: ChampionSkinsAdapter

    companion object {
        val TAG = "champion_skins"

        fun newInstance(args: Bundle? = null): ChampionSkinsFragment{
            val fragment = ChampionSkinsFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_champion_skins, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val homeActivity = activity as HomeActivity

        viewmodel = ViewModelProviders.of(homeActivity.getChampionFragment()!!).get(ChampionViewModel::class.java)

        viewmodel.data?.observe(this@ChampionSkinsFragment, Observer {
            if(it?.count() == 1){
                adapter.skins = it.first()?.skins
            }
        })
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ChampionSkinsAdapter()

        championskins_rcv.adapter = adapter
        championskins_rcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}