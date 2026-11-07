public class ApartmentStrategy implements BuildLogic {

    @Override
    public void build(Window w){
         Apartment apt = new Apartment();
        // check if needed
        //incPopulation();
        //find locaiton
        int[] locaition = w.findFreeLocation();
        //draw
        w.place(locaition[0], locaition[1], apt);
        // System.out.println("total population: "+ w.totalPeople());

    }
}