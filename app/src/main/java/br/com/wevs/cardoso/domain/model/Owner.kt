package br.com.wevs.cardoso.domain.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Owner(
    var login: String? = null,

    var id: Int? = null,

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String = ""
)