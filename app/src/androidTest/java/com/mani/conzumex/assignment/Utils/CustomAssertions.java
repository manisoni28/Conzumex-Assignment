package com.mani.conzumex.assignment.Utils;

import android.support.test.espresso.ViewInteraction;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.mani.conzumex.assignment.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;


/**
 * Created by Daniel Blokus on 30.09.2017.
 */

public class CustomAssertions {

    public static void shouldDisplayNewTaskInTheList(String taskName) {
        ViewInteraction textView = onView(
                allOf(withId(R.id.recycler_view_s),
                        childAtPosition(
                                allOf(withId(R.id.cardview),
                                        childAtPosition(
                                                withId(R.id.cardview),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText(taskName)));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
