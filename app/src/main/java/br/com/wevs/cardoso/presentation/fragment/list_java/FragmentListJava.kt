package br.com.wevs.cardoso.presentation.fragment.list_java

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.domain.model.TopJava
import br.com.wevs.cardoso.presentation.activity.LoadState
import br.com.wevs.cardoso.presentation.adapter.AdapterTopJava
import br.com.wevs.cardoso.presentation.adapter.LoadStateAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentListJava : Fragment() {

    private val mainViewModel: ListJavaViewModel by viewModel()
    private val adapterTopJava: AdapterTopJava by inject()
    private val loadStateAdapter: LoadStateAdapter by inject()
    private var page: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_list_java,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupsRecyclerView(view)
        getListJavaTop(page)

        mainViewModel.viewStates.observe(viewLifecycleOwner, { viewStates ->
            viewStates.let {
                when (it) {
                    is ListJavaDataStates.CallSucess -> {
                        populateListAdapter(it.topJava)
                    }

                    is ListJavaDataStates.CallError -> {
                        messageError()
                    }
                }
            }
        })
    }

    private fun getListJavaTop(page: Int) {
        mainViewModel.interpret(ListJavaInteractor.GetListTopJava(page))
    }

    private fun messageError() {
        Toast.makeText(view?.context, "DEU  RUIMMM", Toast.LENGTH_LONG).show()
    }

    private fun populateListAdapter(topJava: TopJava) {
        adapterTopJava.listTopJava = topJava.items ?: mutableListOf()
    }

    private fun setupsRecyclerView(view: View) =
        with(view.findViewById<RecyclerView>(R.id.list_top_java_rcv)) {
            addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
            adapter = MergeAdapter(adapterTopJava, loadStateAdapter)
            layoutManager = LinearLayoutManager(view.context)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        loadStateAdapter.loadState = LoadState.Loading
                        page++
                        getListJavaTop(page)
                    }
                }
            })
        }

}