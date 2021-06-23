package br.com.wevs.cardoso.presentation.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.constants.EMPTY_STRING
import br.com.wevs.cardoso.domain.model.PullRequestModelItem
import br.com.wevs.cardoso.extensions.inflate
import br.com.wevs.cardoso.extensions.loadImage

class AdapterPullRequest : RecyclerView.Adapter<AdapterPullRequest.PullRequestViewHolder>() {

    var pullRequestList: MutableList<PullRequestModelItem> = mutableListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)

            notifyItemRangeInserted(oldSize, newSize)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PullRequestViewHolder(parent)

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) =
        holder.bind(pullRequestList[position])

    override fun getItemCount(): Int = pullRequestList.size

    override fun getItemViewType(position: Int): Int = VIEW_ID

    inner class PullRequestViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(VIEW_ID)) {

        fun bind(item: PullRequestModelItem) = with(itemView) {
            findViewById<TextView>(R.id.item_pull_request_title).text = item.title
            findViewById<TextView>(R.id.item_pull_request_description).text =
                item.head.repo?.description ?: EMPTY_STRING
            findViewById<TextView>(R.id.item_pull_request_username).text = item.user.login
            findViewById<TextView>(R.id.item_pull_request_name_surname).text =
                item.head.repo?.full_name ?: EMPTY_STRING

            item.user.avatar_url.loadImage(
                findViewById(R.id.item_pull_request_img),
                progressBar = findViewById(R.id.item_pull_request_img_progressBar)
            )
        }
    }

    companion object {
        private const val VIEW_ID = R.layout.item_pull_request
    }
}