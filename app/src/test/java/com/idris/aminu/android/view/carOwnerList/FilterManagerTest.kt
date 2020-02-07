package com.idris.aminu.android.view.carOwnerList

import com.idris.aminu.android.models.FilterElement
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FilterManagerTest {

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val fakeList = FakeData.data


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun filter_should_return_correct_list_size_for_different_gender() {
        runBlocking {
            launch(Dispatchers.Main) {

                val filterManager = FilterManager()
                val criteria = FilterElement(
                    1,
                    2004,
                    2005,
                    "female",
                    listOf("Bolivia", "France"),
                    listOf("Puce")
                )

                val filteredList = filterManager.filter(fakeList, criteria)
                assertEquals(1, filteredList.size)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun filter_should_return_correct_list_size_where_all_countries() {
        runBlocking {
            launch(Dispatchers.Main) {

                val filterManager = FilterManager()
                val criteria = FilterElement(
                    1,
                    2004,
                    2005,
                    "",
                    listOf(),
                    listOf()
                )

                val filteredList = filterManager.filter(fakeList, criteria)
                assertEquals(2, filteredList.size)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun filter_should_return_correct_list_size_where_all_color_countries_and_all_gender() {
        runBlocking {
            launch(Dispatchers.Main) {

                val filterManager = FilterManager()
                val criteria = FilterElement(
                    1,
                    2004,
                    2005,
                    "female",
                    listOf(),
                    listOf(
                        "Green",
                        "Violet",
                        "Yellow",
                        "Blue",
                        "Teal",
                        "Maroon",
                        "Red",
                        "Aquamarine",
                        "Orange",
                        "Mauv"
                    )
                )

                val filteredList = filterManager.filter(fakeList, criteria)
                assertEquals(1, filteredList.size)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun filter_should_return_correct_list_size_where_all_color_and_all_gender() {
        runBlocking {
            launch(Dispatchers.Main) {

                val filterManager = FilterManager()
                val criteria = FilterElement(
                    1,
                    1996,
                    2005,
                    "",
                    listOf(
                        "China",
                        "South Africa",
                        "france",
                        "Mexico",
                        "Japan",
                        "Estonia",
                        "Colombia",
                        "China"
                    ),
                    listOf()
                )

                val filteredList = filterManager.filter(fakeList, criteria)
                assertEquals(1, filteredList.size)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun filter_should_return_correct_list_size_for_no_match_filter() {
        runBlocking {
            launch(Dispatchers.Main) {

                val filterManager = FilterManager()
                val criteria = FilterElement(
                    1,
                    2021,
                    2025,
                    "",
                    listOf(
                        "China",
                        "South Africa",
                        "france",
                        "Mexico",
                        "Japan",
                        "Estonia",
                        "Colombia",
                        "China"
                    ),
                    listOf()
                )

                val filteredList = filterManager.filter(fakeList, criteria)
                assertEquals(0, filteredList.size)
            }
        }
    }


}