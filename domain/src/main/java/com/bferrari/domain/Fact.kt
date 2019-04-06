package com.bferrari.domain

data class Fact(val category: List<String>? = null,
                val iconUrl: String? = null,
                val id: String,
                val url: String? = null,
                val value: String)