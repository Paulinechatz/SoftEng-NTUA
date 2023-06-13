package gr.ntua.ece.softeng19b.data.model;

public class AGPTRecordForSpecificMonth extends AbstractEntsoeRecord {

    private double actualGenerationOutputByDayValue;
    private String productionType;
    private int day;

    public AGPTRecordForSpecificMonth() {
        super(DataSet.AggregatedGenerationPerType);
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }

    public String getProductionType() {
        return productionType;
    }

    public double getActualGenerationOutputByDayValue() {
        return actualGenerationOutputByDayValue;
    }

    public void setActualGenerationOutputByDayValue(double actualGenerationOutputByDayValue) {
        this.actualGenerationOutputByDayValue = actualGenerationOutputByDayValue;
    }

    public int getDay () { return day;}

    public void setDay(int day) {
        this.day = day;
    }
}
