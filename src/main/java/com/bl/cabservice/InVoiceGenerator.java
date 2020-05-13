package com.bl.cabservice;

public class InVoiceGenerator {
    private static final double RATE_PER_KM = 10;
    private static final int RATE_PER_MIN = 1;
    public double calculateFare(double distance, int time) {
        return RATE_PER_KM * distance + RATE_PER_MIN * time;
    }
}