// this is the context, this should contain a reference to our strategies interface, a method for setting the strategy and a method for executing them
public class eventStrategyManager {
    eventLogic strategies;    

    public void SetStrategy(eventLogic s){
        this.strategies = s;
    }

    public void executeStrategy(int population){
        strategies.event(population);
    }
}
