package com.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.launchFragmentInHiltContainer
import com.mesutdurmuser.artbooktestingexample.R
import com.mesutdurmuser.artbooktestingexample.adapter.ImageRecyclerAdapter
import com.mesutdurmuser.artbooktestingexample.getOrAwaitValue
import com.mesutdurmuser.artbooktestingexample.view.ArtFragmentFactory
import com.mesutdurmuser.artbooktestingexample.view.ImageApiFragment
import com.mesutdurmuser.artbooktestingexample.viewmodel.ArtViewModel
import com.repo.FakeArtRepositoryTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImageApiFragmentTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory : ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun selectImage(){

        val navController = Mockito.mock(NavController::class.java)
        val selectedUrl = "test.com"
        val testViewModel = ArtViewModel(FakeArtRepositoryTest())

        launchFragmentInHiltContainer<ImageApiFragment>(
            factory = fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
            viewModel = testViewModel
            imageRecyclerAdapter.images = listOf(selectedUrl)
        }

        Espresso.onView(withId(R.id.imageRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageRecyclerAdapter.ImageViewHolder>(
                0,click()
            )
        )

        Mockito.verify(navController).popBackStack()

        assertThat(testViewModel.selectedImageUrl.getOrAwaitValue()).isEqualTo(selectedUrl)
    }
}