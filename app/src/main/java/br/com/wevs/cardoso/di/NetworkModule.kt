package br.com.wevs.cardoso.di

import br.com.wevs.cardoso.data.GitHubRepositoryImplements
import br.com.wevs.cardoso.domain.repository.GitHubRepository
import br.com.wevs.cardoso.domain.usecase.JavaTopUseCase
import br.com.wevs.cardoso.domain.usecase.PullRequestUseCase
import br.com.wevs.cardoso.network.GitHubService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val url = "https://api.github.com"

val NetworkModule = module {
    single { createService(get()) }
    single { createRetrofit(get(), url) }
    single { createOkHttpClient() }
}



fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    return builder.build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(Gson().newBuilder().create()))
        .build()
}

fun createService(retrofit: Retrofit): GitHubService {
    return retrofit.create(GitHubService::class.java)
}

fun createTopJavaRepository(apiService: GitHubService): GitHubRepository {
    return GitHubRepositoryImplements(apiService)
}

fun createGetTopJavaUseCase(mainRepository: GitHubRepository): JavaTopUseCase {
    return JavaTopUseCase(mainRepository)
}

fun createGetPullRequestUseCase(mainRepository: GitHubRepository): PullRequestUseCase {
    return PullRequestUseCase(mainRepository)
}