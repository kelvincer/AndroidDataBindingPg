package com.keliceru.challengeprosegur.presentation.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.keliceru.challengeprosegur.domain.entitites.User
import com.keliceru.challengeprosegur.presentation.adapter.CinemaAdapter

@BindingAdapter("adapter", "onDiscard")
fun RecyclerView.setAdapter(data: List<User>, onClick: (User) -> Unit) {
    adapter = CinemaAdapter(data, onClick)
}