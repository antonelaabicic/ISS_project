package dhmz.service;

import java.util.List;

public interface WeatherService {
    List<String> getTemperature(String city);
}
