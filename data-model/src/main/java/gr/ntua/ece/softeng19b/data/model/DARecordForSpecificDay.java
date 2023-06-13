package gr.ntua.ece.softeng19b.data.model;

public class DARecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double dayAheadTotalLoadForecastValue;
    private String dateTimeUTC;
    private String updateTimeUTC;

    public DARecordForSpecificDay() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getDayAheadTotalLoadForecastValue() {
        return dayAheadTotalLoadForecastValue;
    }

    public void setDayAheadTotalLoadForecastValue(double dayAheadTotalLoadForecastValue) {
        this.dayAheadTotalLoadForecastValue = dayAheadTotalLoadForecastValue;
    }

    public void setUpdateTimeUTC(String updateTimeUTC) {
        this.updateTimeUTC = updateTimeUTC;
    }

    public void setDateTimeUTC(String dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    public String getUpdateTimeUTC() {
        return updateTimeUTC;
    }

    public String getDateTimeUTC() {
        return dateTimeUTC;
    }
}