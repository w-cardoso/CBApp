package br.com.wevs.cardoso.domain.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Item (
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("private")
    var private: Boolean? = null,

    @SerializedName("owner")
    var owner: Owner? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("stargazers_count")
    @Expose
    var stargazersCount: Int? = null,

    @SerializedName("forks_count")
    @Expose
    var forksCount: Int? = null,

    @SerializedName("score")
    @Expose
    var score: Double? = null,

    @SerializedName("language")
    @Expose
    var language: String? = null
)