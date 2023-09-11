package com.keliceru.challengeprosegur.util

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.ViewFinder
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.keliceru.challengeprosegur.HiltTestActivity
import dagger.hilt.internal.Preconditions
import org.hamcrest.Matcher
import java.lang.reflect.Field

inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = androidx.fragment.testing.manifest.R.style.FragmentScenarioEmptyFragmentActivityTheme,
    crossinline action: Fragment.() -> Unit = {},
) {
    val startActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HiltTestActivity::class.java
        )
    ).putExtra(
        "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
        themeResId
    )

    ActivityScenario.launch<HiltTestActivity>(startActivityIntent).onActivity { activity ->
        val fragment: Fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(T::class.java.classLoader),
            T::class.java.name
        )
        fragment.arguments = fragmentArgs
        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()

        fragment.action()
    }
}


fun waitUntilViewIsDisplayed(matcher: Matcher<View?>) {
    val idlingResource: IdlingResource = ViewIdlingResource(matcher, ViewMatchers.isDisplayed())
    try {
        IdlingRegistry.getInstance().register(idlingResource)
        // First call to onView is to trigger the idler.
        Espresso.onView(ViewMatchers.withId(0)).check(ViewAssertions.doesNotExist())
    } finally {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}

class ViewIdlingResource(
    private val viewMatcher: Matcher<View?>?,
    private val idleMatcher: Matcher<View?>?,
) : IdlingResource {

    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun isIdleNow(): Boolean {
        val view: View? = getView(viewMatcher)
        val isIdle: Boolean = idleMatcher?.matches(view) ?: false
        if (isIdle) {
            resourceCallback?.onTransitionToIdle()
        }
        return isIdle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = resourceCallback
    }

    override fun getName(): String {
        return "$this ${viewMatcher.toString()}"
    }

    private fun getView(viewMatcher: Matcher<View?>?): View? {
        return try {
            val viewInteraction = Espresso.onView(viewMatcher)
            val finderField: Field? = viewInteraction.javaClass.getDeclaredField("viewFinder")
            finderField?.isAccessible = true
            val finder = finderField?.get(viewInteraction) as ViewFinder
            finder.view
        } catch (e: Exception) {
            null
        }
    }
}