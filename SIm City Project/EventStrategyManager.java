// The context in the Strategy pattern: it holds the current event and runs it
public class EventStrategyManager {
    private EventLogic strategy;

    public void setStrategy(EventLogic strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Window city) {
        strategy.event(city);
    }
}