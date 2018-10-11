package com.p2.microservices.limitsservice.bean;

public class LimitsConfiguration {

    int max = 100;
    int min = 1;

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    protected LimitsConfiguration(){}

    public LimitsConfiguration(int max, int min) {
        this.max = max;
        this.min = min;
    }
}
