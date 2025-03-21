package com.elekiwi.moviesappprometeo.domain

import java.io.Serializable

data class CastModel(
    var picUrl: String = "",
    var actor: String = ""
): Serializable
