package com.keliceru.challengeprosegur.fragments

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.keliceru.challengeprosegur.R
import com.keliceru.challengeprosegur.domain.common.successMessage
import com.keliceru.challengeprosegur.presentation.fragments.RegisterFragment
import com.keliceru.challengeprosegur.util.launchFragmentInHiltContainer
import com.keliceru.challengeprosegur.util.waitUntilViewIsDisplayed
import com.keliceru.challengeprosegur.util.withViewParent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class RegisterFragmentHiltTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun launchRegisterFragment() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<RegisterFragment> {
            navController.setGraph(R.navigation.app_navigation)
            navController.setCurrentDestination(R.id.ticketRegisterFragment)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.sv_room_number)).check(matches(isDisplayed()))

        onView(withId(R.id.sv_week_day)).check(matches(isDisplayed()))

        onView(withId(R.id.tf_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.tf_unit_price)).check(matches(isDisplayed()))

        onView(withId(R.id.tf_amount)).check(matches(isDisplayed()))

        onView(withId(R.id.tf_dni)).check(matches(isDisplayed()))

        onView(withId(R.id.bn_register)).check(matches(isDisplayed()))
    }

    @Test
    fun testClickRegisterButton() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<RegisterFragment> {
            navController.setGraph(R.navigation.app_navigation)
            navController.setCurrentDestination(R.id.ticketRegisterFragment)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(
            allOf(
                withId(R.id.act_select),
                withViewParent(withId(R.id.sv_room_number), 2)
            )
        )
            .check(matches(isDisplayed()))
            .perform(click());

        /*onView(Matchers.allOf(withId(R.id.act_select), withHint(R.string.room_number))).perform(
            click()
        )*/

        onData(equalTo("203")).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(Matchers.allOf(withId(R.id.act_select), withHint(R.string.day_of_week))).perform(
            click()
        )

        onData(equalTo("Lunes")).inRoot(RootMatchers.isPlatformPopup()).perform(click());


        onView(Matchers.allOf(withId(R.id.tie_text), withHint(R.string.movie))).perform(
            typeText("movie")
        )

        onView(Matchers.allOf(withId(R.id.tie_text), withHint(R.string.ticket_price))).perform(
            typeText("12")
        )

        onView(Matchers.allOf(withId(R.id.tie_text), withHint(R.string.ticket_amount))).perform(
            typeText("4")
        )

        onView(
            Matchers.allOf(
                withId(R.id.tie_text),
                withHint(R.string.person_identification)
            )
        ).perform(
            typeText("14566745"), closeSoftKeyboard()
        )

        onView(withId(R.id.bn_register)).perform(click())

        waitUntilViewIsDisplayed(withId(R.id.tv_message))

        onView(withId(R.id.tv_result)).check(matches(withText(successMessage)))

    }
}