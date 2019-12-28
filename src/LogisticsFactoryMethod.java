//Creational pattern

/*
 Factory Pattern or Factory Method Pattern says that just define an interface or
 abstract class for creating an object but let the subclasses decide which class to instantiate.
 In other words, subclasses are responsible to create the instance of the class.
 */
import java.util.ArrayList;

abstract class Logistics {
    protected String shippedVia;

    abstract String getShipmentVia();
}

class Roadways extends Logistics {

    Roadways() {
        shippedVia = "Roadways";
    }
    @Override
    String getShipmentVia() {
        return shippedVia;
    }
}

class WaterWays extends  Logistics {
    WaterWays(){
        shippedVia = "WaterWays";
    }

    @Override
    String getShipmentVia() {
        return shippedVia;
    }
}

class Airways extends Logistics {
    Airways() {
        shippedVia = "Airways";
    }

    @Override
    String getShipmentVia() {
        return shippedVia;
    }
}

public class LogisticsFactoryMethod {
    ArrayList<String> demandedLogistics = new ArrayList<>();

    public Logistics getLogistics(String type){
        demandedLogistics.add(type);

        if (type == "Airways"){
            return new Airways();
        }else if (type == "Waterways"){
            return new WaterWays();
        }else {
            return new Roadways();
        }
    }

    public void printAllDemandedLogistics(){
        for(String dl: demandedLogistics){
            System.out.println(dl);
        }
    }
}

//    public static void main(String args[]) {
//        LogisticsFactoryMethod factoryMethod = new LogisticsFactoryMethod();
//
//        Logistics roadways = factoryMethod.getLogistics("Roadways");
//        System.out.println(roadways.getShipmentVia());
//
//        Logistics waterways = factoryMethod.getLogistics("Waterways");
//        System.out.println(waterways.getShipmentVia());
//
//        Logistics airways = factoryMethod.getLogistics("Airways");
//        System.out.println(airways.getShipmentVia());
//    }
