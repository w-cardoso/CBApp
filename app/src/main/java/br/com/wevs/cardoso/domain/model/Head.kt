package br.com.wevs.cardoso.domain.model

data class Head(
    val label: String? = null,
    val ref: String? = null,
    val repo: Repo? = null,
    val sha: String? = null,
)