package gr.ntua.ece.softeng19b.data.model;

public class AVSFRecordForSpecificYear extends AbstractEntsoeRecord {

    private double actualDataLoadByMonthValue;
    private double dayAheadTotalLoadForecastByMonthValue;

    public AVSFRecordForSpecificYear() {
        super(DataSet.ActualTotalLoad);
    }

    public double getActualDataLoadByMonthValue() {
        return actualDataLoadByMonthValue;
    }

    public void setActualDataLoadByMonthValue(double actualDataLoadByMonthValue) {
        this.actualDataLoadByMonthValue = actualDataLoadByMonthValue;
    }

    public void setDayAheadTotalLoadForecastByMonthValue(double dayAheadTotalLoadForecastByMonthValue) {
        this.dayAheadTotalLoadForecastByMonthValue = dayAheadTotalLoadForecastByMonthValue;
    }

    public double getDayAheadTotalLoadForecastByMonthValue() {
        return dayAheadTotalLoadForecastByMonthValue;
    }
}
