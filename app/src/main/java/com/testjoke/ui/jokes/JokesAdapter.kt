package com.testjoke.ui.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testjoke.data.entities.Value
import com.testjoke.databinding.JokeItemBinding

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {
    private val items = ArrayList<Value>()

    fun setItems(items: ArrayList<Value>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class JokesViewHolder(private val itemBinding: JokeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Value) {
            itemBinding.jokeTextView.text = item.joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val binding: JokeItemBinding =
            JokeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}