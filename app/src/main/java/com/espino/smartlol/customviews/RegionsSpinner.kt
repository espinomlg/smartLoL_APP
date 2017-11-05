package com.espino.smartlol.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.espino.smartlol.R


class RegionsSpinner(context: Context, attrs: AttributeSet) : Spinner(context, attrs){
    private val regionsKeys: Array<String> = context.resources.getStringArray(R.array.lol_regions_keys)
    private val regionsValues: Array<String> = context.resources.getStringArray(R.array.lol_regions_values)

    init {
        val regionsAdapter: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_spinner_item, regionsValues)
        regionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        adapter = regionsAdapter
    }

    fun getSelectedRegion(): String = regionsKeys[selectedItemPosition]
}