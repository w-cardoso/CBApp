package br.com.wevs.cardoso.network

import br.com.wevs.cardoso.constants.GET_LIST_PULL_REQUEST
import br.com.wevs.cardoso.constants.GET_LIST_TOP_JAVA
import br.com.wevs.cardoso.domain.model.PullRequestModelItem
import br.com.wevs.cardoso.domain.model.TopJava
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET(GET_LIST_TOP_JAVA)
    suspend fun getListTopJava(@Query("page") page: Int?): TopJava

    @GET(GET_LIST_PULL_REQUEST)
    suspend fun getPullRequest(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): MutableList<PullRequestModelItem>
}