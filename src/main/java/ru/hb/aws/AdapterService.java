package ru.hb.aws;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AdapterService {

    private final WeatherServiceFactory weatherServiceFactory;
    private final RestTemplate restTemplate;

    public AdapterService(WeatherServiceFactory weatherServiceFactory, RestTemplate restTemplate) {
        this.weatherServiceFactory = weatherServiceFactory;
        this.restTemplate = restTemplate;
    }

    public void processAndSendMessage(MessageFromServiceA messageFromServiceA) {
        if (messageFromServiceA.getMsg() == null || messageFromServiceA.getMsg().trim().isEmpty()) {
            throw new IllegalArgumentException("Received empty message from Service A");
        }

        if (!"ru".equals(messageFromServiceA.getLng())) {
            System.out.println("Ignoring message as it does not have lng=ru");
            return;
        }

        WeatherService weatherService = weatherServiceFactory.getWeatherService("gismeteo");
        String weatherData;
        try {
            weatherData = weatherService.getWeather(
                    messageFromServiceA.getCoordinates().getLatitude(),
                    messageFromServiceA.getCoordinates().getLongitude()
            );
        } catch (Exception e) {
            throw new RuntimeException("Weather service is unavailable", e);
        }

        MessageToServiceB transformedMessage = transformMessage(messageFromServiceA, weatherData);
        sendToServiceB(transformedMessage);
    }

    private MessageToServiceB transformMessage(MessageFromServiceA messageFromServiceA, String weatherData) {
        String currentDateTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        int currentTemperature = extractTemperature(weatherData);
        return new MessageToServiceB(messageFromServiceA.getMsg(), currentDateTime, currentTemperature);
    }

    private int extractTemperature(String weatherData) {
        String tempString = weatherData.split(":")[1].trim().replace("°C", "");
        return Integer.parseInt(tempString);
    }

    private void sendToServiceB(MessageToServiceB messageToServiceB) {
        String serviceBUrl = "http://localhost:8081/message";
        restTemplate.postForEntity(serviceBUrl, messageToServiceB, String.class);
    }
}