package com.example.muhanned_task.Activities.homeActivity;

import android.widget.AutoCompleteTextView;

import com.example.muhanned_task.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HomeActivityTest  {
    AutoCompleteTextView etBypostion;
    AutoCompleteTextView etBylocation;
    HomeActivity mActivity;

    @Before
    public void setUp() throws Exception {
        etBypostion = (AutoCompleteTextView) mActivity.findViewById(R.id.etBypostion);
        etBylocation = (AutoCompleteTextView) mActivity.findViewById(R.id.etBylocation);

    }

    @Test
    public void testfilterbypostion() {
        String actual = etBypostion+"";

        assertEquals("Cannot get number on this fields",actual);


    }
    @Test
    public void testfilterbylocaiton() {
        String actual = etBylocation+"";

        assertEquals("Cannot get number on this fields",actual);
    }

    @After
    public void tearDown() throws Exception {
    }
}