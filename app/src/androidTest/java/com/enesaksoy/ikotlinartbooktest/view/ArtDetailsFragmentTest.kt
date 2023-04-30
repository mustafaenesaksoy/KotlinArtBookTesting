package com.enesaksoy.ikotlinartbooktest.view

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.enesaksoy.ikotlinartbooktest.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import com.enesaksoy.ikotlinartbooktest.R
import com.enesaksoy.ikotlinartbooktest.repo.ArtRepositoryTeste
import com.enesaksoy.ikotlinartbooktest.viewmodel.ArtViewModel
import com.google.common.truth.Truth.assertThat

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArtDetailsFragmentTest {

    @get:Rule
    val hitRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory
    @Before
    fun setup(){
        hitRule.inject()
    }

    @Test
    fun detailsfragmenttoapifragment(){
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<DetailsFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).perform(click())
        Mockito.verify(navController).navigate(DetailsFragmentDirections.actionDetailsFragmentToApiFragment())

    }

    @Test
    fun saveTest(){
        val testviewmodel = ArtViewModel(ArtRepositoryTeste())
        launchFragmentInHiltContainer<DetailsFragment>(factory = fragmentFactory){

        }
        Espresso.onView(ViewMatchers.withId(R.id.nameText)).perform(replaceText("deneme"))
        Espresso.onView(ViewMatchers.withId(R.id.yearText)).perform(replaceText("year"))
        Espresso.onView(ViewMatchers.withId(R.id.artistText)).perform(replaceText("artist"))
        Espresso.onView(ViewMatchers.withId(R.id.saveButton)).perform(click())
    }
}