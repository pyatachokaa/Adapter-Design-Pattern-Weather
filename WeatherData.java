// Step 1: Define the common data model for weather information
class WeatherData {
    private String location;
    private double temperatureCelsius;
    private double temperatureFahrenheit;
    private double humidity;

    public WeatherData(String location, double temperatureCelsius, double humidity) {
        this.location = location;
        this.temperatureCelsius = temperatureCelsius;
        this.temperatureFahrenheit = celsiusToFahrenheit(temperatureCelsius);
        this.humidity = humidity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
        this.temperatureFahrenheit = celsiusToFahrenheit(temperatureCelsius);
    }

    public double getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }
}

interface WeatherService {
    WeatherData fetchWeather(String location);
}

class AccuWeatherService implements WeatherService {
    @Override
    public WeatherData fetchWeather(String location) {
        // Simulated data retrieval from AccuWeather API
        return new WeatherData(location, 25.0, 60.0);
    }
}

class OpenWeatherMapService implements WeatherService {
    @Override
    public WeatherData fetchWeather(String location) {
        // Simulated data retrieval from OpenWeatherMap API
        // For demonstration purposes, return dummy data
        return new WeatherData(location, 28.0, 55.0);
    }
}

class WeatherAdapter implements WeatherService {
    private WeatherService weatherService;

    public WeatherAdapter(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public WeatherData fetchWeather(String location) {
        // Perform data translation here if needed
        // For simplicity, just return the fetched weather data
        return weatherService.fetchWeather(location);
    }
}

class testt {
    public static void main(String[] args) {
        WeatherService accuWeatherService = new AccuWeatherService();
        WeatherService openWeatherMapService = new OpenWeatherMapService();

        WeatherService adapter1 = new WeatherAdapter(accuWeatherService);
        WeatherService adapter2 = new WeatherAdapter(openWeatherMapService);

        WeatherData weatherData1 = adapter1.fetchWeather("Almaty");
        WeatherData weatherData2 = adapter2.fetchWeather("Tokyo");

        displayWeather("Almaty", weatherData1);
        displayWeather("Tokyo", weatherData2);
    }

    private static void displayWeather(String location, WeatherData weatherData) {
        System.out.println("Location: " + location);
        System.out.println("Temperature: " + weatherData.getTemperatureCelsius() + "°C / "
                + weatherData.getTemperatureFahrenheit() + "°F");
        System.out.println("Humidity: " + weatherData.getHumidity() + "%");
        System.out.println();
    }
}



