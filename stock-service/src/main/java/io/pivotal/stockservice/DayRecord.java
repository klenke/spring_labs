package io.pivotal.stockservice;

public class DayRecord {

    public double max;
    public double min;
    public int volume;

    public String date;
    public String symbol;

    public DayRecord(){}

    public DayRecord(double max, double min, int volume, String date, String symbol) {
        this.max = max;
        this.min = min;
        this.volume = volume;
        this.date = date;
        this.symbol = symbol;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
