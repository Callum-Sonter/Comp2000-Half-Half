public class SchoolStrategy implements EventLogic {

    @Override
    public void build(Window w) {
    School school = new School();
        // check if needed
        //incPopulation();
        //find locaiton
        int[] locaition = w.findFreeLocation();
        //draw
        w.place(locaition[0], locaition[1], school);
        System.out.println("total population: "+ w.totalPeople());
}
}