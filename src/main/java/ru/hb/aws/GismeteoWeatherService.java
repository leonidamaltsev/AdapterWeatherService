package ru.hb.aws;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GismeteoWeatherService implements WeatherService {

    private final RestTemplate restTemplate;

    public GismeteoWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getWeather(String latitude, String longitude) {
        String apiUrl = "https://api.gismeteo.net/v2/weather/current/" + latitude + "/" + longitude + "/?lang=ru";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error fetching weather data from Gismeteo");
        }
        return parseWeatherData(response.getBody());
    }

    private String parseWeatherData(String weatherApiResponse) {
        return "Gismeteo weather data: 25°C, Clear skies";//пример
    }
}
