package com.espino.smartlol.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.espino.smartlol.R
import com.espino.smartlol.adapters.TopChampionsAdapter
import com.espino.smartlol.models.Summoner
import com.espino.smartlol.viewmodels.SummonerInfoViewModel
import kotlinx.android.synthetic.main.fragment_summonerinfo_2.*


class SummonerInfoFragment : Fragment(){

    interface IFragmentCallback{
        fun loadData(args: Bundle)
    }

    private lateinit var topchampsAdapter: TopChampionsAdapter
    private lateinit var viewmodel: SummonerInfoViewModel

    private lateinit var callback: IFragmentCallback

    companion object {
        val TAG = "summoner_info"
        val TXI_STATE = "txi_state"

        fun newInstance(args: Bundle? = null): SummonerInfoFragment{
            val fragment = SummonerInfoFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            callback = activity as IFragmentCallback
        }catch (ex: ClassCastException){
            Log.e("Activity_error", "activity ${activity.javaClass.simpleName} must implement interface SummonerInfoFragment.IFragmentCallback")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = null

        if(arguments != null)
            view = inflater?.inflate(R.layout.fragment_summonerinfo_2, container, false)
        else
            view = inflater?.inflate(R.layout.fragment_summonerinfo_1, container, false)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        summinfo_btn_search.setOnClickListener {
            val args = Bundle()
            args.putString(TXI_STATE, summinfo_txi_search?.editText?.text.toString())
            callback.loadData(args)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null) {
            summinfo_txi_search?.editText?.setText(arguments.getString(TXI_STATE))

            viewmodel = ViewModelProviders.of(this@SummonerInfoFragment).get(SummonerInfoViewModel::class.java)
            viewmodel.init(arguments.getString(TXI_STATE))
            viewmodel.getSummoner()?.observe(this@SummonerInfoFragment, Observer {
                bindData(it)
            })
        }
        if(savedInstanceState != null){
            summinfo_txi_search.editText?.setText(savedInstanceState.getString(TXI_STATE))
        }
    }

    private fun bindData(summoner: Summoner?){
        Glide.with(context).load(summoner?.icon).into(summinfo_img_profileicon)
        summinfo_txv_defeats.text = String.format(resources.getString(R.string.defeats),  summoner?.leagues?.get(0)?.losses.toString())
        summinfo_txv_summfound.text = summoner?.name
        summinfo_txv_victories.text = String.format(resources.getString(R.string.victories), summoner?.leagues?.get(0)?.wins.toString())
        summinfo_img_league.setImageDrawable(resources.getDrawable(R.drawable.diamond_v))

        topchampsAdapter = TopChampionsAdapter(summoner?.top_champions)
        summinfo_rcv_topchamps.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        summinfo_rcv_topchamps.adapter = topchampsAdapter
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(TXI_STATE, summinfo_txi_search.editText?.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        summinfo_txi_search.editText?.setText(savedInstanceState?.getString(TXI_STATE))
    }


    /* fun makeTransition(){

         val scene: Scene = Scene.getSceneForLayout(summinfo_rootview, R.layout.fragment_summonerinfo_2, context)
         val transitionSet = TransitionInflater.from(context).inflateTransition(R.transition.summonerinfo)

         transitionSet.addListener(object: Transition.TransitionListener {
             override fun onTransitionEnd(transition: Transition) {
                 isTransitionDone = true
                 viewmodel.getSummoner()?.observe(this@SummonerInfoFragment, Observer {
                     bindData(it)
                 })

                 summinfo_btn_search.setOnClickListener {
                     viewmodel.init(summinfo_txi_search.editText?.text.toString())
                 }
             }

             override fun onTransitionResume(transition: Transition) {}
             override fun onTransitionPause(transition: Transition) {}
             override fun onTransitionCancel(transition: Transition) {}
             override fun onTransitionStart(transition: Transition) {}
         })

         TransitionManager.go(scene, transitionSet)
     }*/

}