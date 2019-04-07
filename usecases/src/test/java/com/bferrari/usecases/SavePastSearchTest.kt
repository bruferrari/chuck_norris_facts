package com.bferrari.usecases

import UsecaseTestUtils.Companion.exception
import UsecaseTestUtils.Companion.pastSearch
import com.bferrari.data.datasource.PastSearchDataSource
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class SavePastSearchTest {

    private lateinit var repository: PastSearchDataSource
    private lateinit var savePastSearch: SavePastSearch

    @Before
    fun setUp() {
        repository = Mockito.mock(PastSearchDataSource::class.java)
        savePastSearch = SavePastSearch(repository)
    }

    @Test
    fun `Save past search`() {
        `when`(repository.savePastSearch(pastSearch)).thenReturn(Completable.complete())

        repository.savePastSearch(pastSearch).test().apply {
            assertComplete()
            assertNoErrors()
        }

        verify(repository).savePastSearch(pastSearch)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Save past search on error`() {
        `when`(repository.savePastSearch(pastSearch)).thenReturn(Completable.error(exception))

        repository.savePastSearch(pastSearch).test().assertError(exception)

        verify(repository).savePastSearch(pastSearch)
        verifyNoMoreInteractions(repository)
    }
}