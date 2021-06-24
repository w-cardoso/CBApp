package br.com.wevs.cardoso.domain.usecase

import br.com.wevs.cardoso.constants.EMPTY_STRING
import br.com.wevs.cardoso.domain.model.PullRequestModelItem
import br.com.wevs.cardoso.domain.model.RequestPullRequest
import br.com.wevs.cardoso.domain.repository.GitHubRepository

class PullRequestUseCase(private val gitHubRepository: GitHubRepository) :
    UseCase<List<PullRequestModelItem>, RequestPullRequest>() {

    override suspend fun run(params: RequestPullRequest?): List<PullRequestModelItem> {
        return gitHubRepository.getPullRequest(
            params?.owner ?: EMPTY_STRING,
            params?.repo ?: EMPTY_STRING
        )
    }
}