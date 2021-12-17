package com.cornez.tapbuttoncounter;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.InstrumentationRegistry.getInstrumentation;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;


import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private Solo solo;


    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
    }
    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    @Test
    public void testAddNote() throws Exception {

        solo.clickOnView(solo.getView(R.id.button));
        solo.clickOnView(solo.getView(R.id.button));
        solo.clickOnView(solo.getView(R.id.button));
        solo.clickOnView(solo.getView(R.id.button6));
        TextView tmp = (TextView) solo.getView(R.id.textView6);
        if(tmp.getText().toString().equals("25.9"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
    }

    @Test
    public void testEditNoteTitle() throws Exception {
        solo.clickOnView(solo.getView(R.id.button2));
        solo.clickOnView(solo.getView(R.id.button2));
        solo.clickOnView(solo.getView(R.id.button6));

        TextView tmp = (TextView) solo.getView(R.id.textView6);

        if(tmp.getText().toString().equals("31.0"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }
    }

    private void deleteNotes() {
       /* //Click on first item in List
        solo.clickInList(1);
        //Click on delete action menu item
        solo.clickOnView(solo.getView(com.example.android.notepad.R.id.menu_delete));
        //Long click first item in List
        solo.clickLongInList(1);
        //Click delete
        solo.clickOnText(solo.getString(R.string.menu_delete));*/
    }
}