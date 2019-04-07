package com.bferrari.usecases

import UsecaseTestUtils.Companion.exception
import UsecaseTestUtils.Companion.searches
import com.bferrari.data.datasource.PastSearchDataSource
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class GetPastSearchesTest {

    private lateinit var repository: PastSearchDataSource
    private lateinit var getPastSearches: GetPastSearches

    @Before
    fun setUp() {
        repository = Mockito.mock(PastSearchDataSource::class.java)
        getPastSearches = GetPastSearches(repository)
    }

    @Test
    fun `Get past searches`() {
        `when`(repository.getPastSearches()).thenReturn(Flowable.just(searches))

        repository.getPastSearches().test().apply {
            assertNoErrors()
            assertComplete()
            assertValue(searches)
        }

        verify(repository).getPastSearches()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Get past searches on error`() {
        `when`(repository.getPastSearches()).thenReturn(Flowable.error(exception))

        repository.getPastSearches().test().assertError(exception)

        verify(repository).getPastSearches()
        verifyNoMoreInteractions(repository)
    }
}