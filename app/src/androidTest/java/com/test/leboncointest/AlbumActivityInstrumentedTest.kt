package com.test.leboncointest

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.test.leboncointest.data.models.Album
import com.test.leboncointest.ui.albumlist.AlbumAdapter
import com.test.leboncointest.ui.albumlist.AlbumDetailFragment
import com.test.leboncointest.ui.albumlist.AlbumListFragment
import com.test.leboncointest.utils.BundleKeys
import com.test.leboncointest.utils.MatcherUtils.hasItemCount
import com.test.leboncointest.utils.ViewActionUtils.waitUntil
import org.hamcrest.Matchers.greaterThan

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class AlbumActivityInstrumentedTest {

    private val context by lazy {
        ApplicationProvider.getApplicationContext<Context>()
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.test.leboncointest", appContext.packageName)
    }

    @Test
    fun testAlbumDetailFragmentTitleView() {
        val album = Album(
            albumId = 0,
            title = "title",
            url = "url",
            thumbnailUrl = "thumbnailUrl"
        )

        val bundle = bundleOf(BundleKeys.album to album)
        launchFragmentInContainer<AlbumDetailFragment>(bundle)

        onView(withId(R.id.albumTitleView)).check(matches(isDisplayed()))
        onView(withId(R.id.albumTitleView)).check(matches(withText(album.title)))
    }

    @Test
    fun testNavigationToAlbumDetailFragment() {
        val navController = TestNavHostController(context)

        val albumList = launchFragmentInContainer<AlbumListFragment>()

        albumList.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.recyclerView))
            .perform(
                waitUntil(hasItemCount(greaterThan(0))),
                actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(0, click()))
        assertEquals(navController.currentDestination?.id, R.id.albumDetailFragment)
    }

    @Test
    fun testBackNavigationToAlbumListFragment() {
        val navController = TestNavHostController(context)
        val albumList = launchFragmentInContainer<AlbumListFragment>()

        albumList.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.recyclerView))
            .perform(
                waitUntil(hasItemCount(greaterThan(0))),
                actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(0, click()))

        runOnUiThread {
            navController.navigateUp()
        }
        assertEquals(navController.currentDestination?.id, R.id.albumListFragment)
    }
}