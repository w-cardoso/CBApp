package br.com.wevs.cardoso.domain.model

data class PullRequestModelItem(
    val body: String,
    val head: Head,
    val title: String,
    val user: User
)