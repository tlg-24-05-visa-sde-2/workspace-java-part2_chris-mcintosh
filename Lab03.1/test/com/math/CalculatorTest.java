/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.math;

import static org.junit.Assert.*;

import org.junit.*;

public class CalculatorTest {
    //business objects under test - called the "fixture"
    private Calculator calc;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Before Class");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("After Class");
    }

    @Before
    public void setUp(){
        System.out.println("setUp");
        calc = new Calculator();
    }

    @After
    public void tearDown() {
        System.out.println("tearDown");

    }

    @Test
    public void testisEven() {
        System.out.println("testEven");
        assertTrue(calc.isEven(10));
        assertFalse(calc.isEven(11));
    }

    @Test
    public void testDivide() {
        System.out.println("testDivide");
        assertEquals(2.5,calc.divide(5, 2), .001);
    }

    @Test
    public void testAdd() {
        System.out.println("testAdd");
        assertEquals(5, calc.add(1, 4));  // expected, actual
    }
}