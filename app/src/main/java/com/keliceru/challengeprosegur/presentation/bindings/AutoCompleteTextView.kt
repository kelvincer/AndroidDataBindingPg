package com.keliceru.challengeprosegur.presentation.bindings

import android.R
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter

@BindingAdapter("listener")
fun AutoCompleteTextView.setListener(setPosition: (Int) -> Unit) {
    setOnItemClickListener { _, _, i, _ ->
        setPosition(i)
    }
}

@BindingAdapter("adapter")
fun AutoCompleteTextView.setAdapter(items: List<String>) {
    setAdapter(ArrayAdapter(this.context, R.layout.simple_dropdown_item_1line, items))
}