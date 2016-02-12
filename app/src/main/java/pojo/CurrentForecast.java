package pojo;

/**
 * Created by bprabhanjan on 12-Feb-16.
 */
public class CurrentForecast {

    double apparentTemperature;

    double cloudCover;

    double dewPoint;
    //56.12
    double humidity;
    //0.73

    double ozone;
    //274.34
    double precipIntensity;
    //0.001
    double precipProbability;
    //0.01

    double pressure;
    //1016.6
    String summary;
    //"Mostly Cloudy"
    double temperature;

    double visibility;
    //64.91
    int currentTime;
    //1455145840
    int windBearing;
    //169
    double windSpeed;
    //6.86


    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility =  visibility;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getOzone() {
        return ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getCurrentTime() {

        return currentTime;
    }

    public void setCurrentTime(int currentTime) {

        this.currentTime = currentTime;

    }

    public int getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(int windBearing) {
        this.windBearing = windBearing;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
