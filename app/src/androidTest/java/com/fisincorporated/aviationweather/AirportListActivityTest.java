package com.fisincorporated.aviationweather;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.fisincorporated.aviationweather.airports.AirportListActivity;
import com.fisincorporated.aviationweather.app.AppPreferences;
import com.fisincorporated.aviationweather.app.BaseTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class AirportListActivityTest extends BaseTest {

    @Rule
    public ActivityTestRule<AirportListActivity> mActivityRule =
            new ActivityTestRule<>(AirportListActivity.class, true, false);

    @Before
    @Override
    public void setup() {
        Log.d("Setup", "Setup called");
        super.setup();
        setSharedPreferenceAirportList( "");
    }


    @Test
    public void addAirports() {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(targetContext, AirportListActivity.class);

        mActivityRule.launchActivity(intent);
        onView(withId(R.id.activity_airport_codes)).perform(typeText(airportList));
        onView(withId(R.id.activity_airport_codes)).check(matches(withText(airportList)));

        onView(withId(R.id.activity_airport_save)).perform(click());

        SharedPreferences sp = getSharedPreferences();
        assertEquals(sp.getString(AppPreferences.getAirportListKey(), ""), airportList);

    }
}
