package com.bferrari.data.datasource

import com.bferrari.data.StoneAppApi
import com.bferrari.data.model.FactsResponse
import com.bferrari.domain.Fact
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class FactsRepositoryTest {

    private lateinit var repository: FactsDataSource
    private lateinit var api: StoneAppApi
    private lateinit var gson: Gson
    private lateinit var webServer: MockWebServer

    @Before
    @Throws
    fun setUp() {
        gson = Gson()
        webServer = MockWebServer()

        val baseURL = webServer.url("/").toString()
        val okHttpClient = OkHttpClient.Builder()
            .build()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        api = retrofit.create(StoneAppApi::class.java)

        repository = FactsRepository(api)
    }

    @Test
    fun `Fetch facts from api`() {
        val response = FactsResponse(1, listOf(Fact(id = "", value = "Value")))
        val query = "test"
        val endpoint = "/jokes/search?query=$query"

        webServer.enqueue(MockResponse().apply {
            setResponseCode(TestUtils.HTTP_OK)
            setBody(gson.toJson(response))
        })

        repository.getFacts(query).test().apply {
            awaitTerminalEvent(1, TimeUnit.SECONDS)
            assertValue(response.result)
            assertNoErrors()
            assertValueCount(1)
        }

        val mockRequest = webServer.takeRequest()
        assertEquals(endpoint, mockRequest.path)
    }
}