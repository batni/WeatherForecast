package com.example.bprabhanjan.weatherforecastapp;

import org.junit.Test;

import java.io.File;
import java.io.InputStream;

import commonconstants.CommonConstants;
import httputils.HttpUtils;
import pojo.WeatherForecast;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void checkTimeConversion() throws Exception {
        MainActivity mn  =  new MainActivity();
        String timeCheck = mn.formatTimeStamp(1455145840);
        String expected = "20:12:25";
        assertEquals("Format time int value to Time",expected, timeCheck);
    }
    @Test
    public void checkAPIResponse() throws Exception {

        String testFile = "testDataFile.txt";

        String data = ( (new HttpUtils()).getWeatherForecastData(CommonConstants.DEFAULT_URL));

        String expectedValue = "";
        assertEquals("Format time int value to Time",expectedValue, data);
    }

}