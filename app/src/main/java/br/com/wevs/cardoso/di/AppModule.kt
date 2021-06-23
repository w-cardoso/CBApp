package br.com.wevs.cardoso.di

import br.com.wevs.cardoso.presentation.adapter.AdapterPullRequest
import br.com.wevs.cardoso.presentation.adapter.AdapterTopJava
import br.com.wevs.cardoso.presentation.adapter.LoadStateAdapter
import br.com.wevs.cardoso.presentation.fragment.listJava.ListJavaViewModel
import br.com.wevs.cardoso.presentation.fragment.listPullRequest.ListPullRequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { ListJavaViewModel(get()) }

    viewModel { ListPullRequestViewModel(get()) }

    factory { AdapterTopJava() }

    factory { AdapterPullRequest() }

    factory { LoadStateAdapter() }

    single { createGetTopJavaUseCase(get()) }

    single { createTopJavaRepository(get()) }

    single { createGetPullRequestUseCase(get()) }
}