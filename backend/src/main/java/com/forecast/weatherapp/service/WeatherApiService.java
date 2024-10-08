package com.forecast.weatherapp.service;

import com.forecast.weatherapp.model.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiService {
    private final RestTemplate restTemplate;

    public WeatherApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse fetchWeatherData(String url) {
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
