package com.forecast.weatherapp.controller;


import com.forecast.weatherapp.model.WeatherResponse;
import com.forecast.weatherapp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Operation(summary = "Get weather forecast", description = "Retrieve the 3-day weather forecast for a specified city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of weather data"),
            @ApiResponse(responseCode = "404", description = "City not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{city}")
    public WeatherResponse getWeatherForecast(@PathVariable String city) {
        return weatherService.getWeatherForecast(city);
    }
}
