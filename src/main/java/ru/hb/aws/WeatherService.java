package ru.hb.aws;

public interface WeatherService {
    String getWeather(String latitude, String longitude);
}