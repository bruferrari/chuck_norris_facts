package com.bferrari.data.datasource

import TestUtils.Companion.exception
import TestUtils.Companion.pastSearch
import TestUtils.Companion.pastSearches
import com.bferrari.data.PastSearchCache
import com.bferrari.domain.PastSearch
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class PastSearchRepositoryTest {

    private lateinit var repository: PastSearchDataSource
    private lateinit var localPersistence: PastSearchCache

    @Before
    @Throws
    fun setUp() {
        localPersistence = Mockito.mock(PastSearchCache::class.java)
        repository = PastSearchRepository(localPersistence)
    }

    @Test
    fun `Get local stored past searches`() {
        `when`(localPersistence.get()).thenReturn(Flowable.just(pastSearches))

        repository.getPastSearches().test().assertValue(pastSearches)

        verify(localPersistence).get()
        verifyNoMoreInteractions(localPersistence)
    }

    @Test
    fun `Save search`() {
        `when`(localPersistence.put(pastSearch)).thenReturn(Completable.complete())

        repository.savePastSearch(pastSearch).test().apply {
            assertComplete()
            assertNoErrors()
        }

        verify(localPersistence).put(pastSearch)
        verifyNoMoreInteractions(localPersistence)
    }

    @Test
    fun `Get local stored past searches on error`() {
        `when`(localPersistence.get()).thenReturn(Flowable.error(exception))

        repository.getPastSearches().test().assertError(exception)

        verify(localPersistence).get()
        verifyNoMoreInteractions(localPersistence)
    }

    @Test
    fun `Save search on error`() {
        `when`(localPersistence.put(pastSearch)).thenReturn(Completable.error(exception))

        repository.savePastSearch(pastSearch).test().apply {
            assertNotComplete()
            assertError(exception)
        }

        verify(localPersistence).put(pastSearch)
        verifyNoMoreInteractions(localPersistence)
    }
}