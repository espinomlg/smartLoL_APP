package com.espino.smartlol.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.espino.smartlol.R
import com.espino.smartlol.databinding.ListitemSummonerBestchampionBinding
import com.espino.smartlol.models.SummonerTopChampions
import kotlinx.android.synthetic.main.listitem_summoner_bestchampion.view.*


class TopChampionsAdapter(var topChampions: List<SummonerTopChampions>? = null) : RecyclerView.Adapter<TopChampionsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        val inflater = LayoutInflater.from(parent?.context)
        val item: ListitemSummonerBestchampionBinding = DataBindingUtil.inflate(inflater, R.layout.listitem_summoner_bestchampion, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(topChampions?.get(position))
    }

    override fun getItemCount(): Int = topChampions?.size ?: 0


    inner class ViewHolder(private val item: ListitemSummonerBestchampionBinding) : RecyclerView.ViewHolder(item.root){
        fun bind(topChampion: SummonerTopChampions?){
            this.item.topchampion = topChampion
            this.item.executePendingBindings()
        }
    }

}