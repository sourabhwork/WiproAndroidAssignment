package com.wipro.android.wiproandroidassignment

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test


class UITests {

    @get:Rule
    var mActivityRule: ActivityTestRule<FactsActivity> = ActivityTestRule(FactsActivity::class.java)
    @Test
    fun ToolbarTitleTest() {

        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withResourceName("action_bar"))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText("About Canada")))
    }

    @Test
    fun recyclerViewItemCountTest() {
        val recyclerView = mActivityRule.activity.findViewById(R.id.listFacts) as RecyclerView
        assertEquals(14, recyclerView.adapter!!.itemCount)
    }
}