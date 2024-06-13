package zfr.mobile.persuhuanduniawii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private TextView longitude;
    private TextView latitude;
    private TextView city;
    private TextView temperature;
    private TextView condition;
    private TextView wind;
    private ImageView icon;
    private ArrayList<Cuaca> cuacaData;
    private AdapterCuaca cuacaAdapter;
    RecyclerView cuacaRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        longitude = findViewById(R.id.tvLongitude);
        latitude = findViewById(R.id.tvLatitude);
        city = findViewById(R.id.tvCity);
        temperature = findViewById(R.id.tvTemperature);
        icon = findViewById(R.id.ivIcon);
        condition = findViewById(R.id.tvCondition);
        wind = findViewById(R.id.tvWind);
        RecyclerView cuacaRV = findViewById(R.id.rvWeather);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.card_spacing);

        cuacaData = new ArrayList<>();
        cuacaAdapter = new AdapterCuaca(this, cuacaData);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        cuacaRV.setLayoutManager(layoutManager);
        cuacaRV.addItemDecoration(new CardSpacingItemDecoration(this, spacingInPixels));
        cuacaRV.setAdapter(cuacaAdapter);


        getWeather();
    }

    public interface WeatherApi {

        @GET("forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto")
        Call<WeatherResponse> getWeather();
    }


    private void getWeather() {
        String baseUrl = "https://api.open-meteo.com/v1/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeather();

        call.enqueue(new Callback<WeatherResponse>() {

            @Override
            public void onResponse(Call<WeatherResponse> call, retrofit2.Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        // Lakukan pengolahan data response di sini
                        latitude.setText("-7.98");
                        longitude.setText("112.63");

                        CurrentWeather currentWeather = weatherResponse.getCurrentWeather();
                        temperature.setText(String.valueOf(currentWeather.getTemperature()));

                        int weatherCode = currentWeather.getWeatherCode();
                        getCondition(weatherCode);

                        String windValue = String.valueOf(currentWeather.getWindspeed());
                        wind.setText(windValue);

                        DailyWeather dailyWeather = weatherResponse.getDailyWeather();
                        List<Double> temperatureMax = dailyWeather.getTemperatureMax();
                        List<String> time = dailyWeather.getTime();
                        List<Integer> weatherCoder = dailyWeather.getWeatherCode();

                        int data = Math.min(time.size(), weatherCoder.size());

                        for (int i = 0; i < data; i++) {
                            String times = time.get(i);
                            int dailyCode = weatherCoder.get(i);
                            Cuaca cuaca = new Cuaca(times, dailyCode);
                            cuacaData.add(cuaca);
                        }

                        cuacaAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e("API Error", "Failed to retrieve data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("API Error", "Failed to make API call: " + t.getMessage());
            }
        });
    }

    private void getCondition(int getCode) {
        if (getCode >= 95) {
            condition.setText("Thunderstorm");
            icon.setImageResource(R.drawable.thunderstorm);
        } else if (getCode >= 85) {
            condition.setText("Snow Showers");
            icon.setImageResource(R.drawable.snow);
        } else if (getCode >= 80) {
            condition.setText("Rain showers : violent");
            icon.setImageResource(R.drawable.storm);
        } else if (getCode >= 61) {
            condition.setText("Rain: heavy intensity");
            icon.setImageResource(R.drawable.heavyrain);
        } else if (getCode >= 45) {
            condition.setText("Cloudy");
            icon.setImageResource(R.drawable.cloudyday);
        } else{
            condition.setText("Clear Sky");
            icon.setImageResource(R.drawable.sunny);
        }
    }
}