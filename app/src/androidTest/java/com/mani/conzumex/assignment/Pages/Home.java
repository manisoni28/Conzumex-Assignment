package com.mani.conzumex.assignment.Pages;


import com.mani.conzumex.assignment.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;


public class Home {

    public Home(){
        onView(allOf(withId(R.id.action_bar)))
                .check(matches(notNullValue())).check(matches(isDisplayed()));
    }

    public AddTask navigateToAddTask() {
        onView(allOf(withId(R.id.imageButton))).perform(click());
        return new AddTask();
    }
}
