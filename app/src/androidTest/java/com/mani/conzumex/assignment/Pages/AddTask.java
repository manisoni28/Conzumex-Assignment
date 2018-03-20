package com.mani.conzumex.assignment.Pages;



import com.mani.conzumex.assignment.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;


public class AddTask {

    public static final String TASK_NAME = "test";

    public AddTask() {
        onView(allOf(withId(R.id.activityName)))
                .check(matches(notNullValue()))
                .check(matches(isDisplayed()));
    }

    public Home addTask() {
        isTaskNameInputDisplayed();
        fillTaskName();
        closeSoftKeyboard();
        isAddButtonDisplayed();
        clickAddButton();
        return new Home();
    }

    private void isTaskNameInputDisplayed() {
        onView(allOf(withId(R.id.activityName), isDisplayed()));
    }

    private void fillTaskName() {
        onView((withId(R.id.activityName)))
                .perform(replaceText(TASK_NAME));
    }

    private void isAddButtonDisplayed() {
        onView(withId(R.id.imageButton))
                .check(matches(notNullValue()))
                .check(matches(isDisplayed()));
    }

    private void clickAddButton() {
        onView(withId(R.id.imageButton))
                .perform(click());
    }

}
