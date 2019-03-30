package com.bferrari.data

import com.bferrari.data.model.FactsResponse
import com.bferrari.domain.Facts
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface StoneAppApi {

    @GET("jokes/search")
    fun getFacts(@Query("query") query: String): Observable<FactsResponse>

}