public class HouseStrategy implements BuildLogic {

    @Override
    public void build(Window w) {
    House house = new House(2,2);
        // check if needed
        //incPopulation();
        //find locaiton
        int[] locaition = w.findFreeLocation();
        //draw
        w.place(locaition[0], locaition[1], house);
        // System.out.println("total population: "+ w.totalPeople());
}
}