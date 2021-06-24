package br.com.wevs.cardoso.domain.model

import com.google.gson.annotations.SerializedName

class Item(
    var id: Int? = null,
    var name: String? = null,
    var private: Boolean? = null,
    var owner: Owner? = null,
    var description: String? = null,

    @SerializedName("stargazers_count")
    var stargazersCount: Int? = null,

    @SerializedName("forks_count")
    var forksCount: Int? = null,

    var score: Double? = null,
    var language: String? = null
)