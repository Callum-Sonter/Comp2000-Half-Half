import java.util.ArrayList;

public class EventManager<T extends CityEvent>{
    private ArrayList<T> list = new ArrayList<>();
    private int indexActive;

    public void add(T eventT){
        list.add(eventT);
    }

    public void runEvent(Window window){
        if (list.get(indexActive)!=null && indexActive<list.size()) 
            list.get(indexActive).run(window);
        else{
            indexActive = 0;
        }
    }

    public void runAll(Window window){
        for(int i = 0; i<list.size(); i++){
            list.get(i).run(window);
        }
    }

    public T nextEvent(){
        if (list.get(indexActive+1)!=null && indexActive<list.size() )
            return list.get(indexActive+1);
        return null;
    }
    
}