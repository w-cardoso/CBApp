package br.com.wevs.cardoso.network

import br.com.wevs.cardoso.domain.model.PullRequestModelItem
import br.com.wevs.cardoso.domain.model.TopJava
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("/search/repositories?q=language:Java&sort=stars")
    suspend fun getListTopJava(@Query("page") page: Int): TopJava

    @GET("/repos/{owner}/{repo}/pulls")
    suspend fun getPullRequest(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): MutableList<PullRequestModelItem>
}