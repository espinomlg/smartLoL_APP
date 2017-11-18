package com.espino.smartlol.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.espino.smartlol.R
import com.espino.smartlol.databinding.ListitemSkinBinding
import com.espino.smartlol.models.ChampionSkin


class ChampionSkinsAdapter(var skins: List<ChampionSkin>? = null) : RecyclerView.Adapter<ChampionSkinsAdapter.ChampionSkinsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChampionSkinsViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binder: ListitemSkinBinding = DataBindingUtil.inflate(inflater, R.layout.listitem_skin, parent, false)

        return ChampionSkinsViewHolder(binder)
    }

    override fun onBindViewHolder(holder: ChampionSkinsViewHolder?, position: Int) {
        holder?.bind(skins?.get(position))
    }

    override fun getItemCount(): Int = skins?.size ?: 0


    inner class ChampionSkinsViewHolder(private var binder: ListitemSkinBinding) : RecyclerView.ViewHolder(binder.root){
        fun bind(skin: ChampionSkin?){
            binder.skin = skin
            binder.executePendingBindings()
        }
    }
}