public class Flood implements EventLogic {
    @Override
    public void event(Window city) {
        city.decPopulation();
        System.out.println("Flood! Population is now " + city.totalPeople());
    }
}