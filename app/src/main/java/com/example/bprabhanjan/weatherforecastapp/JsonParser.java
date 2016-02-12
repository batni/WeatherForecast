package com.example.bprabhanjan.weatherforecastapp;

import org.json.JSONException;
import org.json.JSONObject;

import pojo.CurrentForecast;
import pojo.DayForecast;
import pojo.HourForecast;
import pojo.WeatherForecast;

/**
 * Created by bprabhanjan on 12-Feb-16.
 */
public class JsonParser {

    public static WeatherForecast getWeather(String data) throws JSONException {
        WeatherForecast weatherForecast = new WeatherForecast();
        JSONObject jObj = new JSONObject(data);

        //Null check to prevent null pointer exception caused due to missing Json tag
        if(!jObj.isNull("latitude")) {
            weatherForecast.setGeoLatitude(jObj.getDouble("latitude"));
        }
        if(!jObj.isNull("longitude")) {
            weatherForecast.setGetLongitude(jObj.getDouble("longitude"));
        }
        if(!jObj.isNull("timezone")) {
            weatherForecast.setCurrentLocation(jObj.getString("timezone"));
        }

        CurrentForecast currentForecast =  new CurrentForecast();
        JSONObject currentForecastObj = jObj.getJSONObject("currently");

        if(!currentForecastObj.isNull("time")) {
            currentForecast.setCurrentTime(currentForecastObj.getInt("time"));
        }
        if(!currentForecastObj.isNull("summary")) {
            currentForecast.setSummary(currentForecastObj.getString("summary"));
        }
        if(!currentForecastObj.isNull("apparentTemperature")) {
            currentForecast.setApparentTemperature(currentForecastObj.getDouble("apparentTemperature"));
        }
        if(!currentForecastObj.isNull("cloudCover")) {
            currentForecast.setCloudCover(currentForecastObj.getDouble("cloudCover"));
        }
        if(!currentForecastObj.isNull("dewPoint")) {
            currentForecast.setDewPoint(currentForecastObj.getDouble("dewPoint"));
        }
        if(!currentForecastObj.isNull("humidity")) {
            currentForecast.setHumidity(currentForecastObj.getDouble("humidity"));
        }
        if(!currentForecastObj.isNull("ozone")) {
            currentForecast.setOzone(currentForecastObj.getDouble("ozone"));
        }
        if(!currentForecastObj.isNull("precipIntensity")) {
            currentForecast.setPrecipIntensity(currentForecastObj.getDouble("precipIntensity"));
        }
        if(!currentForecastObj.isNull("precipProbability")) {
            currentForecast.setPrecipProbability(currentForecastObj.getDouble("precipProbability"));
        }
        if(!currentForecastObj.isNull("pressure")) {
            currentForecast.setPressure(currentForecastObj.getDouble("pressure"));
        }
        if(!currentForecastObj.isNull("temperature")) {
            currentForecast.setTemperature(currentForecastObj.getDouble("temperature"));
        }
        if(!currentForecastObj.isNull("visibility")){
            currentForecast.setVisibility(currentForecastObj.getDouble("visibility"));
        }
        if(!currentForecastObj.isNull("windBearing")) {
            currentForecast.setWindBearing(currentForecastObj.getInt("windBearing"));
        }
        if(!currentForecastObj.isNull("windSpeed")) {
            currentForecast.setWindSpeed(currentForecastObj.getDouble("windSpeed"));
        }

        weatherForecast.setCurrentForecast(currentForecast);

        HourForecast hourForecast = new HourForecast();
        JSONObject jsonHourForecastObj = jObj.getJSONObject("hourly");
        if(!currentForecastObj.isNull("summary")) {
            hourForecast.setHourSummary(jsonHourForecastObj.getString("summary"));
        }

        weatherForecast.setHourForecast(hourForecast);

        DayForecast dayForecast = new DayForecast();
        JSONObject jsonDayForecastObj = jObj.getJSONObject("hourly");

        if(!currentForecastObj.isNull("summary")) {
            dayForecast.setDaySummary(jsonHourForecastObj.getString("summary"));
        }

        weatherForecast.setDayForecast(dayForecast);

        return weatherForecast;
    }
}
