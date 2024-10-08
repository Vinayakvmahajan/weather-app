package com.forecast.weatherapp.service;

import com.forecast.weatherapp.model.WeatherResponse;
import com.forecast.weatherapp.model.WeatherResponse.WeatherForecast;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.offline.mode:false}")
    private boolean offlineModeEnabled;

    private final WeatherApiService weatherApiService;
    private final WeatherRecommendationService weatherRecommendationService;

    public WeatherService(WeatherApiService weatherApiService,
                          WeatherRecommendationService weatherRecommendationService) {
        this.weatherApiService = weatherApiService;
        this.weatherRecommendationService = weatherRecommendationService;
    }

    public WeatherResponse getWeatherForecast(String city) {
        String url = String.format("%s?q=%s&appid=%s&cnt=10", weatherApiUrl, city, apiKey);
        WeatherResponse response;

        // Check if offline mode is enabled
        if (isOfflineModeEnabled()) {
            return getFallbackResponse(city);
        }

        response = weatherApiService.fetchWeatherData(url);
        return processResponse(response);
    }

    private WeatherResponse processResponse(WeatherResponse response) {
        // Process the response to add recommendations based on weather conditions
        response.setList(response.getList().stream().limit(3)
                .map(forecast -> {
                    String recommendation = weatherRecommendationService.generateRecommendation(forecast);
                    forecast.getWeather().get(0).setDescription(recommendation);
                    return forecast;
                })
                .collect(Collectors.toList()));
        return response;
    }

    // Offline fallback data
    private WeatherResponse getFallbackResponse(String city) {
        WeatherResponse fallbackResponse = new WeatherResponse();
        fallbackResponse.setCity(new WeatherResponse.City());
        fallbackResponse.getCity().setName(city);
        fallbackResponse.getCity().setCountry("Offline Country");

        // Example static data for offline mode
        WeatherForecast forecast1 = createOfflineForecast("2024-10-05", 295, 290, "Clear", 5);
        WeatherForecast forecast2 = createOfflineForecast("2024-10-06", 300, 295, "Rain", 15);
        WeatherForecast forecast3 = createOfflineForecast("2024-10-07", 320, 310, "Thunderstorm", 20);

        // Add forecasts to fallback response
        List<WeatherForecast> forecasts = Arrays.asList(forecast1, forecast2, forecast3);
        fallbackResponse.setList(forecasts);
        fallbackResponse.setCnt(forecasts.size());
        fallbackResponse.setCod("200");
        fallbackResponse.setMessage(1);

        return fallbackResponse;
    }

    private WeatherForecast createOfflineForecast(String date, double maxTemp, double minTemp, String weatherCondition, double windSpeed) {
        WeatherForecast forecast = new WeatherForecast();
        forecast.setDt(System.currentTimeMillis() / 1000);
        forecast.setDt_txt(date + " 12:00:00");

        // Create and set main temperature data
        WeatherResponse.Main main = new WeatherResponse.Main();
        main.setTemp_max(maxTemp);
        main.setTemp_min(minTemp);
        forecast.setMain(main);

        // Create and set weather data
        WeatherResponse.Weather weather = new WeatherResponse.Weather();
        weather.setMain(weatherCondition);
        forecast.setWeather(Arrays.asList(weather));

        // Create and set wind data
        WeatherResponse.Wind wind = new WeatherResponse.Wind();
        wind.setSpeed(windSpeed);
        forecast.setWind(wind);

        return forecast;
    }

    public boolean isOfflineModeEnabled() {
        return offlineModeEnabled;
    }
}
