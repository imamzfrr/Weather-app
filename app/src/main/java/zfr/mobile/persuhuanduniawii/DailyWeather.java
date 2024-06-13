package zfr.mobile.persuhuanduniawii;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyWeather {

    @SerializedName("temperature_2m_max")
    private List<Double> temperatureMax;

    @SerializedName("time")
    private List<String> time;

    @SerializedName("weathercode")
    private List<Integer> weatherCode;

    // getters and setters

    public List<Double> getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(List<Double> temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Integer> getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(List<Integer> weatherCode) {
        this.weatherCode = weatherCode;
    }
}
