package br.com.wevs.cardoso.presentation.fragment.list_pull_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.domain.model.PullRequestModelItem
import br.com.wevs.cardoso.presentation.adapter.AdapterPullRequest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentListPullRequest : Fragment() {

    private val pullViewModel: ListPullRequestViewModel by viewModel()
    private val adapterPullRequest: AdapterPullRequest by inject()
    private val arguments by navArgs<FragmentListPullRequestArgs>()
    private val owner by lazy { arguments.owner }
    private val repos by lazy { arguments.repo }

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
        setupsRecyclerView(view)
        getListPullRequest()
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
        TODO("Not yet implemented")
    }

    private fun poupulateAdapter(listPullrequest: MutableList<PullRequestModelItem>) {
        adapterPullRequest.pullRequestList = listPullrequest
    }

    private fun setupsRecyclerView(view: View) =
        with(view.findViewById<RecyclerView>(R.id.list_pull_request_java_rcv)) {
            addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
            adapter = adapterPullRequest
        }
}