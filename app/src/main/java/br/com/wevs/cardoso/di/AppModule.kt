package br.com.wevs.cardoso.di

import br.com.wevs.cardoso.presentation.adapter.AdapterTopJava
import br.com.wevs.cardoso.presentation.adapter.LoadStateAdapter
import br.com.wevs.cardoso.presentation.fragment.list_java.ListJavaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { ListJavaViewModel(get()) }

    factory { AdapterTopJava() }

    factory { LoadStateAdapter() }

    single { createGetMainUseCase(get()) }

    single { createMainRepository(get()) }
}