package com.example.elmnewsapp.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.elmnewsapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.robolectric.annotation.Config


@ExperimentalPagingApi
@HiltAndroidTest
@Config(application = HiltTestApplication::class, maxSdk = 30)
@RunWith (AndroidJUnit4::class)
class NewsListFragmentTest {
    @get:Rule()
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Test
    fun testNavigation(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val scenario = launchFragmentInContainer<NewsListFragment>()

        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.news_navigation_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<NewsViewHolder>(0, click()))
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.newsDetailsFragment)
    }

}