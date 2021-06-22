package br.com.wevs.cardoso.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.wevs.cardoso.presentation.activity.LoadState
import br.com.wevs.cardoso.presentation.adapter.LoadStateAdapter

fun View.endlessScroll(
    @IdRes idRecycler: Int,
    mergeAdapter: MergeAdapter,
    page: Int,
) : Int{
    val loadStateAdapter : LoadStateAdapter = mergeAdapter.adapters.component2() as LoadStateAdapter
    with(this.findViewById<RecyclerView>(idRecycler)) {
        addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
        adapter = mergeAdapter
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadStateAdapter.loadState = LoadState.Loading
                }
            }
        })
    }
    return page+1

}