public class OfficeStrategy implements BuildLogic {

    @Override
    public void build(Window w) {
    Office office = new Office(3,3);
        // check if needed
        //incPopulation();
        //find locaiton
        int[] locaition = w.findFreeLocation();
        //draw
        w.place(locaition[0], locaition[1], office);
        // System.out.println("total population: "+ w.totalPeople());
}
}