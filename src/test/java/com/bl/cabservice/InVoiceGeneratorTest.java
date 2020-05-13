package com.bl.cabservice;

import org.junit.Assert;
import org.junit.Test;

public class InVoiceGeneratorTest {
    InVoiceGenerator invoiceGenerator = new InVoiceGenerator();

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFareForJourney() {
        double distance = 3.0;
        int time = 10;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(40, fare, 0);
    }
}
