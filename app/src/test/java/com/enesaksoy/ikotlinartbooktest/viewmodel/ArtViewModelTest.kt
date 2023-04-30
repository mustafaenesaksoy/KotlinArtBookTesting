package com.enesaksoy.ikotlinartbooktest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.enesaksoy.ikotlinartbooktest.MainCoroutineRule
import com.enesaksoy.ikotlinartbooktest.getOrAwaitValueTest
import com.enesaksoy.ikotlinartbooktest.repo.ArtRepositoryTest
import com.enesaksoy.ikotlinartbooktest.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){
        viewModel = ArtViewModel(ArtRepositoryTest())
    }

    @Test
    fun `yas verme hata al`(){
        viewModel.makeArt("mona lisa","da vinci","")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `isim verme hata al`(){
        viewModel.makeArt("","da vinci","12")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `artist verme hata al`(){
        viewModel.makeArt("mona lisa","","12")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }



}