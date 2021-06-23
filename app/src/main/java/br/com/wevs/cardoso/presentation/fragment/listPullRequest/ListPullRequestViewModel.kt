package br.com.wevs.cardoso.presentation.fragment.listPullRequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wevs.cardoso.domain.model.ApiError
import br.com.wevs.cardoso.domain.model.PullRequestModelItem
import br.com.wevs.cardoso.domain.model.RequestPullRequest
import br.com.wevs.cardoso.domain.usecase.PullRequestUseCase
import br.com.wevs.cardoso.domain.usecase.UseCaseResponse
import kotlinx.coroutines.cancel

class ListPullRequestViewModel(private val useCase: PullRequestUseCase) : ViewModel() {

    private val state: MutableLiveData<ListPullRequestStates> = MutableLiveData()
    val viewStates: LiveData<ListPullRequestStates> = state

    fun interpret(interactor: ListPullRequestInteractor) = when (interactor) {
        is ListPullRequestInteractor.GetPullRequestList -> getListPullRequest(
            interactor.owner,
            interactor.repo
        )
    }

    private fun getListPullRequest(owner: String, repo: String) {
        useCase.invoke(
            viewModelScope,
            RequestPullRequest(owner, repo),
            object : UseCaseResponse<List<PullRequestModelItem>> {
                override fun onSuccess(result: List<PullRequestModelItem>) {
                    state.postValue(
                        ListPullRequestStates
                            .CallSucess(result as MutableList<PullRequestModelItem>)
                    )
                }

                override fun onError(apiError: ApiError?) {
                    state.postValue(ListPullRequestStates.CallError)
                }

            })
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}