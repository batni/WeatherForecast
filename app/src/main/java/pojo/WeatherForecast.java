package pojo;

/**
 * Created by bprabhanjan on 12-Feb-16.
 */
public class WeatherForecast {
    double geoLatitude;
    double getLongitude;
    String currentLocation;
    CurrentForecast currentForecast;
    HourForecast hourForecast;
    DayForecast dayForecast;

    public CurrentForecast getCurrentForecast() {
        return currentForecast;
    }

    public void setCurrentForecast(CurrentForecast currentForecast) {
        this.currentForecast = currentForecast;
    }

    public HourForecast getHourForecast() {
        return hourForecast;
    }

    public void setHourForecast(HourForecast hourForecast) {
        this.hourForecast = hourForecast;
    }

    public DayForecast getDayForecast() {
        return dayForecast;
    }

    public void setDayForecast(DayForecast dayForecast) {
        this.dayForecast = dayForecast;
    }

    public double getGeoLatitude() {
        return geoLatitude;
    }

    public void setGeoLatitude(double geoLatitude) {
        this.geoLatitude = geoLatitude;
    }

    public double getGetLongitude() {
        return getLongitude;
    }

    public void setGetLongitude(double getLongitude) {
        this.getLongitude = getLongitude;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
