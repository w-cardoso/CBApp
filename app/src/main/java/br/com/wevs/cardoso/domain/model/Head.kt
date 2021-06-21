package br.com.wevs.cardoso.domain.model

data class Head(
    val label: String,
    val ref: String,
    val repo: RepoX,
    val sha: String,
    val user: UserX
)