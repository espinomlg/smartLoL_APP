package com.espino.smartlol.fragments

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.espino.smartlol.R
import kotlinx.android.synthetic.main.fragment_currentgame.*


class CurrentGameFragment : Fragment(){

    companion object {
        val TAG: String = "current_game"

        fun newInstance(args: Bundle?=null): CurrentGameFragment{
            val fragment: CurrentGameFragment = CurrentGameFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_currentgame, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Provisional
        currentgame_list.visibility = View.GONE
        currentgame_txv_empty.visibility = View.VISIBLE
    }


}