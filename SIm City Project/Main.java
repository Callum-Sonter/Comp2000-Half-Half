import java.util.Random;
public class Main {
    private static final int EVENT_INTERVAL = 20;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Initializing program");
        Window w = new Window(800, 600);
        StatsWindow sw = new StatsWindow(300, 300);
        EventStrategyManager eventManager = new EventStrategyManager();
        EventLogic[] events = {new Volcano(), new Flood(), new BabyBoom()};

        
        Office o = new Office(0, 0);
        int money = o.generateIncome();
       //creating main loop

          Random random = new Random();
       int tick = 0;

       while (w.isDisplayable()){
            w.houseMain();
            tick++;

            // every 20 ticks (about 10 seconds), a random event hits the city
            if (tick % EVENT_INTERVAL == 0) {
                eventManager.setStrategy(events[random.nextInt(events.length)]);
                eventManager.executeStrategy(w);
            }

            sw.updateLabel(Integer.toString(w.totalPeople()));
            Thread.sleep(500);
            w.repaint();
       }






        //  var random = new Random();
        //  int minX = 1;
        //  int minY = 1;
        //  int maxX = 14;
        //  int maxY = 10;

        // boolean test3 = true;
        // House test = new House(3, 3);
        
        // Office testOffice = new Office(3, 3);
        // for (int i = 0; i < 70; i++) {
        //     w.houseMain();
            // int[] location = w.findFreeLocation();
            // if (location == null) {
            //     break;
            // }
            // if (test3){
            //     w.place(location[0], location[1], testOffice);
            //     test3 = false;
            // }
            // else{
            //     w.place(location[0], location[1], test);
            //     test3 = true;
            // }
        //     Thread.sleep(100);
        // }
      // w.repaint();
        // for (int i = 0; i < 35; i++) {
        //    int randomX = random.nextInt(maxX - minX + 1) + minX;
        //    int randomY = random.nextInt(maxY - minY + 1) + minY;
        //     TimeUnit.SECONDS.sleep(1);
        //     w.place(randomX, randomY, test);
        //     System.out.println(w.getBuilding("House"));
            

        // }
        // int i = 1;


        // w.totalPeople();
        // sw.updateLabel(Integer.toString(w.total));
      
    

         
    
        // Office test3 = new Office(0, 0);
        // w.place(6, 7, test3);

        // School test4 = new School();
        // w.place (6,8,test4); 


        // Mall test5 = new Mall();
        // w.place(9, 3, test5);

        // Shop test6 = new Shop(); 
        // w.place(11, 5, test6); 

        // sw.updateShopLabel(Integer.toString(w.getShopCount()));
    }
}