package com.keliceru.challengeprosegur.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.keliceru.challengeprosegur.R
import com.keliceru.challengeprosegur.databinding.ItemMovieBinding
import com.keliceru.challengeprosegur.domain.entitites.User

class CinemaAdapter(private val users: List<User>, private val onDiscard: (User) -> Unit) :
    RecyclerView.Adapter<CinemaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMovieBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(user = users[position])
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(user: User) {
            binding.item = user
            binding.onClick = onDiscard
        }
    }
}