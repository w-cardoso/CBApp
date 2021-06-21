package br.com.wevs.cardoso.domain.usecase

import br.com.wevs.cardoso.domain.model.ApiError


interface UseCaseResponse<T> {

    fun onSuccess(result: T)

    fun onError(apiError: ApiError?)
}

