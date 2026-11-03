public class volcano implements eventLogic{

    int casualties = 500;
    public void event(int x){
        System.out.println("aahhhhh its errupting!!");
        System.out.println("the Volcano has taken " + casualties + " casualties!");
        int calc = x - casualties;
        System.out.println("Population: " + calc);
    }

    
}