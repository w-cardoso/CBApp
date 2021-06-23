package br.com.wevs.cardoso.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.wevs.cardoso.data.GitHubRepositoryImplements
import br.com.wevs.cardoso.di.createGetTopJavaUseCase
import br.com.wevs.cardoso.presentation.fragment.listJava.ListJavaDataStates
import br.com.wevs.cardoso.presentation.fragment.listJava.ListJavaViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ListJavaViewModelTest : AutoCloseKoinTest() {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var observer: Observer<ListJavaDataStates>

    private lateinit var viewModel: ListJavaViewModel

    @MockK
    private lateinit var repository: GitHubRepositoryImplements

    @Before
    fun setupsTest() {
        MockKAnnotations.init(this)
        MockitoAnnotations.initMocks(this)
        observer = Mockito.mock(Observer::class.java) as Observer<ListJavaDataStates>
        Dispatchers.setMain(mainThreadSurrogate)
        startKoin {
            modules(
                module {
                    factory { createGetTopJavaUseCase(repository) }
                    viewModel { ListJavaViewModel(get()) }
                }
            )
        }
        viewModel = get()
        coEvery { repository.getTopListJava(null) } returns mockk()
        viewModel.viewStates.observeForever(observer)
    }

    @Test
    fun `Assert that viewStates is not null`() {
        Assert.assertNotNull(viewModel.viewStates)
    }

    @Test
    fun `Assert state hasObserver`() {
        assert(viewModel.viewStates.hasObservers())
    }
}