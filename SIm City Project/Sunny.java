public class Sunny implements WeatherState {

    @Override
    public void action(Weather context) {
        context.setState(this);
    }
}