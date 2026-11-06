import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.function.Predicate;
public class Window extends Frame {

    private int width;
    private int height;
    private Building[][] grid;
    
    public int[] getGridSize(){
        int x = 0;
        return new int[]{grid.length, grid[x].length}; 
    }

     public int totalPeople(){
        int total = 0;
        for (int x = 0; x<grid.length; x++){
            for (int y = 0; y<grid[x].length; y++){
                try{
                    if (grid[x][y] == null)
                        continue;
                    if (grid[x][y].getName().equals("Apartment") || grid[x][y].getName().equals( "House")){
                        total += grid[x][y].getPopulation();
                        continue;
                    }
               }
                catch(Exception e){
                    System.out.println("problem with if statement in totalpeople "+grid[x][y]);
                }
            }
        }
        // System.out.println("total people = "+total);
        return total;
    }

    public void reset(){
        if (totalPeople() >=2000){
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[x].length; y++) {
                    grid[x][y] = null;
                }
            }

            // place new house to start idk with users mouse?
        }
        
    }


    // constructor, acts as a decorator for our main window
    Window(int width, int height){
        this.width = width;
        this.height = height;
        this.setSize(width,height);
        this.grid = new Building[(width - 100) / 50][(height - 100) / 50];
        this.setTitle("Sim City");
        this.setBackground(Color.green);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
    }

    public int getBuilding(String temp){
        //pass in building subclass, returns number of subclasses in grid
        int total = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == null)
                    continue; 
                if (grid[x][y].getName() == temp){
                    total++;
                }
            }
        }
        return total;
    }


    // public boolean toBuildSchool(){
    //     boolean total = false;
    //     int children = 0;
    //     for (int i = 0; i<grid.length; i++){
    //         for (int z = 0; z<grid[i].length; z++){
    //             if (grid[z][i] instanceof House){
    //                 total += grid[z][i].getChildren();
    //                 continue;
    //             }
    //             if (grid[z][z] instanceof Apartment){
    //                 total += grid[z][i].get();
    //                 continue;
    //             }
    //         }
    //     }
    //     return total;
    // }
 

    // paint method used to add graphics to the screen
    @Override public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        


        //loop for vertical lines
        for (int i = 0; i < 100; i++) {
            g2d.drawLine(i * 50, 50, i * 50, 550);    
        }

        //loop for horizontal line
        for (int i = 0; i < 100; i++) {
           g2d.drawLine(50, i*50, 750, i*50); 
        }

        g2d.translate(50, 50);
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] != null) {
                    grid[x][y].build(g2d, x, y);
                }
            }
        }
    }
    
    public void drawRedOval(Graphics2D ovalg2d, int x, int y){;// draws red oval
        ovalg2d.setColor(Color.RED);
        //ovalg2d.drawOval(75,75,25,25);
        ovalg2d.fillOval((x*50)+7,(y*50)+7,35,35);
    }

    // finds every grid cell whose building passes the test given as a lambda
    private List<int[]> findCells(Predicate<Building> test) {
        return IntStream.range(0, grid.length)
                .boxed()
                .flatMap(x -> IntStream.range(0, grid[x].length)
                        .filter(y -> grid[x][y] != null && test.test(grid[x][y]))
                        .mapToObj(y -> new int[]{x, y}))
                .collect(Collectors.toList());
    }

    // counts buildings of any type, e.g. countBuildings(Shop.class)
    public long countBuildings(Class<? extends Building> type) {
        return Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .filter(type::isInstance)
                .count();
    }

    // used by the Volcano event
    public void destroyRandomBuildings(int count) {
        List<int[]> occupied = findCells(building -> true);
        Collections.shuffle(occupied);
        occupied.stream()
                .limit(count)
                .forEach(cell -> remove(cell[0], cell[1]));
    }

    // used by the MallOpening event: four shops merge into one mall
    public boolean openMall() {
        List<int[]> shops = findCells(building -> building instanceof Shop);
        if (!Mall.canBuild(shops.size(), totalPeople())) {
            return false;
        }

        Mall mall = new Mall();
        for (int i = 0; i < Mall.SHOPS_REQUIRED; i++) {
            int[] cell = shops.get(i);
            mall.list[i] = (Shop) grid[cell[0]][cell[1]];
            remove(cell[0], cell[1]);
        }

        int[] site = shops.get(0);
        place(site[0], site[1], mall);
        boostHousesNear(site[0], site[1]);
        return true;
    }

    // houses within 2 cells of a new mall gain 2 adults
    private void boostHousesNear(int mallX, int mallY) {
        findCells(building -> building instanceof House).stream()
                .filter(cell -> Math.abs(cell[0] - mallX) <= 2 && Math.abs(cell[1] - mallY) <= 2)
                .map(cell -> (House) grid[cell[0]][cell[1]])
                .forEach(house -> house.setAdults(house.getAdults() + 2));
    }

    // a mall counts as the shops it replaced
    private boolean needsShop() {
        long shopSpaces = countBuildings(Shop.class) + countBuildings(Mall.class) * Mall.SHOPS_REQUIRED;
        return shopSpaces < Shop.shopsRequired(totalPeople());
    }
    
    public void remove(int x, int y){ 
        grid[x][y] = null;
        repaint();
    }

    public void place(int x, int y, Building b){

        //System.out.println(grid.length);
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[x].length) {
                grid[x][y] = b;
                repaint();
        }
    }

    public int[] findFreeLocation(){
        int centerX = grid.length / 2;
        int centerY = grid[0].length / 2;

        int ram = (int)(Math.random() * 4) + 1;
        int maxRadius = Math.max(centerX, centerY);// max between x and yy 
        int minX;
        int maxX;
        int minY;
        int maxY;

        // set confinds for quad depending on ram
        switch (ram) {
            case 1:
                minX = centerX;
                maxX = grid.length - 1;
                minY = 0;
                maxY = centerY;
                break;
            case 2:
                minX = 0;
                maxX = centerX;
                minY = 0;
                maxY = centerY;
                break;
            case 3:
                minX = 0;
                maxX = centerX;
                minY = centerY;
                maxY = grid[0].length - 1;
                break;
            case 4:
                minX = centerX;
                maxX = grid.length - 1;
                minY = centerY;
                maxY = grid[0].length - 1;
                break;
            default:
                return null;
        }

        // expand outward from the center until a free cell is found
        for (int radius = 0; radius <= maxRadius; radius++) {
            // check every x in the selected quadrant.
            for (int x = minX; x <= maxX; x++) {
                // check every y for the current x.
                for (int y = minY; y <= maxY; y++) { //math.abs() returns pos (ex -7=+7) 
                    // check if x is within the radius, check is y is within radius, check is it is free
                    if (Math.abs(x - centerX) <= radius && Math.abs(y - centerY) <= radius && spare(x, y)) {// make x and y positive not neg 
                        return new int[]{x, y};
                    }
                }
            }
        }
        return null;
    }
    public boolean spare(int x, int y){
        
        if (grid[x][y] == null){
            return true;
        }
        return false;
    }

    public void incPopulation(){
        int step = 5;
        ArrayList<House> total = new ArrayList<House>();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] instanceof House){
                    total.add((House) grid[x][y]);
                }
            }
        }
        for (int i = 0; i<step;i++){
            try{
                int index = (int)(Math.random() * total.size()-1) + 1; 
                if (total.get(i).getAdults()<= 6){
                    total.get(index).setAdults(total.get(index).getAdults()+1);
                }
                else{
                    total.get(index).setAdults(total.get(index).getAdults()-1);
                }   
            }catch (IndexOutOfBoundsException e){
                System.out.println("ArrayList out of bounds at:"+i);
            }
        }
         
    }

    
    public int getShopCount() {
        int count = 0;

        for (int x = 0; x < grid.length; x++) { 
            for( int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] instanceof Shop) {
                    count++;
                }
            }
        }
        return count;
    }

    public void houseMain(){
        //find locaiton
        int[] locaition = findFreeLocation();
        // the chosen area of the grid can be full, so skip placing this tick
        if (locaition == null) {
            return;
        }
        // build a shop if the population needs one, otherwise a house
        Building next = needsShop() ? new Shop() : new House(2, 2);
        //draw
        place(locaition[0], locaition[1], next);
        System.out.println("total population: "+ totalPeople());
    }

        public void decPopulation(){
        int step = 30;
        ArrayList<House> total = new ArrayList<House>();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] instanceof House){
                    total.add((House) grid[x][y]);
                }
            }
        }
        for (int i = 0; i<step;i++){
            try{
                int index = (int)(Math.random() * total.size()-1) + 1; 
                if (total.get(index) == null)
                    break;
                if (total.get(i).getAdults()<= 6){
                    total.get(index).setAdults(total.get(index).getAdults()-1);
                }
                else{
                    total.get(index).setAdults(total.get(index).getAdults()-1);
                }   
            }catch (IndexOutOfBoundsException e){
                System.out.println("ArrayList out of bounds at:"+i);
            }
        }
         
    }

}
