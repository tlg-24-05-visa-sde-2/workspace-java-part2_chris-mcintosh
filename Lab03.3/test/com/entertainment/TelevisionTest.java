package com.entertainment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TelevisionTest {
    private Television tv1;
    private Television tv2;

    @Before
    public void setUp() {
        tv1 = new Television("Sony", 50, DisplayType.PLASMA); //channel is 3
        tv2 = new Television("Sony", 50, DisplayType.PLASMA); //channel is 3
    }


    //TODO - we still need to check 0 and 1000


    @Test
    public void changeChannel_shouldThrowInvalidChannelException_whenInvalid_lowerBound() {
        try {
            tv1.changeChannel(0); // should throw exception
            fail("Should throw InvalidChannelException");
        }
        catch (InvalidChannelException e) {
            String expected = "Invalid channel: 0. Allowed range: [1,999].";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void changeChannel_shouldStoreValue_whenValid_UpperBound() throws InvalidChannelException {
        tv1.changeChannel(999);
        assertEquals(999, tv1.getCurrentChannel());
    }

    @Test
    public void changeChannel_shouldStoreValue_whenValid_LowerBound() throws InvalidChannelException {
        tv1.changeChannel(1);
        assertEquals(1, tv1.getCurrentChannel());
    }

    //TODO - we still need to check -1 and 101


    @Test(expected = IllegalArgumentException.class)
    public void setVolume_shouldThrowIllegalArgumentException_whenInvalid_lowerBound() {
        tv1.setVolume(-1); //should throw exception

    }

    @Test
    public void setVolume_shouldStoreValue_whenValid_upperBound() {
        tv1.setVolume(100);
        assertEquals(100, tv1.getVolume());
    }

    @Test
    public void setVolume_shouldStoreValue_whenValid() {
        tv1.setVolume(0);
        assertEquals(0, tv1.getVolume());
    }


}