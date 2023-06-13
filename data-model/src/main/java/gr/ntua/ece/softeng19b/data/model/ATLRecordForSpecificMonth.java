package gr.ntua.ece.softeng19b.data.model;

public class ATLRecordForSpecificMonth extends AbstractEntsoeRecord {

    private double actualTotalLoadByDayValue;

    private int day;

    public ATLRecordForSpecificMonth() {
        super(DataSet.ActualTotalLoad);
    }

    public double getActualTotalLoadByDayValue() {
        return actualTotalLoadByDayValue;
    }

    public void setActualTotalLoadByDayValue(double actualTotalLoadByDayValue) {
        this.actualTotalLoadByDayValue = actualTotalLoadByDayValue;
    }

    public int getDay () { return day;}

    public void setDay(int day) {
        this.day = day;
    }
}
