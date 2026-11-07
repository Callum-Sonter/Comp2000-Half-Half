public class MallStrategy implements BuildLogic {

    @Override
    public void build(Window w) {
    Mall mall = new Mall();
        // check if needed
        //incPopulation();
        //find locaiton
        int[] locaition = w.findFreeLocation();
        //draw
        w.place(locaition[0], locaition[1], mall);
        // System.out.println("total population: "+ w.totalPeople());
}
}