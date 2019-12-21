import java.util.HashMap;
import java.util.Random;
public class Helper {

    public static void display(String s)
    {
        System.out.println(s);
    }


    public static Random getRandomObject(){
        return new Random();
    }

    public static HashMap getHashmapObject(){
        return new HashMap<>();
    }

}
