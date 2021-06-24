package br.com.wevs.cardoso.presentation.fragment.listPullRequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.constants.API_ERROR
import br.com.wevs.cardoso.domain.model.PullRequestModelItem
import br.com.wevs.cardoso.presentation.adapter.AdapterPullRequest
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentListPullRequest : Fragment() {

    private val pullViewModel: ListPullRequestViewModel by viewModel()
    private val adapterPullRequest: AdapterPullRequest by inject()
    private val arguments by navArgs<FragmentListPullRequestArgs>()
    private val owner by lazy { arguments.owner }
    private val repos by lazy { arguments.repo }
    private lateinit var shimmerViewContainer: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_list_pull_request,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startShimmerLayout(view)
        setupsRecyclerView(view)
        getListPullRequest()
        initViewModel()
    }

    private fun initViewModel() {
        pullViewModel.viewStates.observe(viewLifecycleOwner, { viewStates ->
            viewStates.let {
                when (it) {
                    is ListPullRequestStates.CallSucess -> {
                        poupulateAdapter(it.listPullrequest)
                    }

                    is ListPullRequestStates.CallError -> {
                        buildError()
                    }
                }
            }
        })
    }

    private fun getListPullRequest() {
        pullViewModel.interpret(ListPullRequestInteractor.GetPullRequestList(owner, repos))
    }

    private fun buildError() {
        //NO MELHOR CENARIO CONSTRUIRIA UM FRAGMENT PARA EXIBICAO DO ERRO
        Toast.makeText(view?.context, API_ERROR, Toast.LENGTH_LONG).show()
    }

    private fun poupulateAdapter(listPullrequest: MutableList<PullRequestModelItem>) {
        adapterPullRequest.pullRequestList = listPullrequest
        stopShimmerLayout()
    }

    private fun setupsRecyclerView(view: View) =
        with(view.findViewById<RecyclerView>(R.id.list_pull_request_java_rcv)) {
            addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
            adapter = adapterPullRequest
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