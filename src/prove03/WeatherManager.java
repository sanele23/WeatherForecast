package prove03;

import com.google.gson.*;


import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class WeatherManager {
    private final String key = "00be4d9b49e94fd40347bba31bda24d8";
    private final String baseUrl = "http://api.openweathermaorgp./data/2.5/weather/";
    private Gson gson;
    private List<WeatherForecastSummary> summaries;
    private HTTPHelper httpHelper;

    public WeatherManager() {
        gson = new Gson();
        httpHelper = new HTTPHelper();
        summaries = new ArrayList<WeatherForecastSummary>();
    }

    /* STRETCH REQUIREMENTS */
    public void loadMultipleForecasts(List<String> cityList) {
        // The WeatherForecastSummary will have the city name and max temp and max wind from the
        // the forecast results.
        for (String city : cityList) {
            WeatherForecast forecast = loadWeatherForecast(city);
            WeatherForecastSummary summary = new WeatherForecastSummary(city, forecast);
            summaries.add(summary);
            }
        }

        public void sortMaxWind() {
        // Define what it means to sort a list of WeatherForecastSummary by Wind
        summaries.sort(new Comparator<WeatherForecastSummary>() {
        @Override
        public int compare(WeatherForecastSummary o1, WeatherForecastSummary o2) {
                // Reuse the compare function for Floats
                return Float.compare(o1.getMaxWind(), o2.getMaxWind());
                }
            });
        }

        public void sortMaxTemp() {
        // Define what it means to sort a list of WeatherForecastSummary by Temp
        // This is using a lambda instead of saying `new Comparator<WeatherForecastSummary>()`
        summaries.sort((WeatherForecastSummary o1, WeatherForecastSummary o2)->(int)(o2.getMaxTemp()-o1.getMaxTemp()));
        }

        public List<WeatherForecastSummary> getSummaries() { return summaries; }

        /* CORE REQUIREMENTS */

        public WeatherConditions loadCurrentWeather(String city) {
        // Request for weather data
        String url = baseUrl + "weather" +
                "?q=" + city +
                "&units=imperial" +
                "&apiKey=" + key;
        String data = httpHelper.readHTTP(url);
        // Convert JSON data to WeatherConditions object
        return gson.fromJson(data, WeatherConditions.class);
        }

        public WeatherForecast loadWeatherForecast(String city) {
        // Requset for weather forecast
        String url = baseUrl + "forecast" +
                "?q=" + city +
                "&units=imperial" +
                "&apiKey=" + key;
        String data = httpHelper.readHTTP(url);
        // Convert JSON data to WeatherForecast object
        return gson.fromJson(data, WeatherForecast.class);
        }

        public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        WeatherManager mgr = new WeatherManager();
        /* CORE REQUIREMENTS */


        System.out.println("Enter city name: ");
        String city = scanner.nextLine();
        WeatherConditions conditions = mgr.loadCurrentWeather(city);
        System.out.println(conditions);
        WeatherForecast forecast = mgr.loadWeatherForecast(city);
        System.out.println(forecast);


        /* STRETCH REQUIREMENTS */
        ArrayList<String> cities = new ArrayList<String>();
        cities.add("Rexburg");
        cities.add("Kalamazoo");
        cities.add("Harlingen");
        cities.add("Rockford");
        cities.add("San Francisco");
        System.out.println("Unsorted Summaries:");
        mgr.loadMultipleForecasts(cities);
        for (WeatherForecastSummary summary : mgr.getSummaries()) {
            System.out.println(summary);
            }

        System.out.println("Sorted by Max Temp:");
        mgr.sortMaxTemp();
        for (WeatherForecastSummary summary : mgr.getSummaries()) {
            System.out.println(summary);
            }

        System.out.println("Sorted by Max Wind:");
        mgr.sortMaxWind();
        for (WeatherForecastSummary summary : mgr.getSummaries()) {
            System.out.println(summary);
            }
        }
}
