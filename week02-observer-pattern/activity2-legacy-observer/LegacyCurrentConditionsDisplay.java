import java.util.Observer;
import java.util.Observable;

// Legacy Observer
@SuppressWarnings("deprecation")
public class LegacyCurrentConditionsDisplay implements Observer {

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof LegacyWeatherData) {
            LegacyWeatherData weatherData = (LegacyWeatherData) o;

            System.out.println("[Legacy Current] Temp: "
                    + weatherData.getTemperature() + "°C, "
                    + "Humidity: " + weatherData.getHumidity() + "%");
        }
    }
}
