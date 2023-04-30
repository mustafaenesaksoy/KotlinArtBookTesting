package com.enesaksoy.ikotlinartbooktest.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.enesaksoy.ikotlinartbooktest.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import com.enesaksoy.ikotlinartbooktest.R

@MediumTest
@HiltAndroidTest
class ArtFragmentTest {

    @get:Rule
    var hitRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory
    @Before
    fun setup(){
        hitRule.inject()
    }
    val navController = Mockito.mock(NavController::class.java)
    @Test
    fun fabtesti(){
        launchFragmentInHiltContainer<ArtFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(click())
        Mockito.verify(navController).navigate(ArtFragmentDirections.actionArtFragmentToDetailsFragment())
    }

}