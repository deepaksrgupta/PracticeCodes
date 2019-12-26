public class Main
{
    public static void main(String args[]) {
        LogisticsFactoryMethod factoryMethod = new LogisticsFactoryMethod();

        Logistics roadways = factoryMethod.getLogistics("Roadways");
        System.out.println(roadways.getShipmentVia());

        Logistics waterways = factoryMethod.getLogistics("Waterways");
        System.out.println(waterways.getShipmentVia());

        Logistics airways = factoryMethod.getLogistics("Airways");
        System.out.println(airways.getShipmentVia());
    }
}