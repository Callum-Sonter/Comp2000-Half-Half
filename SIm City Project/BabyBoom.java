public class BabyBoom implements EventLogic {
    @Override
    public void event(Window city) {
        city.incPopulation();
        System.out.println("YOOOO WE GIVING BIRTH"+ city.totalPeople());
    }
}
