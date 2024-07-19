package com.entertainment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TelevisionChannelComparatorTest {
    private Television tv1;
    private Television tv2;
    private Television tv3;
    private TelevisionChannelComparator comparator;

    @Before
    public void setUp() {
        tv1 = new Television("Sony");
        tv2 = new Television("Sony");
        tv3 = new Television("Sony");
        comparator = new TelevisionChannelComparator();
    }


    @Test
    public void compare_shouldReturnNegative_whenFirstTelevisionHasLowerChannel() throws Exception {
           tv1.changeChannel(999);
           int result = comparator.compare(tv1, tv2);
           assertTrue(result < 0);
    }

    @Test
    public void compare_shouldReturnPositive_whenFirstTelevisionHasHigherChannel() {
        int result = comparator.compare(tv2, tv1);
        assertEquals(1, result);
    }

    @Test
    public void compare_shouldReturnNegative_whenFirstTelevisionHasLowerChannel_thanThird() {
        int result = comparator.compare(tv1, tv3);
        assertEquals(-1, result);
    }

    @Test
    public void compare_shouldReturnPositive_whenFirstTelevisionHasHigherChannel_thanThird() {
        int result = comparator.compare(tv3, tv1);
        assertEquals(1, result);
    }
}