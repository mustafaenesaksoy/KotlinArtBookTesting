package com.enesaksoy.ikotlinartbooktest.dependencyinjection

import androidx.room.Room
import com.enesaksoy.ikotlinartbooktest.roomdb.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.junit.runner.manipulation.Ordering.Context
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppmodule {

    @Provides
    @Named("testdatabase")
    @Singleton
    fun injectinmemoryroom(@ApplicationContext context: android.content.Context) = Room.inMemoryDatabaseBuilder(context,ArtDatabase::class.java)
        .allowMainThreadQueries().build()
}