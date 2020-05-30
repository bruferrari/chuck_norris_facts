package com.bferrari.data.datasource

import TestUtils
import TestUtils.Companion.categories
import TestUtils.Companion.categoryResponse
import TestUtils.Companion.retrofitInstance
import com.bferrari.data.CategoryCache
import com.bferrari.data.AppApiService
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Flowable
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class CategoryRepositoryTest {

    private lateinit var repository: CategoryDataSource
    private lateinit var localPersistence: CategoryCache
    private lateinit var api: AppApiService
    private lateinit var webServer: MockWebServer
    private lateinit var gson: Gson

    @Before
    @Throws
    fun setUp() {
        localPersistence = Mockito.mock(CategoryCache::class.java)
        gson = Gson()
        webServer = MockWebServer()

        val baseURL = webServer.url("/").toString()
        val retrofit = retrofitInstance(baseURL)

        api = retrofit.create(AppApiService::class.java)

        repository = CategoryRepository(api, localPersistence)
    }

    @Test
    fun `Get categories from API and persist`() {
        val endpoint = "/jokes/categories"

        webServer.enqueue(MockResponse().apply {
            setResponseCode(TestUtils.HTTP_OK)
            setBody(gson.toJson(categoryResponse))
        })

        `when`(localPersistence.put(categories)).thenReturn(Completable.complete())

        repository.saveCategories().test().apply {
            awaitTerminalEvent(1, TimeUnit.SECONDS)
            assertNoErrors()
            assertComplete()
        }

        verify(localPersistence).put(categories)

        val mockRequest = webServer.takeRequest()
        assertEquals(endpoint, mockRequest.path)
    }

    @Test
    fun `When categories are cached`() {
        `when`(localPersistence.isCached()).thenReturn(true)

        repository.saveCategories().test().assertComplete()

        verify(localPersistence).isCached()
        verifyNoMoreInteractions(localPersistence)
    }

    @Test
    fun `Get categories from local storage`() {
        `when`(localPersistence.get()).thenReturn(Flowable.just(categories))

        repository.getCategories().test().apply {
            assertNoErrors()
            assertComplete()
            assertValue(categories)
        }

        verify(localPersistence).get()
        verifyNoMoreInteractions(localPersistence)
    }
}