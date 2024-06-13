package zfr.mobile.persuhuanduniawii;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {

    @SerializedName("temperature")
    private double temperature;

    @SerializedName("windspeed")
    private double windspeed;

    @SerializedName("weathercode")
    private int weatherCode;

    // getters and setters

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }
}

