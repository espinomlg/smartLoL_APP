package com.espino.smartlol.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.espino.smartlol.R
import com.espino.smartlol.adapters.ChampionListAdapter
import com.espino.smartlol.utils.showNetworkErrorDialog
import com.espino.smartlol.viewmodels.ChampionListViewModel
import kotlinx.android.synthetic.main.fragment_championlist.*


class ChampionListFragment : Fragment(){

    interface IFragmentCallback{
        fun loadChampionData(args: Bundle)
    }

    private lateinit var callback: IFragmentCallback
    private lateinit var championListAdapter: ChampionListAdapter
    private lateinit var viewmodel: ChampionListViewModel

    companion object {
        val TAG = "champion_list"
        val CHAMPION_ID = "champion_id"

        fun newInstance(args: Bundle? = null) : ChampionListFragment{
            val fragment = ChampionListFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            callback = activity as IFragmentCallback
        } catch (e: ClassCastException) {
            Log.e("ACTIVITY_ERROR", "Activity ${activity::class.java.simpleName} must implement interface ChampionListFragment.IFragmentCallback")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
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
        championListAdapter = ChampionListAdapter(callback)

        championlist_rcv.layoutManager = GridLayoutManager(context, 3) //todo span count assignment should be dynamic, relative to screen's width
        championlist_rcv.adapter = championListAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_champions_menu, menu)

        val item: MenuItem? = menu?.findItem(R.id.menu_action_search_champion)
        val searchView: SearchView? = item?.actionView as SearchView

        searchView?.setIconifiedByDefault(false)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                championListAdapter.replaceAll(query)
                championlist_rcv.scrollToPosition(0)
                return true
            }
        })

    }
}