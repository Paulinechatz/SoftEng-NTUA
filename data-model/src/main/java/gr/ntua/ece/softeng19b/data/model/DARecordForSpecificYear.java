package gr.ntua.ece.softeng19b.data.model;

public class DARecordForSpecificYear extends AbstractEntsoeRecord {

    private double dayAheadTotalLoadForecastByMonthValue;

    public DARecordForSpecificYear() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public double getDayAheadTotalLoadForecastByMonthValue() {
        return dayAheadTotalLoadForecastByMonthValue;
    }

    public void setDayAheadTotalLoadForecastByMonthValue(double dayAheadTotalLoadForecastByMonthValue) {
        this.dayAheadTotalLoadForecastByMonthValue = dayAheadTotalLoadForecastByMonthValue;
    }
}