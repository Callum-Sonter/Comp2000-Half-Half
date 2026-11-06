public class MallOpening implements EventLogic {
    @Override
    public void event(Window city) {
        if (city.openMall()) {
            System.out.println("A new mall opened! Nearby houses are growing. Population is now " + city.totalPeople());
        }
    }
}