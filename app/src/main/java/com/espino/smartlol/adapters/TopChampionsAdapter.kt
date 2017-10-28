package com.espino.smartlol.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.espino.smartlol.R
import com.espino.smartlol.models.SummonerTopChampions
import kotlinx.android.synthetic.main.listitem_summoner_bestchampion.view.*
import org.json.JSONArray
import org.json.JSONObject


class TopChampionsAdapter(var topChampions: List<SummonerTopChampions>? = null) : RecyclerView.Adapter<TopChampionsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        var v: View = LayoutInflater.from(parent?.context).inflate(R.layout.listitem_summoner_bestchampion, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(topChampions?.get(position))
    }

    override fun getItemCount(): Int = topChampions?.size ?: 0


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: SummonerTopChampions?) = with(itemView){
            itembestchampion_txv_name.text = item?.name
            itembestchampion_txv_title.text = item?.title
            itembestchampion_txv_points.text = String.format(resources.getString(R.string.champion_mastery_points), item?.points)
            Glide.with(context).load(item?.img_url).into(itembestchampion_img_champion)
            itembestchampion_img_mastery.setImageResource(R.drawable.championmastery_7)
        }
    }

}