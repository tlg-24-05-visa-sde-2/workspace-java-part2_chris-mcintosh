package com.javatunes.billing;

import static com.javatunes.billing.Location.*;

public class TaxCalculatorFactory {

    private TaxCalculatorFactory() {

    }
    public static TaxCalculator createCalculator(Location location) {
        //see Java Part 2 Sessions Manual p.122 for more details
        return switch (location) {
            case ONLINE -> new OnlineTax();
            case USA -> new USATax();
            case EUROPE -> new EuropeTax();
        };

    }



   /*
    public static TaxCalculator createCalculator(Location location) {
        TaxCalculator calc = null;
        switch (location) {
            case ONLINE:
                calc = new OnlineTax();
                break;
            case USA:
                calc = new USATax();
                break;
            case EUROPE:
                calc = new EuropeTax();
        }
        return calc;
    }
    */
}