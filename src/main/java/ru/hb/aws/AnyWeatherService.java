package ru.hb.aws;

import org.springframework.stereotype.Service;

@Service
public class AnyWeatherService implements WeatherService{

    @Override
    public String getWeather(String latitude, String longitude) {
        return null;
    }
}