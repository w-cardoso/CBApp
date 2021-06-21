package br.com.wevs.cardoso.domain.model

import com.google.gson.annotations.SerializedName

class TopJava(

    @SerializedName("items")
    val items: MutableList<Item>? = null
)