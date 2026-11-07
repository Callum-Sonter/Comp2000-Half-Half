import java.awt.Graphics2D;
import java.awt.Color;

public class Mall extends Building {
    public Shop[] list; // a mall is made up of an array of shops
    private static final int SHOPS_REQUIRED = 4;
    private static final int MIN_POPULATION = 100;
    

    public Mall(){
        this.list = new Shop[SHOPS_REQUIRED];
    }

    
    @Override
    public int getPopulation(){
        return 0;
    }

    
    public static boolean canBuild(int shopCount, int cityPopulation){
    return shopCount >= SHOPS_REQUIRED
        && cityPopulation >= MIN_POPULATION;
}

    public int getShopCount(){
        int count = 0;
        for (int i = 0; i < list.length; i++){
            if (list[i] != null){
                count++;
            }
        }
        return count;
    }

    
    @Override
    public void build(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.ORANGE);
        g2d.fillRect((x * 50) + 7, (y * 50) + 7, 35, 35);
    }
        @Override 
    public String getName(){
        return getClass().getSimpleName();
    } 
}