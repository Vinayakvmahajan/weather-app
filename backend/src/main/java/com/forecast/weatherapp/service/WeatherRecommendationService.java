package com.forecast.weatherapp.service;

import com.forecast.weatherapp.model.WeatherResponse.WeatherForecast;
import org.springframework.stereotype.Service;

@Service
public class WeatherRecommendationService {
    public String generateRecommendation(WeatherForecast forecast) {
        String recommendation = "";

        // Convert temperatures from Kelvin to Celsius
        double maxTempCelsius = forecast.getMain().getTemp_max() - 273.15;
        double minTempCelsius = forecast.getMain().getTemp_min() - 273.15;

        // Evaluate weather conditions and assign appropriate recommendations
        if (forecast.getWeather().get(0).getMain().equalsIgnoreCase("Rain")) {
            recommendation = "Carry an umbrella";
        }
        if (maxTempCelsius > 40) {
            recommendation = "Use sunscreen lotion";
        }
        if (forecast.getWind().getSpeed() > 10) {
            recommendation = "It’s too windy, watch out!";
        }
        if (forecast.getWeather().get(0).getMain().equalsIgnoreCase("Thunderstorm")) {
            recommendation = "Don’t step out! A storm is brewing!";
        }

        // Return recommendation as part of the description
        return recommendation;
    }
}
