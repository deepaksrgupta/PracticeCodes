//Creational pattern --> prototype
/*
Prototype Pattern says that cloning of an existing object instead of creating new one and can also be customized as per the requirement.
 */
interface ProtoTypeInterface {
    ProtoTypeInterface getClone();
}

class Person implements ProtoTypeInterface {
    String name;
    String gender;

    Person(String name, String gender){
        this.name = name;
        this.gender = gender;
    }

    void printDetails(){
        System.out.println("Name: "+this.name+ " Gender: "+this.gender);
    }

    @Override
    public ProtoTypeInterface getClone() {
        return new Person(this.name,this.gender);
    }
}

public class Prototype {
    public static void DemoPrototype() {
        Person p = new Person("Barry Allen","Male");
        System.out.print("Person Object is ");
        p.printDetails();

        Person personClone  = (Person) p.getClone();
        System.out.print("Person Clone Object is ");
        personClone.printDetails();
    }
}

//
//    public static void main(String args[]) {
//        Prototype.DemoPrototype();
//    }