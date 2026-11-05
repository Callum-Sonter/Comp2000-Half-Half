public class Flood implements EventLogic {
    int casualties = 600;

    public void event(int x){
        System.out.println("aahhhhh its flooding!!");
        System.out.println("the flood has taken " + casualties + " casualties!");
        int calc = x - casualties;
        System.out.println("Population: " + calc);
    }
    
}
