// this is the context, this should contain a reference to our strategies interface, a method for setting the strategy and a method for executing them
public class EventStrategyManager {
    
    EventLogic strategies;    

    public void SetStrategy(EventLogic s){
        this.strategies = s;
    }

    public void executeStrategy(Window win){
        strategies.build(win);
    }
}
