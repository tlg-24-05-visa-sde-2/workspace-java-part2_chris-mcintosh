package com.entertainment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TelevisionBrandChannelComparatorTest {
    private Television tv1;
    private Television tv2;
    private Television tv3;
    private Television tv4;
    private TelevisionBrandChannelComparator comparator;

    @Before
    public void setUp() {
        tv1 = new Television("Samsung");
        tv2 = new Television("Samsung");
        tv3 = new Television("Sony", 1);
        tv4 = new Television("Sony", 2);
        comparator = new TelevisionBrandChannelComparator();
    }

    @Test
    public void compare_shouldReturnNegativeNumber_when1stBrandLessThan2ndBrand() {
        tv1.setBrand("A_lessThan");
        tv2.setBrand("B_greaterThan");
        assertTrue(comparator.compare(tv1, tv2) < 0);
    }
    @Test
    public void compare_shouldReturnPositive_when1stBrandGreaterThan2ndBrand() {
        tv1.setBrand("B_greaterThan");
        tv2.setBrand("A_lessThan");
        assertTrue(comparator.compare(tv1, tv2) > 0);
    }

    @Test
    public void compare_shouldReturnZero_whenBrandsAreSame_and1stChannelLessThan2ndChannel() throws InvalidChannelException {
        tv1.changeChannel(49);
        tv2.changeChannel(50);
        assertTrue(comparator.compare(tv1, tv2) < 0);
    }

    @Test
    public void compare_shouldReturnZero_whenBrandsAreSame_and1stChannelGreaterThan2ndChannel() throws InvalidChannelException {
        tv2.changeChannel(5);
        tv1.changeChannel(2);
        assertTrue(comparator.compare(tv1, tv2) < 0);
    }

    @Test
    public void compare_shouldReturnZero_whenBrandsAndChannelsAreSame() {
        Television tv5 = new Television("Samsung", 1);
        assertEquals(0, comparator.compare(tv1, tv5));
    }

    @Test
    public void compare_shouldReturnNegative_when1stBrandSame_as2ndBrand_and1stChannelLessThan2ndChannel() throws InvalidChannelException {
        tv1.changeChannel(49);
        tv2.changeChannel(50);
        assertTrue(comparator.compare(tv1, tv2) < 0);
    }

    @Test
    public void compare_shouldReturnPositive_when1stBrandSame_as2ndBrand_and1stChannelGreaterThan2ndChannel() throws InvalidChannelException {
        tv1.changeChannel(5);
        tv2.changeChannel(1);
        assertTrue(comparator.compare(tv1, tv2) > 0);
    }

    @Test
    public void compare_shouldReturnZero_when1stBrandSame_as2ndBrand_and1stChannelSame_as2ndChannel() {
        Television tv5 = new Television("Samsung", 1);
        assertEquals(0, comparator.compare(tv1, tv5));
    }
}