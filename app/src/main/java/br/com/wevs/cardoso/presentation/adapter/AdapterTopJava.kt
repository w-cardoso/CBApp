package br.com.wevs.cardoso.presentation.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.domain.model.Item
import br.com.wevs.cardoso.extensions.inflate
import br.com.wevs.cardoso.extensions.loadImage

class AdapterTopJava : RecyclerView.Adapter<AdapterTopJava.TopJavaViewHolder>() {

    var listTopJava: MutableList<Item> = arrayListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)

            notifyItemRangeInserted(oldSize, newSize)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopJavaViewHolder(parent)

    override fun onBindViewHolder(holder: TopJavaViewHolder, position: Int) =
        holder.bind(listTopJava[position])

    override fun getItemCount(): Int = listTopJava.size

    override fun getItemViewType(position: Int): Int = VIEW_ID

    inner class TopJavaViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(VIEW_ID)) {

        fun bind(data: Item) = with(itemView) {
            findViewById<TextView>(R.id.item_java_repository_name).text = data.name
            findViewById<TextView>(R.id.item_java_repository_description).text = data.description
            findViewById<TextView>(R.id.item_java_repository_username).text = data.owner?.login
            findViewById<TextView>(R.id.item_java_repository_surname).text = data.language

            findViewById<TextView>(R.id.item_java_repository_branch).text =
                data.forksCount.toString()

            findViewById<TextView>(R.id.item_java_repository_favorite).text =
                data.stargazersCount.toString()

            this.loadImage(data.owner?.avatarUrl ?: "")
        }
    }

    companion object {
        private const val VIEW_ID = R.layout.item_java
    }
}