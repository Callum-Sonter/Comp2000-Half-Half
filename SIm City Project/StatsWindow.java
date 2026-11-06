import java.awt.*;
import javax.swing.JLabel;

public class StatsWindow extends Frame {
    JLabel label = new JLabel("Label");
    JLabel shopLabel = new JLabel("shop: 0");

    StatsWindow(int width, int height){
        this.setTitle("Stats Window");
        this.setSize(width, height);
        this.setVisible(true);
        this.setLocation(1920/2, 1080/2);

        // dont really like this layout but i didn't know what you guys wanted the display to look like. 
        // FEEL FREE TO CHANGE THE FORMAT TO WHATEVER YOU LIKE.  
        this.setLayout(new FlowLayout());

       this.add(label);
       label.setVisible(true);

        this.add(shopLabel);
        shopLabel.setVisible(true);
    }

    public void updateLabel(String text){
        label.setText("population: " + text);
         
    }

    public void updateShopLabel(String text){
        shopLabel.setText("shops: " + text);
    }
}
