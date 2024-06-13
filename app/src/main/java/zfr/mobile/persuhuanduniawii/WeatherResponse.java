package zfr.mobile.persuhuanduniawii;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

        @SerializedName("current_weather")
        private CurrentWeather currentWeather;

        @SerializedName("daily")
        private DailyWeather dailyWeather;

        // getters and setters

        public CurrentWeather getCurrentWeather() {
            return currentWeather;
        }

        public void setCurrentWeather(CurrentWeather currentWeather) {
            this.currentWeather = currentWeather;
        }

        public DailyWeather getDailyWeather() {
            return dailyWeather;
        }

        public void setDailyWeather(DailyWeather dailyWeather) {
            this.dailyWeather = dailyWeather;
        }
    }
