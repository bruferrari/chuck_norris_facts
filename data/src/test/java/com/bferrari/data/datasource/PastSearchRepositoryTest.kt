package com.bferrari.data.datasource

import com.bferrari.data.PastSearchCache
import com.bferrari.domain.PastSearch
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
        val pastSearches = listOf(PastSearch("test"))

        `when`(localPersistence.get()).thenReturn(Flowable.just(pastSearches))

        repository.getPastSearches().test().apply { assertValue(pastSearches) }

        verify(localPersistence).get()
        verifyNoMoreInteractions(localPersistence)
    }
}