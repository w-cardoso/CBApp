package br.com.wevs.cardoso.domain.repository

import br.com.wevs.cardoso.domain.model.PullRequestModelItem
import br.com.wevs.cardoso.domain.model.TopJava

interface GitHubRepository {

    suspend fun getTopListJava(page: Int): TopJava

    suspend fun getPullRequest(owner: String, repo: String): List<PullRequestModelItem>

}