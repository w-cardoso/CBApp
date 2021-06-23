package br.com.wevs.cardoso.presentation.fragment.listJava

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wevs.cardoso.domain.model.ApiError
import br.com.wevs.cardoso.domain.model.TopJava
import br.com.wevs.cardoso.domain.usecase.JavaTopUseCase
import br.com.wevs.cardoso.domain.usecase.UseCaseResponse
import kotlinx.coroutines.cancel

class ListJavaViewModel(private val useCase: JavaTopUseCase) : ViewModel() {

    private val state: MutableLiveData<ListJavaDataStates> = MutableLiveData()
    val viewStates: LiveData<ListJavaDataStates> = state

    fun interpret(interactor: ListJavaInteractor) = when (interactor) {
        is ListJavaInteractor.GetListTopJava -> getListTopJava(interactor.page)
    }

    private fun getListTopJava(page: Int?) {
        useCase.invoke(viewModelScope, page, object : UseCaseResponse<TopJava> {
            override fun onSuccess(result: TopJava) {
                state.postValue(ListJavaDataStates.CallSucess(result))
            }

            override fun onError(apiError: ApiError?) {
                state.postValue(ListJavaDataStates.CallError)
            }
        })
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}