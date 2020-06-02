package com.bferrari.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Fact(val category: List<String>? = null,
                @SerializedName("icon_url")
                val iconUrl: String? = null,
                val id: String,
                val url: String? = null,
                val value: String) : Serializable