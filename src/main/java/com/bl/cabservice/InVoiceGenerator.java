package com.bl.cabservice;

public class InVoiceGenerator {
    private static final double RATE_PER_KM = 10;
    private static final int RATE_PER_MIN = 1;
    private static final double MINIMUM_FARE = 5.0;

    public double calculateFare(double distance, int time) {
        double totalFare = RATE_PER_KM * distance + RATE_PER_MIN * time;
        return Math.max(totalFare, MINIMUM_FARE);
    }
    public double calculateFareForMultipleRides(Ride[] rides) {
        double aggregateFare = 0;
        for (Ride ride : rides) {
            aggregateFare = aggregateFare + calculateFare(ride.distance, ride.time);
        }
        return aggregateFare;
    }
    public InvoiceSummary getInvoiceSummary(Ride[] rides) {
        return new InvoiceSummary(rides.length, calculateFareForMultipleRides(rides));
    }
}