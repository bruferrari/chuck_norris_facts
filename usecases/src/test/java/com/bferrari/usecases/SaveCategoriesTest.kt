package com.bferrari.usecases

import UsecaseTestUtils.Companion.exception
import com.bferrari.data.datasource.CategoryDataSource
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class SaveCategoriesTest {

    private lateinit var repository: CategoryDataSource
    private lateinit var saveCategories: SaveCategories

    @Before
    fun setUp() {
        repository = Mockito.mock(CategoryDataSource::class.java)
        saveCategories = SaveCategories(repository)
    }

    @Test
    fun `Save categories`() {
        `when`(repository.saveCategories()).thenReturn(Completable.complete())

        repository.saveCategories().test().apply {
            assertComplete()
            assertNoErrors()
        }

        verify(repository).saveCategories()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Save categories on error`() {
        `when`(repository.saveCategories()).thenReturn(Completable.error(exception))

        repository.saveCategories().test().assertError(exception)

        verify(repository).saveCategories()
        verifyNoMoreInteractions(repository)
    }
}