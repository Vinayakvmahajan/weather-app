package com.forecast.weatherapp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherResponse {
    private String cod;
    private int message;
    private int cnt;
    private List<WeatherForecast> list = new ArrayList<>();
    private City city;
    private List<Link> links = new ArrayList<>(); // For HATEOAS links

    public void addLink(String rel, String href) {
        links.add(new Link(rel, href));
    }

    @Data
    public static class WeatherForecast {
        private long dt;
        private Main main;
        private List<Weather> weather;
        private Wind wind;
        private String dt_txt;
    }

    @Data
    public static class Main {
        private double temp;
        private double temp_min;
        private double temp_max;
    }

    @Data
    public static class Weather {
        private String main;
        private String description;
    }

    @Data
    public static class Wind {
        private double speed;
    }

    @Data
    public static class City {
        private String name;
        private String country;
    }

    @Data
    public static class Link {
        private String rel;
        private String href;

        public Link(String rel, String href) {
            this.rel = rel;
            this.href = href;
        }
    }
}
