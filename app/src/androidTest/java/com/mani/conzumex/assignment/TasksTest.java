package com.mani.conzumex.assignment;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mani.conzumex.assignment.Pages.Home;
import com.mani.conzumex.assignment.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mani.conzumex.assignment.Pages.AddTask.TASK_NAME;
import static com.mani.conzumex.assignment.Utils.CustomAssertions.shouldDisplayNewTaskInTheList;


@RunWith(AndroidJUnit4.class)
public class TasksTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createDefaultTask_ShouldDisplayHomeView() {
        new Home().navigateToAddTask().addTask();
        shouldDisplayNewTaskInTheList(TASK_NAME);
    }
}
