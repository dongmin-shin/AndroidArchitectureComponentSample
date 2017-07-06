package com.example.acsha.androidarchitecturecomponentsample.sampletimer;

import com.example.acsha.androidarchitecturecomponentsample.R;
import com.example.acsha.androidarchitecturecomponentsample.sampletimer.TimerModel;
import com.example.acsha.androidarchitecturecomponentsample.sampletimer.TimerSampleActivity;
import com.example.acsha.androidarchitecturecomponentsample.sampletimer.TimerViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import utils.ElapsedTimeIdlingResource;
import utils.SimpleIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TimerSampleActivityTest {

    @Rule
    public ActivityTestRule<TimerSampleActivity> activityRule = new ActivityTestRule<>(TimerSampleActivity.class);

    @Test
    public void test_check_title() {
        final SimpleIdlingResource idlingRes = new SimpleIdlingResource();
        SimpleIdlingResource.setIdlingResource(idlingRes);

        TimerSampleActivity activity = activityRule.getActivity();
        TimerViewModel viewModel = ViewModelProviders.of(activity).get(TimerViewModel.class);

        viewModel.getTimerModelLiveData().observeForever(new Observer<TimerModel>() {
            @Override
            public void onChanged(@Nullable TimerModel timerModel) {
                if (timerModel != null) {
                    idlingRes.setIdleNow(true);
                }
            }
        });

        onView(ViewMatchers.withId(R.id.timer_title)).check(ViewAssertions.matches(withText("Hello World!")));
    }

    @Test
    public void test_timer_text_5sec() {
        IdlingResource idlingResource = ElapsedTimeIdlingResource.startTiming(5000);
        onView(withId(R.id.timer_count)).perform().check(ViewAssertions.matches(withText("5")));

        ElapsedTimeIdlingResource.stopTiming(idlingResource);
    }


}
