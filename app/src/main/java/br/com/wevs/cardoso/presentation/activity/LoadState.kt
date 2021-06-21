package br.com.wevs.cardoso.presentation.activity

sealed class LoadState {
    object Loading: LoadState()
    object Done: LoadState()
}