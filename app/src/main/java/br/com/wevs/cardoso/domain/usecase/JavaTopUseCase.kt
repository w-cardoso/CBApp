package br.com.wevs.cardoso.domain.usecase

import br.com.wevs.cardoso.domain.model.TopJava
import br.com.wevs.cardoso.domain.repository.GitHubRepository

class JavaTopUseCase(private val gitHubRepository: GitHubRepository) : UseCase<TopJava, Int>() {

    override suspend fun run(params: Int): TopJava {
        return gitHubRepository.getTopListJava(params)
    }

}