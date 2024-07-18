package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class HourlyEmployeeTest {
    private  HourlyEmployee emp = new HourlyEmployee();

    @Before
    public void setUp() {
        emp = new HourlyEmployee("Amilia", Date.valueOf("2020-02-02"), 37.50, 25.0);
    }

    @Test
    public void testPayTaxes() {
        assertEquals(234.375, emp.payTaxes(), .001 );

    }

    @Test
    public void testPay() {
        assertEquals(937.50, emp.pay(), .001 );

    }
}