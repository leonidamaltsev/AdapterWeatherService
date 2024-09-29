package ru.hb.aws;

import org.springframework.stereotype.Service;

@Service
public class WeatherServiceFactory {

    private final GismeteoWeatherService gismeteoService;
    private final AnyWeatherService anyWeatherService;

    public WeatherServiceFactory(GismeteoWeatherService gismeteoService,
                                 AnyWeatherService anyWeatherService) {
        this.gismeteoService = gismeteoService;
        this.anyWeatherService = anyWeatherService;
    }

    public WeatherService getWeatherService(String serviceName) {
        switch (serviceName.toLowerCase()) {
            case "gismeteo":
                return gismeteoService;
            case "anyweatherservice":
                return anyWeatherService;
            default:
                throw new IllegalArgumentException("Unknown weather service: " + serviceName);
        }
    }
}
