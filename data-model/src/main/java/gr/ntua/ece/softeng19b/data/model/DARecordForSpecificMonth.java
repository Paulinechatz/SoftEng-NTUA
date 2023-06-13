package gr.ntua.ece.softeng19b.data.model;

public class DARecordForSpecificMonth extends AbstractEntsoeRecord {

    private double dayAheadTotalLoadForecastByDayValue;
    private int day;

    public DARecordForSpecificMonth() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public double getDayAheadTotalLoadForecastByDayValue() {
        return dayAheadTotalLoadForecastByDayValue;
    }

    public void setDayAheadTotalLoadForecastByDayValue(double dayAheadTotalLoadForecastByDayValue) {
        this.dayAheadTotalLoadForecastByDayValue = dayAheadTotalLoadForecastByDayValue;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}