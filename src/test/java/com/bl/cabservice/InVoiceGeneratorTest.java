package com.bl.cabservice;

import com.bl.cabservice.repository.Ride;
import com.bl.cabservice.summary.InvoiceSummary;
import org.junit.Assert;
import org.junit.Test;

public class InVoiceGeneratorTest {
    InVoiceGeneratorService invoiceForNormalPlan = new InVoiceGeneratorService(InVoiceGeneratorService.subscriptionPlan.NORMAL);
    InVoiceGeneratorService invoiceForPremiumPlan = new InVoiceGeneratorService(InVoiceGeneratorService.subscriptionPlan.PREMIUM);

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 3.0;
        int time = 10;
        double fare = invoiceForNormalPlan.calculateFare(distance, time);
        Assert.assertEquals(40, fare, 0);
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 2;
        double fare = invoiceForNormalPlan.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0);
    }

    @Test
    public void givenMultipleRideDetails_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        double totalFare = invoiceForNormalPlan.calculateFareForMultipleRides(rides);
        Assert.assertEquals(181, totalFare, 0);
    }

    @Test
    public void givenMultipleRideDetails_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        InvoiceSummary invoiceSummary = invoiceForNormalPlan.getInvoiceSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 181.0);
        Assert.assertEquals(expectedSummary, invoiceSummary);
    }

    @Test
    public void givenUserId_ShouldReturnListOfRidesAndInvoice() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        invoiceForNormalPlan.addRides(userId);
        InvoiceSummary invoiceSummary = invoiceForNormalPlan.getInvoiceSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 181.0);
        Assert.assertEquals(expectedSummary, invoiceSummary);
        Assert.assertEquals(rides.length, invoiceForNormalPlan.getRidesByUserId(userId).size());
    }

    @Test
    public void givenDistanceAndTime_WithPremiumSubscriptionPlan_ShouldReturnTotalFare() {
        double distance = 3.0;
        int time = 10;
        double fare = invoiceForPremiumPlan.calculateFare(distance, time);
        Assert.assertEquals(65, fare, 0);
    }

    @Test
    public void givenDistanceAndTime_WithPremiumSubscriptionPlan_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 2;
        double fare = invoiceForPremiumPlan.calculateFare(distance, time);
        Assert.assertEquals(20, fare, 0);
    }

    @Test
    public void givenMultipleRideDetails_WithPremiumSubscriptionPlan_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        double totalFare = invoiceForPremiumPlan.calculateFareForMultipleRides(rides);
        Assert.assertEquals(297, totalFare, 0);
    }

    @Test
    public void givenMultipleRideDetails_WithPremiumSubscriptionPlan_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        InvoiceSummary invoiceSummary = invoiceForPremiumPlan.getInvoiceSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3,297.0);
        Assert.assertEquals(expectedSummary, invoiceSummary);
    }

    @Test
    public void givenUserId_WithPremiumSubscriptionPlan_ShouldReturnListOfRidesAndInvoice() {
        String userId= "xyz@b.com";
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        invoiceForPremiumPlan.addRides(userId);
        InvoiceSummary invoiceSummary = invoiceForPremiumPlan.getInvoiceSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3,297.0);
        Assert.assertEquals(expectedSummary, invoiceSummary);
        Assert.assertEquals(rides.length, invoiceForPremiumPlan.getRidesByUserId(userId).size());
    }
}