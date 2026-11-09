import java.awt.Graphics2D;
import java.awt.Color;

public class Shop extends Building{
    
    // stop the shops from being added to the cities population 
    @Override
    public int getPopulation() {
        return 0;
     }

     //how many shops the city should have 
     public static int shopsRequired(int population) { 
        return population / 15; 
     }

     // draws a blue square to represent a shop
    @Override
    public void build(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect((x * 50) + 7,(y * 50) +7, 35, 35);
    }
    } 

