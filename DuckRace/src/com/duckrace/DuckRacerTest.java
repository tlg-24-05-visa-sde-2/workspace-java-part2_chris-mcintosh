package com.duckrace;

import java.util.Collection;

class DuckRacerTest {
    public static void main(String[] args) {
        DuckRacer racer = new DuckRacer(1, "Bullen");
        DuckRacer racer2 = new DuckRacer(2, "Chris");
        System.out.println(racer2); //toString automatically called
        racer2.win(Reward.PRIZES);
        racer2.win(Reward.PRIZES);
        racer2.win(Reward.DEBIT_CARD);

        System.out.println(racer2);
        System.out.println();

        //fetch the collection from this existing DuckRacer object
        Collection<Reward> rewards = racer2.getRewards();
        System.out.println(rewards);

        racer2.win(Reward.DEBIT_CARD);
        System.out.println(rewards);

    }
}