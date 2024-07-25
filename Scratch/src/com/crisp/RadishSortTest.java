package com.crisp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class RadishSortTest {

    public static void main(String[] args) {
        List<Radish> radishes = new ArrayList<Radish>();

        radishes.add(new Radish("red",2.75,0.0,7));
        radishes.add(new Radish("pink",1.1,2.1,2));
        radishes.add(new Radish("maroon",0.7,3.3,0));
        radishes.add(new Radish("black",1.9,0.0,0));

        System.out.println("Original Radishes:");
        dump(radishes);
        System.out.println();


        System.out.println("Sort by natural order (size)");
        radishes.sort(null);
        dump(radishes);
        System.out.println();

        System.out.println("Sort by guysOnTop, via Comparator");
        radishes.sort(new RadishGuysOnTopComparator());
        dump(radishes);
        System.out.println();

        System.out.println("Sort by color, via Comparator");
        radishes.sort(new RadishColorComparator());
        dump(radishes);
        System.out.println();

        System.out.println("Sort by tail length, via anonymous Comparator class");
        radishes.sort(new Comparator<Radish>() {
            @Override
            public int compare(Radish r1, Radish r2) {
                return Double.compare(r1.getTailLength(), r2.getTailLength());
            }
        });

    }

    private static void dump(List<Radish> radishes) {
        for (Radish radish : radishes) {
            System.out.println(radish);  // toString() automatically called
        }
    }

}