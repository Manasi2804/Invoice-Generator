package com.bl.cabservice;

import java.util.ArrayList;

public class InVoiceGenerator {
    private static final double RATE_PER_KM = 10;
    private static final int RATE_PER_MIN = 1;
    private static final double MINIMUM_FARE = 5.0;
    RideRepository rideRepository = new RideRepository();
    ArrayList<Ride> listOfRides = new ArrayList<Ride>();

    public double calculateFare(double distance, int time) {
        double totalFare = RATE_PER_KM * distance + RATE_PER_MIN * time;
        return Math.max(totalFare, MINIMUM_FARE);
    }
    public double calculateFareForMultipleRides(Ride[] rides) {
        double aggregateFare = 0;
        for (Ride ride : rides) {
            listOfRides.add(ride);
            aggregateFare = aggregateFare + calculateFare(ride.distance, ride.time);
        }
        return aggregateFare;
    }
    public InvoiceSummary getInvoiceSummary(Ride[] rides) {
        return new InvoiceSummary(rides.length, calculateFareForMultipleRides(rides));
    }
    public void addRides(String userId) {
        rideRepository.addUserRides(userId, listOfRides);
    }
    public ArrayList<Ride> getRidesByUserId(String userId) {
        ArrayList<Ride> ridesByUserId = rideRepository.getRidesByUserId(userId);
        return ridesByUserId;
    }
}