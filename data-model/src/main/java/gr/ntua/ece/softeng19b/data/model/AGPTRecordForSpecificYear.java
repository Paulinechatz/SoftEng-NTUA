package gr.ntua.ece.softeng19b.data.model;

public class AGPTRecordForSpecificYear extends AbstractEntsoeRecord {

    private double actualGenarationOutputByMonthValue;
    private String productionType;
    public AGPTRecordForSpecificYear() {
        super(DataSet.AggregatedGenerationPerType);
    }

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }

    public double getActualGenarationOutputByMonthValue() {
        return actualGenarationOutputByMonthValue;
    }

    public void setActualGenarationOutputByMonthValue(double actualGenarationOutputByMonthValue) {
        this.actualGenarationOutputByMonthValue = actualGenarationOutputByMonthValue;
    }

}
