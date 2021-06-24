package br.com.wevs.cardoso.presentation.fragment.listJava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.constants.API_ERROR
import br.com.wevs.cardoso.constants.EMPTY_STRING
import br.com.wevs.cardoso.domain.model.Item
import br.com.wevs.cardoso.domain.model.TopJava
import br.com.wevs.cardoso.presentation.activity.LoadState
import br.com.wevs.cardoso.presentation.adapter.AdapterTopJava
import br.com.wevs.cardoso.presentation.adapter.LoadStateAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentListJava : Fragment() {
    private val controller by lazy { findNavController() }
    private val javaViewModel: ListJavaViewModel by viewModel()
    private val adapterTopJava: AdapterTopJava by inject()
    private val loadStateAdapter: LoadStateAdapter by inject()
    private var items: MutableList<Item> = mutableListOf()
    private var page: Int = 1
    private lateinit var shimmerViewContainer: ShimmerFrameLayout


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
        startShimmerLayout(view)
        setupsRecyclerView(view)
        getListJavaTop(page)
        initViewModel()
        setupsOnClick()

    }

    private fun setupsOnClick() {
        adapterTopJava.onItemClick = { pos ->
            val itemClicked = items[pos]
            val direcao = FragmentListJavaDirections.actionListJavaToListPullRequest(
                itemClicked.owner?.login ?: EMPTY_STRING,
                itemClicked.name ?: EMPTY_STRING
            )
            controller.navigate(direcao)
        }
    }

    private fun initViewModel() {
        javaViewModel.viewStates.observe(viewLifecycleOwner, { viewStates ->
            viewStates.let {
                when (it) {
                    is ListJavaDataStates.CallSucess -> {
                        populateListAdapter(it.topJava)
                    }

                    is ListJavaDataStates.CallError -> {
                        buildError()
                    }
                }
            }
        })
    }

    private fun getListJavaTop(page: Int) {
        javaViewModel.interpret(ListJavaInteractor.GetListTopJava(page))
    }

    private fun buildError() {
        //NO MELHOR CENARIO CONSTRUIRIA UM FRAGMENT PARA EXIBICAO DO ERRO
        Toast.makeText(view?.context, API_ERROR, Toast.LENGTH_LONG).show()
    }

    private fun populateListAdapter(topJava: TopJava) {
        stopShimmerLayout()
        val itemsApi = topJava.items
        if (itemsApi != null) {
            adapterTopJava.listTopJava = itemsApi
            items.addAll(topJava.items)
        }

    }

    private fun setupsRecyclerView(view: View) =
        with(view.findViewById<RecyclerView>(R.id.list_top_java_rcv)) {
            addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
            adapter = MergeAdapter(adapterTopJava, loadStateAdapter)
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

    private fun startShimmerLayout(view: View) {
        shimmerViewContainer = view.findViewById(R.id.shimmer_view_container)
        shimmerViewContainer.startShimmerAnimation()
    }

    private fun stopShimmerLayout() {
        shimmerViewContainer.stopShimmerAnimation()
        shimmerViewContainer.visibility = View.GONE
    }

}

