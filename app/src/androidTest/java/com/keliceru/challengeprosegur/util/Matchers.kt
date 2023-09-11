package com.keliceru.challengeprosegur.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
    checkNotNull(itemMatcher)
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position)
                ?: // has no item on such position
                return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}

fun withViewParent(parentMatcher: Matcher<View?>, hierarchyLevel: Int): Matcher<View?>? {
    return WithParentMatcherApp(parentMatcher, hierarchyLevel)
}

private class WithParentMatcherApp constructor(
    parentMatcher: Matcher<View?>,
    hierarchyLevel: Int,
) :
    TypeSafeMatcher<View?>() {
    private val parentMatcher: Matcher<View?>
    private val hierarchyLevel: Int

    init {
        this.parentMatcher = parentMatcher
        this.hierarchyLevel = hierarchyLevel
    }

    override fun describeTo(description: Description) {
        description.appendText("has parent matching: ")
        parentMatcher.describeTo(description)
    }

    override fun matchesSafely(view: View?): Boolean {
        var viewParent = view?.parent
        for (index in 1 until hierarchyLevel) {
            viewParent = viewParent?.parent
        }
        return parentMatcher.matches(viewParent)
    }
}