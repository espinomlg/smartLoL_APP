package com.espino.smartlol.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.espino.smartlol.R
import com.espino.smartlol.models.ChampionListItem
import kotlinx.android.synthetic.main.listitem_champion.view.*


class ChampionListAdapter : RecyclerView.Adapter<ChampionListAdapter.ViewHolder>() {

    var championList: List<ChampionListItem>? = null
        set(list) {
            field = list?.sortedBy {
                it.name
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent?.context).inflate(R.layout.listitem_champion, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(championList?.get(position))
    }

    override fun getItemCount(): Int = championList?.size ?: 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(champion: ChampionListItem?) = with(itemView){
            championitem_txv.text = champion?.name
            Glide.with(context)
                    .load(champion?.image)
                    .into(championitem_img)

            /*todo aplicar esto a SummonerInfoFragment también usando un método de extension
        Glide.with(holder.logo.getContext())
       .load(standingObjectItems.get(position).getImgId()).diskCacheStrategy(DiskCacheStrategy.ALL)
       .error(R.mipmap.ic_launcher)
       .placeholder(R.mipmap.ic_launcher)
       .into(holder.logo);*/
        }
    }
}