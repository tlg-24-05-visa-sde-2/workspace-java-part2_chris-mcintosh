package com.crisp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class RadishSortTestLambda {

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

        System.out.println("Sort by guysOnTop, via Lambda");
        radishes.sort( (r1, r2) -> Integer.compare(r1.getGuysOnTop(), r2.getGuysOnTop()));
        dump(radishes);
        System.out.println();

        System.out.println("Sort by color, via Lambda");
        radishes.sort( (r1, r2) -> r1.getColor().compareTo(r2.getColor()));
        dump(radishes);
        System.out.println();

        System.out.println("Sort by tail length, via lambda class");
        radishes.sort((r1, r2) -> Double.compare(r1.getTailLength(), r2.getTailLength())) ;
            dump(radishes);
        System.out.println();

    }

    private static void dump(List<Radish> radishes) {
        for (Radish radish : radishes) {
            System.out.println(radish);  // toString() automatically called
        }
    }

}