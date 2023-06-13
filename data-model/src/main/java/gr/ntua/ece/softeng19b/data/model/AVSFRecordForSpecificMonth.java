package gr.ntua.ece.softeng19b.data.model;

public class AVSFRecordForSpecificMonth extends AbstractEntsoeRecord {

    private double actualTotalLoadByDayValue;
    private double dayAheadTotalLoadForecastByDayValue;

    private int day;

    public AVSFRecordForSpecificMonth() {
        super(DataSet.ActualTotalLoad);
    }

    public double getActualTotalLoadByDayValue() {
        return actualTotalLoadByDayValue;
    }

    public void setActualTotalLoadByDayValue(double actualTotalLoadByDayValue) {
        this.actualTotalLoadByDayValue = actualTotalLoadByDayValue;
    }

    public double getDayAheadTotalLoadForecastByDayValue() {
        return dayAheadTotalLoadForecastByDayValue;
    }

    public void setDayAheadTotalLoadForecastByDayValue(double dayAheadTotalLoadForecastByDayValue) {
        this.dayAheadTotalLoadForecastByDayValue = dayAheadTotalLoadForecastByDayValue;
    }

    public int getDay () { return day;}

    public void setDay(int day) {
        this.day = day;
    }
}

