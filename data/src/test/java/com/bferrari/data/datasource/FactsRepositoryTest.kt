package com.bferrari.data.datasource

import TestUtils.Companion.retrofitInstance
import com.bferrari.data.AppApiService
import com.bferrari.data.model.FactsResponse
import com.bferrari.domain.Fact
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class FactsRepositoryTest {

    private lateinit var repository: FactsDataSource
    private lateinit var api: AppApiService
    private lateinit var gson: Gson
    private lateinit var webServer: MockWebServer

    @Before
    @Throws
    fun setUp() {
        gson = Gson()
        webServer = MockWebServer()

        val baseURL = webServer.url("/").toString()
        val retrofit = retrofitInstance(baseURL)

        api = retrofit.create(AppApiService::class.java)

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