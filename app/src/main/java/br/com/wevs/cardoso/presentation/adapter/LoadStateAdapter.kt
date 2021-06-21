package br.com.wevs.cardoso.presentation.adapter

import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.extensions.inflate
import br.com.wevs.cardoso.extensions.visible
import br.com.wevs.cardoso.presentation.activity.LoadState


class LoadStateAdapter : RecyclerView.Adapter<LoadStateAdapter.LoadStateViewHolder>() {

    var loadState: LoadState = LoadState.Done
        set(value) {
            when (field) {
                value -> {
                    notifyItemChanged(0)
                }
                is LoadState.Loading -> {
                    itemsCount = 0
                    notifyItemRemoved(0)
                }
                is LoadState.Done -> {
                    itemsCount = 1
                    notifyItemInserted(1)
                }
            }

            field = value
        }

    private var itemsCount: Int = 0


    inner class LoadStateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(VIEW_ID)) {
        fun bind(loadState: LoadState) = with(itemView) {
           findViewById<ProgressBar>(R.id.progress_bar).visible(
                visible = LoadState.Loading == loadState
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LoadStateViewHolder(parent)
    override fun getItemViewType(position: Int): Int = VIEW_ID
    override fun getItemCount(): Int = itemsCount
    override fun onBindViewHolder(holder: LoadStateViewHolder, position: Int) {
        holder.bind(loadState)
    }

    companion object {
        private const val VIEW_ID =
            R.layout.item_load_state
    }
}