import java.awt.Color;
import java.awt.Graphics2D;
public class Apartment extends Building{
    public House[] list; // a apartment is made out of a arrray of houses  
    

    

    @Override
    public void build(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect((x * 50) + 7, (y * 50) + 7, 35, 35);
    }

    @Override 
    public String getName(){
        return getClass().getSimpleName();
    } 

    
}