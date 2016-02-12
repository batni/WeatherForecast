package com.example.bprabhanjan.weatherforecastapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.location.LocationManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;

import commonconstants.CommonConstants;
import httputils.HttpUtils;
import pojo.WeatherForecast;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    String url = "";
    TextView timeZone;
    ListView listview;
    private ArrayAdapter<String> listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        listview = (ListView) findViewById(R.id.listView);

        setSupportActionBar(toolbar);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager
                .getLastKnownLocation(bestProvider);
        // Check for location value, since location is returned by the GPS provider
        if(null != location){
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(CommonConstants.BASE_URL);
            stringBuilder.append(CommonConstants.APPLICATION_KEY);
            stringBuilder.append(CommonConstants.SLASH);
            stringBuilder.append(String.valueOf(currentLatitude));
            stringBuilder.append(CommonConstants.COMMA);
            stringBuilder.append(String.valueOf(currentLongitude));
            url = stringBuilder.toString();
        }
        else{
            //Use default url to get the response
            url = CommonConstants.DEFAULT_URL;
        }

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, WeatherForecast> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, CommonConstants.PROGRESS_DIALOG_MAIN_TEXT, CommonConstants.PROGRESS_DIALOG_SUB_TEXT, true, false);
        }

        @Override
        protected WeatherForecast doInBackground(String... params) {

            WeatherForecast weatherForecast = new WeatherForecast();
            String weatherForecastData = ( (new HttpUtils()).getWeatherForecastData(url));

            try {
                weatherForecast = JsonParser.getWeather(weatherForecastData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weatherForecast;

        }

        @Override
        protected void onPostExecute(WeatherForecast weatherForecast) {
            super.onPostExecute(weatherForecast);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            ArrayList<String> list = new ArrayList<String>();
            list.add(CommonConstants.CURRENT_LOCATION + weatherForecast.getCurrentLocation().toString());
            list.add(CommonConstants.CURRENT_LONGITUDE + String.valueOf(weatherForecast.getGetLongitude()));
            list.add(CommonConstants.CURRENT_LATITUDE + String.valueOf(weatherForecast.getGetLongitude()));
            String timeFormat =  formatTimeStamp(weatherForecast.getCurrentForecast().getCurrentTime());
            list.add(CommonConstants.TIME + timeFormat);
            list.add(CommonConstants.FORECAST_SUMMARY + String.valueOf(weatherForecast.getCurrentForecast().getSummary()));
            list.add(CommonConstants.APPARENT_TEMPERATURE + String.valueOf(weatherForecast.getCurrentForecast().getApparentTemperature()));
            list.add(CommonConstants.CLOUD_COVER + String.valueOf(weatherForecast.getCurrentForecast().getCloudCover()));
            list.add(CommonConstants.DEW_POINT + String.valueOf(weatherForecast.getCurrentForecast().getDewPoint()));
            list.add(CommonConstants.HUMIDITY + String.valueOf(weatherForecast.getCurrentForecast().getHumidity()));
            list.add(CommonConstants.OZONE + String.valueOf(weatherForecast.getCurrentForecast().getOzone()));
            list.add(CommonConstants.PRECIPITATION_INTENSITY + String.valueOf(weatherForecast.getCurrentForecast().getPrecipIntensity()));
            list.add(CommonConstants.PRECIPITATION_PROBABILITY + String.valueOf(weatherForecast.getCurrentForecast().getPrecipProbability()));
            list.add(CommonConstants.PRESSURE + String.valueOf(weatherForecast.getCurrentForecast().getPressure()));
            list.add(CommonConstants.TEMPERATURE + String.valueOf(weatherForecast.getCurrentForecast().getTemperature()));
            list.add(CommonConstants.VISIBILITY + String.valueOf(weatherForecast.getCurrentForecast().getVisibility()));
            list.add(CommonConstants.WIND_BEARING + String.valueOf(weatherForecast.getCurrentForecast().getWindBearing()));
            list.add(CommonConstants.WIND_SPEED + String.valueOf(weatherForecast.getCurrentForecast().getWindSpeed()));
            list.add(CommonConstants.HOUR_SUMMARY + String.valueOf(weatherForecast.getHourForecast().getHourSummary()));
            list.add(CommonConstants.DAY_SUMMARY + String.valueOf(weatherForecast.getDayForecast().getDaySummary()));
            listAdapter = new ArrayAdapter<String>(context, R.layout.row_element, list);

            listview.setAdapter(listAdapter);


        }


    }

    public String formatTimeStamp(int currentTimeValue) {
        int second = (currentTimeValue / 1000) % 60;
        int minute = (currentTimeValue / (1000 * 60)) % 60;
        int hour = (currentTimeValue / (1000 * 60 * 60)) % 24;
        StringBuilder timeFormatStringBuilder= new StringBuilder();
        timeFormatStringBuilder.append(String.valueOf(hour));
        timeFormatStringBuilder.append(String.valueOf(CommonConstants.COLON));
        timeFormatStringBuilder.append(String.valueOf(minute));
        timeFormatStringBuilder.append(String.valueOf(CommonConstants.COLON));
        timeFormatStringBuilder.append(String.valueOf(second));
        return timeFormatStringBuilder.toString();
    }
}
