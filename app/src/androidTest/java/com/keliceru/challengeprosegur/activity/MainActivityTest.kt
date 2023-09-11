package com.keliceru.challengeprosegur.activity

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.keliceru.challengeprosegur.R
import com.keliceru.challengeprosegur.domain.common.successMessage
import com.keliceru.challengeprosegur.presentation.MainActivity
import com.keliceru.challengeprosegur.util.atPosition
import com.keliceru.challengeprosegur.util.waitUntilViewIsDisplayed
import com.keliceru.challengeprosegur.util.withViewParent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testInputDataAndMatchDatabaseData() {

        hiltRule.inject()

        onView(
            allOf(
                withId(R.id.act_select),
                withViewParent(withId(R.id.sv_room_number), 2)
            )
        )
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(click());

        onData(CoreMatchers.equalTo("203")).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(
            Matchers.allOf(
                withId(R.id.act_select),
                ViewMatchers.withHint(R.string.day_of_week)
            )
        ).perform(
            click()
        )

        onData(CoreMatchers.equalTo("Lunes")).inRoot(RootMatchers.isPlatformPopup())
            .perform(click());


        onView(
            Matchers.allOf(
                withId(R.id.tie_text),
                ViewMatchers.withHint(R.string.movie)
            )
        ).perform(
            ViewActions.typeText("movie")
        )

        onView(
            Matchers.allOf(
                withId(R.id.tie_text),
                ViewMatchers.withHint(R.string.ticket_price)
            )
        ).perform(
            ViewActions.typeText("12")
        )

        onView(
            Matchers.allOf(
                withId(R.id.tie_text),
                ViewMatchers.withHint(R.string.ticket_amount)
            )
        ).perform(
            ViewActions.typeText("4"), closeSoftKeyboard()
        )

        onView(
            Matchers.allOf(
                withId(R.id.tie_text),
                ViewMatchers.withHint(R.string.person_identification)
            )
        ).perform(
            ViewActions.typeText("14566745"), ViewActions.closeSoftKeyboard()
        )

        onView(withId(R.id.bn_register)).perform(click())

        waitUntilViewIsDisplayed(withId(R.id.tv_message))

        onView(withId(R.id.tv_result)).check(matches(ViewMatchers.withText(successMessage)))

        onView(withId(R.id.registeredUsersFragment)).perform(click());

        onView(withId(R.id.rv_main)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_main))
            .check(matches(atPosition(0, hasDescendant(withText("DNI: 14566745")))))

        onView(withId(R.id.rv_main)).check(
            matches(
                atPosition(
                    0,
                    hasDescendant(withId(R.id.btn_discard))
                )
            )
        )
    }
}