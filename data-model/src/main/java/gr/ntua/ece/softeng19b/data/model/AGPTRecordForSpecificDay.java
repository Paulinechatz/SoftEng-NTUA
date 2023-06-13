package gr.ntua.ece.softeng19b.data.model;

public class AGPTRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double actualGenerationOutputValue;
    private String dateTimeUTC;
    private String updateTimeUTC;
    private String productionType;

    public AGPTRecordForSpecificDay() {
        super(DataSet.AggregatedGenerationPerType);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getActualGenerationOutputValue() {
        return actualGenerationOutputValue;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }

    public void setActualGenerationOutputValue(double actualGenerationOutputValue) {
        this.actualGenerationOutputValue = actualGenerationOutputValue;
    }

    public String getProductionType() {
        return productionType;
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
}
