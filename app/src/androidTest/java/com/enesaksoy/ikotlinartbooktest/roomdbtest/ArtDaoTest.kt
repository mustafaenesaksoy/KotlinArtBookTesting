package com.enesaksoy.ikotlinartbooktest.roomdbtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.test.filters.SmallTest
import com.enesaksoy.ikotlinartbooktest.getOrAwaitValue
import com.enesaksoy.ikotlinartbooktest.roomdb.Art
import com.enesaksoy.ikotlinartbooktest.roomdb.ArtDao
import com.enesaksoy.ikotlinartbooktest.roomdb.ArtDatabase
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest //istege baglı kullan pro
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var hiltrule = HiltAndroidRule(this)
    @Inject
    @Named("testdatabase")
    lateinit var artDatabase: ArtDatabase
    private lateinit var artDao: ArtDao

    @Before
    fun setup(){
        /*artDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),ArtDatabase::class.java)
            .allowMainThreadQueries().build()*/
        hiltrule.inject()
        artDao = artDatabase.artDao()
    }

    @Before
    fun teardown(){
        artDatabase.close()
    }

    @Test
    fun insertartTesting() = runBlocking{
        val art = Art("deneme","deneme",12,"test.com",1)
        artDao.insertArt(art)
        val list = artDao.observeArts().getOrAwaitValue()
        assertThat(list).contains(art)
    }

    @Test
    fun deleteArtTesting() = runBlocking {
        val art = Art("name","artistname",11,"deneme.com",1)
        artDao.insertArt(art)
        artDao.deleteArt(art)
        val list = artDao.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(art)
    }
}