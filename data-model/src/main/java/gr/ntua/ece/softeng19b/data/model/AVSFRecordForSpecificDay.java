package gr.ntua.ece.softeng19b.data.model;

public class AVSFRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double actualTotalLoadValue;
    private double dayAheadTotalLoadForecastValue;
    private String dateTimeUTC;
    private String updateTimeUTC;

    public AVSFRecordForSpecificDay() {
        super(DataSet.ActualVSForecastedTotalLoad);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getActualTotalLoadValue() {
        return actualTotalLoadValue;
    }

    public void setActualTotalLoadValue(double actualTotalLoadValue) {
        this.actualTotalLoadValue = actualTotalLoadValue;
    }

    public String getDateTimeUTC() {
        return dateTimeUTC;
    }

    public String getUpdateTimeUTC() {
        return updateTimeUTC;
    }

    public void setDateTimeUTC(String dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    public void setUpdateTimeUTC(String updateTimeUTC) {
        this.updateTimeUTC = updateTimeUTC;
    }

    public double getDayAheadTotalLoadForecastValue() {
        return dayAheadTotalLoadForecastValue;
    }

    public void setDayAheadTotalLoadForecastValue(double dayAheadTotalLoadForecastValue) {
        this.dayAheadTotalLoadForecastValue = dayAheadTotalLoadForecastValue;
    }
}
