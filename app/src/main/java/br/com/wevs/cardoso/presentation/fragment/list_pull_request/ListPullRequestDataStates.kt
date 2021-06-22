package br.com.wevs.cardoso.presentation.fragment.list_pull_request

import br.com.wevs.cardoso.domain.model.PullRequestModelItem

sealed class ListPullRequestStates {
    data class CallSucess(val listPullrequest: MutableList<PullRequestModelItem>) : ListPullRequestStates()
    object CallError : ListPullRequestStates()
}

sealed class ListPullRequestInteractor {
    data class GetPullRequestList(val owner: String, val repo: String) : ListPullRequestInteractor()
}