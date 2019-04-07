package com.bferrari.usecases

import UsecaseTestUtils.Companion.categories
import UsecaseTestUtils.Companion.exception
import com.bferrari.data.datasource.CategoryDataSource
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class GetCategoriesTest {

    private lateinit var getCategories: GetCategories
    private lateinit var categoryRepository: CategoryDataSource

    @Before
    fun setUp() {
        categoryRepository = Mockito.mock(CategoryDataSource::class.java)
        getCategories = GetCategories(categoryRepository)
    }

    @Test
    fun `Get categories`() {
        `when`(categoryRepository.getCategories()).thenReturn(Flowable.just(categories))

        getCategories.invoke().test().apply {
            assertNoErrors()
            assertComplete()
            assertValue(categories)
        }

        verify(categoryRepository).getCategories()
        verifyNoMoreInteractions(categoryRepository)
    }

    @Test
    fun `Get categories on error`() {
        `when`(categoryRepository.getCategories()).thenReturn(Flowable.error(exception))

        getCategories.invoke().test().assertError(exception)

        verify(categoryRepository).getCategories()
        verifyNoMoreInteractions(categoryRepository)
    }
}