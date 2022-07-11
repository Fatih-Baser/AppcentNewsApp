package com.fatihbaserpl.appcentnewsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fatihbaserpl.appcentnewsapp.MainCoroutineRule
import com.fatihbaserpl.appcentnewsapp.fragments.SearchViewModel
import com.fatihbaserpl.appcentnewsapp.getOrAwaitValueTest
import com.fatihbaserpl.appcentnewsapp.repo.FakeNewsRepository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.fatihbaserpl.appcentnewsapp.util.Status
import com.google.common.truth.Truth.assertThat

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        viewModel = SearchViewModel(FakeNewsRepository())
    }

    @Test
    fun `data success`() {
        viewModel.searchNews("Besiktas")
        val value = viewModel.newsResponse.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }
}
