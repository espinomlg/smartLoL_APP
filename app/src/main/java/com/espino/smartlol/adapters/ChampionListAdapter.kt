package com.espino.smartlol.adapters

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.util.SortedList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.espino.smartlol.R
import com.espino.smartlol.databinding.ListitemChampionBinding
import com.espino.smartlol.fragments.ChampionListFragment
import com.espino.smartlol.models.ChampionListItem
import kotlinx.android.synthetic.main.listitem_champion.view.*

//todo add PagedListAdapter in the future
class ChampionListAdapter(val listener: ChampionListFragment.IFragmentCallback) : RecyclerView.Adapter<ChampionListAdapter.ViewHolder>() {

    var championList: List<ChampionListItem>? = null
        set(list) {
            field = list
            sortedList.addAll(list)
        }

    private val sortedList: SortedList<ChampionListItem> = SortedList(ChampionListItem::class.java, object : SortedList.Callback<ChampionListItem>() {
        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            notifyItemRangeChanged(position, count, payload)

        }

        override fun compare(o1: ChampionListItem, o2: ChampionListItem): Int = o1.name.compareTo(o2.name)

        override fun areItemsTheSame(item1: ChampionListItem, item2: ChampionListItem): Boolean = item1.id == item2.id

        override fun areContentsTheSame(oldItem: ChampionListItem, newItem: ChampionListItem): Boolean = oldItem == newItem

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }
    })

    fun replaceAll(query: String) {
        sortedList.beginBatchedUpdates()
        for (index in (sortedList.size() - 1) downTo 0) {
            if (!sortedList[index].name.contains(query, ignoreCase = true)) {
                sortedList.removeItemAt(index)
            }
        }
        val list = championList?.filter { it.name.contains(query, ignoreCase = true) }
        sortedList.addAll(list)
        sortedList.endBatchedUpdates()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val item: ListitemChampionBinding = DataBindingUtil.inflate(inflater, R.layout.listitem_champion, parent, false)

        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    inner class ViewHolder(private val item: ListitemChampionBinding) : RecyclerView.ViewHolder(item.root) {

        init {
            itemView.setOnClickListener {
                val args = Bundle()
                args.putInt(ChampionListFragment.CHAMPION_ID, sortedList.get(adapterPosition).id)
                listener.loadChampionData(args)
            }
        }

        fun bind(champion: ChampionListItem?) = with(itemView) {
            item.champion = champion
            item.executePendingBindings()
        }
    }
}