package com.example.acsha.androidarchitecturecomponentsample.samplerecycler;

import com.example.acsha.androidarchitecturecomponentsample.R;

import org.junit.Rule;
import org.junit.Test;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */
public class StickerRecyclerSampleActivityTest {

    @Rule
    public ActivityTestRule<StickerRecyclerSampleActivity> activityTestRule = new ActivityTestRule<>(StickerRecyclerSampleActivity.class);

    @Test
    public void test_onClickFirstItem() {
        StickerRecyclerSampleActivity activity = activityTestRule.getActivity();

        onView(withId(R.id.sticker_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withText("StickerId: 0")).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void test_onClickLastItem() {
        StickerRecyclerSampleActivity activity = activityTestRule.getActivity();

        onView(withId(R.id.sticker_list)).perform(RecyclerViewActions.actionOnItemAtPosition(7, click()));
        onView(withText("StickerId: 7")).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

}