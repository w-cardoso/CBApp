package br.com.wevs.cardoso.data

import br.com.wevs.cardoso.domain.model.PullRequestModelItem
import br.com.wevs.cardoso.domain.model.TopJava
import br.com.wevs.cardoso.domain.repository.GitHubRepository
import br.com.wevs.cardoso.network.GitHubService

class GitHubRepositoryImplements(private val apiService: GitHubService) : GitHubRepository {

    override suspend fun getTopListJava(page: Int): TopJava {
        return apiService.getListTopJava(page)
    }

    override suspend fun getPullRequest(owner: String, repo: String): MutableList<PullRequestModelItem> {
        return apiService.getPullRequest(owner, repo)
    }
}