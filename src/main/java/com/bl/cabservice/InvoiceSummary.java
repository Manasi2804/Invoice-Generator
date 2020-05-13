package com.bl.cabservice;

public class InvoiceSummary {
    public int noOfRides;
    public double totalFare;
    public double averageFarePerRide;

    public InvoiceSummary(int noOfRides, double totalFare) {
        this.noOfRides = noOfRides;
        this.totalFare = totalFare;
        this.averageFarePerRide = totalFare / noOfRides;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return noOfRides == that.noOfRides &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                Double.compare(that.averageFarePerRide, averageFarePerRide) == 0;
    }
    @Override
    public int hashCode() {
        return 0;
    }
    @Override
    public String toString() {
        return "InvoiceSummary -> {" +
                "noOfRides=" + noOfRides +
                ", totalFare=" + totalFare +
                " , averageFarePerRide=" + averageFarePerRide +
                '}';
    }
}
