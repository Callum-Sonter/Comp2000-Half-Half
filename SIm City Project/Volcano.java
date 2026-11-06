public class Volcano implements EventLogic {
    private static final int BUILDINGS_DESTROYED = 5;

    @Override
    public void event(Window city) {
        city.destroyRandomBuildings(BUILDINGS_DESTROYED);
        System.out.println("Volcano! Population is now " + city.totalPeople());
    }

    // make reference to window
    //building grid in it
    //place functionality
    //repaint updates the grid
    // remove function

    
}