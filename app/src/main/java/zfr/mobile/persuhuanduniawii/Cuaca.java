package zfr.mobile.persuhuanduniawii;

public class Cuaca {

    public String date;
    public String condition;
    public int icon;
    public int weatherCode;

    public Cuaca(String d,  int w){
        this.date = d;
        this.condition = getConditionFromWeatherCode(w);
        this.icon = getIconFromWeatherCode(w);
        this.weatherCode = w;
    }

    private int getIconFromWeatherCode(int code) {
        if (code >= 95) {
            return R.drawable.thunderstorm;
        } else if (code >= 85) {
            return R.drawable.snow;
        } else if (code >= 80) {
            return R.drawable.storm;
        } else if (code >= 61) {
            return R.drawable.heavyrain;
        } else if (code >= 45) {
            return R.drawable.cloudyday;
        } else {
            return R.drawable.sunny;
        }
    }

    private String getConditionFromWeatherCode(int code) {
        if (code >= 95) {
            return "Thunderstorm";
        } else if (code >= 85) {
            return "Snow Showers";
        } else if (code >= 80) {
            return "Rain showers : violent";
        } else if (code >= 61) {
            return "Rain: heavy intensity";
        } else if (code >= 45) {
            return "Cloudy";
        } else {
            return "Clear Sky";
        }
    }
    public String getDate() {
        return date;
    }

    public String getCondition() {
        return condition;
    }

    public int getIcon() {
        return icon;
    }
}
