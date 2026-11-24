@SuppressWarnings("deprecation ")
public class LegacyWeatherStationMain {

    public static void main(String[] args) {

        LegacyWeatherData weatherData = new LegacyWeatherData();

        LegacyCurrentConditionsDisplay currentDisplay =
                new LegacyCurrentConditionsDisplay();

        // Register the observer
        weatherData.addObserver(currentDisplay);

        // Send weather measurements
        weatherData.setMeasurements(25.0f, 65.0f);
        weatherData.setMeasurements(28.0f, 70.0f);
        weatherData.setMeasurements(22.0f, 60.0f);

        // Remove the observer
        System.out.println("\n--- Removing Legacy Observer ---");
        weatherData.deleteObserver(currentDisplay);

        // This update should no longer be displayed
        weatherData.setMeasurements(20.0f, 55.0f);
    }
}
