package ru.hb.aws;

import org.springframework.stereotype.Service;

@Service
public class AdapterService {

    private final WeatherServiceFactory weatherServiceFactory;


    public AdapterService(WeatherServiceFactory weatherServiceFactory) {
        this.weatherServiceFactory = weatherServiceFactory;
    }
}
