public class BabyBoom implements EventLogic {
    int popBurst = 600;
    
    public void event(int x) {
        int calc = x + popBurst;
        System.out.println("YOOOO WE GIVING BIRTH");
        System.out.println("Population: " + calc);
    }
    
    
}
