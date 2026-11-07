public class ShopStrategy implements BuildLogic {

    @Override
    public void build(Window w) {
    Shop shop = new Shop();
        // check if needed
        //incPopulation();
        //find locaiton
        int[] locaition = w.findFreeLocation();
        //draw
        w.place(locaition[0], locaition[1], shop);
        System.out.println("total population: "+ w.totalPeople());
}
}