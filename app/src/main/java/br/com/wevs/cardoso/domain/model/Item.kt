package br.com.wevs.cardoso.domain.model

class Item (
    var id: Int? = null,
    var name: String? = null,
    var private: Boolean? = null,
    var owner: Owner? = null,
    var description: String? = null,
    var stargazersCount: Int? = null,
    var forksCount: Int? = null,
    var score: Double? = null,
    var language: String? = null
)