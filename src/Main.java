import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.*;
import java.util.*;

class Main {

    static public void main( String args[] ) {
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 500, 0.001);

        for(int i=0; i < 100; i++){
            filter.put(i);
        }

        System.out.println(filter.mightContain(99));
        System.out.println(filter.mightContain(100));
        System.out.println(filter.mightContain(999));
    }
}   