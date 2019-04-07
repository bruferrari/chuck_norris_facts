package com.bferrari.usecases

import UsecaseTestUtils.Companion.exception
import UsecaseTestUtils.Companion.facts
import UsecaseTestUtils.Companion.query
import com.bferrari.data.datasource.FactsDataSource
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class GetFactsTest {

    private lateinit var getFacts: GetFacts
    private lateinit var repository: FactsDataSource

    @Before
    fun setUp() {
        repository = Mockito.mock(FactsDataSource::class.java)
        getFacts = GetFacts(repository)
    }

    @Test
    fun `Get facts`() {
        `when`(repository.getFacts(query)).thenReturn(Observable.just(facts))

        getFacts.invoke(query).test().apply {
            assertNoErrors()
            assertComplete()
            assertValue(facts)
        }

        verify(repository).getFacts(query)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Get facts on error`() {
        `when`(repository.getFacts(query)).thenReturn(Observable.error(exception))

        getFacts.invoke(query).test().assertError(exception)

        verify(repository).getFacts(query)
        verifyNoMoreInteractions(repository)
    }
}