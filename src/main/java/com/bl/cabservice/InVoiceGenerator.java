package com.bl.cabservice;

import com.bl.cabservice.repository.Ride;
import com.bl.cabservice.summary.InvoiceSummary;

import java.util.ArrayList;

public interface InVoiceGenerator {
   double calculateFare(double distance, int time);
    double calculateFareForMultipleRides(Ride[] rides);
    InvoiceSummary getInvoiceSummary(Ride[] rides);
    void addRides(String userId);
    ArrayList<Ride> getRidesByUserId(String userId);
}
