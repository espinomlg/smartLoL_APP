package com.espino.smartlol.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.espino.smartlol.R
import com.espino.smartlol.adapters.ChampionFragmentAdapter
import com.espino.smartlol.utils.getLanguage
import com.espino.smartlol.utils.showNetworkErrorDialog
import com.espino.smartlol.viewmodels.ChampionViewModel
import kotlinx.android.synthetic.main.fragment_champion.*


class ChampionFragment : Fragment(){

    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: ChampionFragmentAdapter
    private lateinit var viewmodel: ChampionViewModel

    companion object {
        val TAG = "champion"

        fun newInstance(args: Bundle? = null): ChampionFragment{
            val fragment = ChampionFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View? = inflater?.inflate(R.layout.fragment_champion, container, false)

        if(v != null){
            tabLayout = activity.findViewById(R.id.app_tablayout)
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewmodel = ViewModelProviders.of(this@ChampionFragment).get(ChampionViewModel::class.java)
        viewmodel.init(arguments.getInt(ChampionListFragment.CHAMPION_ID).toString(), language = getLanguage())

        viewmodel.data?.observe(this@ChampionFragment, Observer {
            if(it?.count() == 1){
                champion_progressbar.visibility = View.GONE
                champion_viewpager.visibility = View.VISIBLE

                tabLayout.visibility = View.VISIBLE

                adapter = ChampionFragmentAdapter(childFragmentManager, resources.getStringArray(R.array.champion_tabs))

                tabLayout.setupWithViewPager(champion_viewpager)
                champion_viewpager.adapter = adapter
            }
        })

        viewmodel.networkError?.observe(this@ChampionFragment, Observer {
            if(it != null){
                champion_progressbar.visibility = View.GONE
                showNetworkErrorDialog(it)
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        tabLayout.removeAllTabs()
        tabLayout.visibility = View.GONE
    }
}