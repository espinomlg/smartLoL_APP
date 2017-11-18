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
import com.espino.smartlol.databinding.FragmentChampionInfoBinding
import com.espino.smartlol.utils.getLanguage
import com.espino.smartlol.utils.showNetworkErrorDialog
import com.espino.smartlol.viewmodels.ChampionViewModel
import kotlinx.android.synthetic.main.fragment_champion_info.*


class ChampionInfoFragment : Fragment(){

    private lateinit var viewModel: ChampionViewModel
    private lateinit var binder: FragmentChampionInfoBinding

    companion object {
        val TAG = "champion_info"

        fun newinstance(args: Bundle? = null): ChampionInfoFragment{
            val fragment = ChampionInfoFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_champion_info, container, false)

        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val homeActivity = activity as HomeActivity
        viewModel = ViewModelProviders.of(homeActivity.getChampionFragment()!!).get(ChampionViewModel::class.java)

        viewModel.data?.observe(this@ChampionInfoFragment, Observer {
            if(it?.count() == 1){
                binder.champion = it.first()
                changeContentVisibility(View.VISIBLE)
            }
        })

    }

    private fun changeContentVisibility(visibility: Int){
        champinfo_txv_name.visibility = visibility
        champinfo_txv_title.visibility = visibility
    }
}