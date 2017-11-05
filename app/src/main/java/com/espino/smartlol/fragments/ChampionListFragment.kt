package com.espino.smartlol.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.espino.smartlol.R
import com.espino.smartlol.adapters.ChampionListAdapter
import com.espino.smartlol.utils.showNetworkErrorDialog
import com.espino.smartlol.viewmodels.ChampionListViewModel
import kotlinx.android.synthetic.main.fragment_championlist.*


class ChampionListFragment : Fragment(){

    interface IFragmentCallback{
        fun loadChampionData(args: Bundle)
    }

    private lateinit var championListAdapter: ChampionListAdapter
    private lateinit var viewmodel: ChampionListViewModel

    companion object {
        val TAG = "champion_list"

        fun newInstance(args: Bundle? = null) : ChampionListFragment{
            val fragment = ChampionListFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_championlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewmodel = ViewModelProviders.of(this@ChampionListFragment).get(ChampionListViewModel::class.java)
        viewmodel.init()
        viewmodel.data?.observe(this@ChampionListFragment, Observer {
            if(it?.count() == 1){
                championlist_progressbar.visibility = View.GONE
                championListAdapter.championList = it.first()?.champions
            }
        })
        viewmodel.networkError?.observe(this@ChampionListFragment, Observer {
            if(it != null){
                championlist_progressbar.visibility = View.GONE
                showNetworkErrorDialog(it)
            }
        })
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        championListAdapter = ChampionListAdapter()

        championlist_rcv.layoutManager = GridLayoutManager(context, 3) //todo span count assignment should be dynamic, relative to screen's width
        championlist_rcv.adapter = championListAdapter
    }
}