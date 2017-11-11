package com.espino.smartlol.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.espino.smartlol.R
import com.espino.smartlol.models.Champion
import com.espino.smartlol.utils.getLanguage
import com.espino.smartlol.viewmodels.ChampionViewModel
import kotlinx.android.synthetic.main.fragment_championinfo.*


class ChampionInfoFragment : Fragment(){

    private lateinit var viewModel: ChampionViewModel

    companion object {
        val TAG = "champion_info"

        fun newinstance(args: Bundle? = null): ChampionInfoFragment{
            val fragment = ChampionInfoFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_championinfo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this@ChampionInfoFragment).get(ChampionViewModel::class.java)

        viewModel.init(arguments.getInt(ChampionListFragment.CHAMPION_ID).toString(), language = getLanguage())
        viewModel.data?.observe(this@ChampionInfoFragment, Observer {
            if(it?.count() == 1){
                champinfo_progressbar.visibility = View.GONE
                bindData(it.first())
                changeContentVisibility(View.VISIBLE)
            }
        })
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun bindData(champion: Champion?){
        champinfo_txv_name.text = champion?.name
        champinfo_txv_title.text = champion?.title
    }

    private fun changeContentVisibility(visibility: Int){
        champinfo_txv_name.visibility = visibility
        champinfo_txv_title.visibility = visibility
    }
}