package br.com.wevs.cardoso.presentation.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.domain.model.Item
import br.com.wevs.cardoso.extensions.inflate
import br.com.wevs.cardoso.extensions.loadImage

class AdapterTopJava : RecyclerView.Adapter<AdapterTopJava.TopJavaViewHolder>() {

    lateinit var onItemClick: (pos: Int) -> Unit

    var listTopJava: MutableList<Item> = mutableListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)

            notifyItemRangeInserted(oldSize, newSize)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopJavaViewHolder(parent)

    override fun onBindViewHolder(holder: TopJavaViewHolder, position: Int) =
        holder.bind(listTopJava[position], position)

    override fun getItemCount(): Int = listTopJava.size

    override fun getItemViewType(position: Int): Int = VIEW_ID

    inner class TopJavaViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(VIEW_ID)), View.OnClickListener {

        private var itemPosition: Int = 0

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(data: Item, position: Int) = with(itemView) {
            itemPosition = position
            findViewById<TextView>(R.id.item_java_repository_name).text = data.name
            findViewById<TextView>(R.id.item_java_repository_description).text = data.description
            findViewById<TextView>(R.id.item_java_repository_username).text = data.owner?.login
            findViewById<TextView>(R.id.item_java_repository_surname).text = data.language

            findViewById<TextView>(R.id.item_java_repository_branch).text =
                data.forksCount.toString()

            findViewById<TextView>(R.id.item_java_repository_favorite).text =
                data.stargazersCount.toString()

            data.owner?.avatarUrl.loadImage(
                findViewById(R.id.item_java_img),
                progressBar = findViewById(R.id.item_java_img_progressBar)
            )
        }

        override fun onClick(v: View) {
            onItemClick.invoke(itemPosition)
        }
    }

    companion object {
        private const val VIEW_ID = R.layout.item_java
    }
}