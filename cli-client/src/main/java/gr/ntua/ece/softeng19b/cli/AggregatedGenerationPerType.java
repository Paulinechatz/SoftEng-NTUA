package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.*;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name="AggregatedGenerationPerType"
)
public class AggregatedGenerationPerType extends EnergyCliArgs implements Callable<Integer> {

    @Option(
        names = "--productiontype",
        required = true,
        description = "the production type."
    )
    protected String productionType;

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }


        try {
            if (dateArgs.date != null ) {
                List<AGPTRecordForSpecificDay> records = new RestAPI().
                        getAGPT(areaName, productionType, timeres.name(), LocalDate.parse(dateArgs.date), format);
                // Do something with the records :)
                if ( format.name() == "JSON" ) {
                    for (int i = 0; i < records.size(); i++) {
                        if (i==0){
                            System.out.println("[ {");
                        }
                        else {
                            System.out.println("{");
                        }
                        System.out.println("Source : " +records.get(i).getSource()+ ",");
                        System.out.println("Dataset : " +records.get(i).getDataSet()+ ",");
                        System.out.println("AreaName : " +records.get(i).getAreaName()+ ",");
                        System.out.println("AreaTypeCode : " +records.get(i).getAreaTypeCode()+ ",");
                        System.out.println("MapCode : " +records.get(i).getMapCode()+ ",");
                        System.out.println("ResolutionCode : " +records.get(i).getResolutionCode()+ ",");
                        System.out.println("Year : " +records.get(i).getYear()+ ",");
                        System.out.println("Month : " +records.get(i).getMonth()+ ",");
                        System.out.println("Day : " +records.get(i).getDay()+ ",");
                        System.out.println("DataTimeUTC : " +records.get(i).getDateTimeUTC()+ ",");
                        System.out.println("ProductionType : " +records.get(i).getProductionType()+ ",");
                        System.out.println("ActualGenerationOutputValue : " +records.get(i).getActualGenerationOutputValue()+ ",");
                        System.out.println("UpdateTimeUTC : " +records.get(i).getUpdateTimeUTC());
                        if (i==records.size()-1){
                            System.out.println("} ]");
                        }
                        else {
                            System.out.println("}");
                        }
                    }
                }

                else {
                    System.out.println("Source,Dataset,AreaName,AreaTypeCode,MapCode,ResolutionCode,Year,Month,Day,DateTimeUTC,ProductionType,ActualGenerationOutputValue,UpdateTimeUTC");
                    for (int i=0; i<records.size(); i++){
                        System.out.print(records.get(i).getSource()+ ",");
                        System.out.print(records.get(i).getDataSet()+ ",");
                        System.out.print(records.get(i).getAreaName()+ ",");
                        System.out.print(records.get(i).getAreaTypeCode()+ ",");
                        System.out.print(records.get(i).getMapCode()+ ",");
                        System.out.print(records.get(i).getResolutionCode()+ ",");
                        System.out.print(records.get(i).getYear()+ ",");
                        System.out.print(records.get(i).getMonth()+ ",");
                        System.out.print(records.get(i).getDay()+ ",");
                        System.out.print(records.get(i).getDateTimeUTC()+ ",");
                        System.out.print(records.get(i).getProductionType()+ ",");
                        System.out.print(records.get(i).getActualGenerationOutputValue()+ ",");
                        System.out.println(records.get(i).getUpdateTimeUTC());
                    }
                }
                return 0;
            }
            else if (dateArgs.month != null){
                List<AGPTRecordForSpecificMonth> records = new RestAPI().
                        getAGPTMonth(areaName, productionType, timeres.name(), dateArgs.month, format);
                if ( format.name() == "JSON" ) {
                    for (int i = 0; i < records.size(); i++) {
                        if (i==0){
                            System.out.println("[ {");
                        }
                        else {
                            System.out.println("{");
                        }
                        System.out.println("Source : " +records.get(i).getSource()+ ",");
                        System.out.println("Dataset : " +records.get(i).getDataSet()+ ",");
                        System.out.println("AreaName : " +records.get(i).getAreaName()+ ",");
                        System.out.println("AreaTypeCode : " +records.get(i).getAreaTypeCode()+ ",");
                        System.out.println("MapCode : " +records.get(i).getMapCode()+ ",");
                        System.out.println("ResolutionCode : " +records.get(i).getResolutionCode()+ ",");
                        System.out.println("Year : " +records.get(i).getYear()+ ",");
                        System.out.println("Month : " +records.get(i).getMonth()+ ",");
                        System.out.println("Day : " +records.get(i).getDay()+ ",");
                        System.out.println("ProductionPerType : " +records.get(i).getProductionType()+ ",");
                        System.out.println("ActualGenerationOutputByDayValue : " +records.get(i).getActualGenerationOutputByDayValue());
                        if (i==records.size()-1){
                            System.out.println("} ]");
                        }
                        else {
                            System.out.println("}");
                        }
                    }
                }

                else {
                    System.out.println("Source,Dataset,AreaName,AreaTypeCode,MapCode,ResolutionCode,Year,Month,Day,ProductionType,ActualGenerationOutputByDayValue");
                    for (int i=0; i<records.size(); i++){
                        System.out.print(records.get(i).getSource()+ ",");
                        System.out.print(records.get(i).getDataSet()+ ",");
                        System.out.print(records.get(i).getAreaName()+ ",");
                        System.out.print(records.get(i).getAreaTypeCode()+ ",");
                        System.out.print(records.get(i).getMapCode()+ ",");
                        System.out.print(records.get(i).getResolutionCode()+ ",");
                        System.out.print(records.get(i).getYear()+ ",");
                        System.out.print(records.get(i).getMonth()+ ",");
                        System.out.print(records.get(i).getDay()+ ",");
                        System.out.print(records.get(i).getProductionType()+ ",");
                        System.out.println(records.get(i).getActualGenerationOutputByDayValue());
                    }
                }

            }
            else if (dateArgs.year != null){
                List<AGPTRecordForSpecificYear> records = new RestAPI().
                        getAGPTYear(areaName, productionType, timeres.name(), dateArgs.year, format);
                if ( format.name() == "JSON" ) {
                    for (int i = 0; i < records.size(); i++) {
                        if (i==0){
                            System.out.println("[ {");
                        }
                        else {
                            System.out.println("{");
                        }
                        System.out.println("Source : " +records.get(i).getSource()+ ",");
                        System.out.println("Dataset : " +records.get(i).getDataSet()+ ",");
                        System.out.println("AreaName : " +records.get(i).getAreaName()+ ",");
                        System.out.println("AreaTypeCode :" +records.get(i).getAreaTypeCode()+ ",");
                        System.out.println("MapCode : " +records.get(i).getMapCode()+ ",");
                        System.out.println("ResolutionCode : " +records.get(i).getResolutionCode()+ ",");
                        System.out.println("Year : " +records.get(i).getYear()+ ",");
                        System.out.println("Month : " +records.get(i).getMonth()+ ",");
                        System.out.println("ProductionType : " +records.get(i).getProductionType()+ ",");
                        System.out.println("ActualGenerationOutputByMonthValue : " +records.get(i).getActualGenarationOutputByMonthValue());
                        if (i==records.size()-1){
                            System.out.println("} ]");
                        }
                        else {
                            System.out.println("}");
                        }
                    }
                }

                else {
                    System.out.println("Source,Dataset,AreaName,AreaTypeCode,MapCode,ResolutionCode,Year,Month,ProductionType,ActualGenerationOutputByMonthValue");
                    for (int i=0; i<records.size(); i++){
                        System.out.print(records.get(i).getSource()+ ",");
                        System.out.print(records.get(i).getDataSet()+ ",");
                        System.out.print(records.get(i).getAreaName()+ ",");
                        System.out.print(records.get(i).getAreaTypeCode()+ ",");
                        System.out.print(records.get(i).getMapCode()+ ",");
                        System.out.print(records.get(i).getResolutionCode()+ ",");
                        System.out.print(records.get(i).getYear()+ ",");
                        System.out.print(records.get(i).getMonth()+ ",");
                        System.out.print(records.get(i).getProductionType()+ ",");
                        System.out.println(records.get(i).getActualGenarationOutputByMonthValue());
                    }
                }

            }

            return 0;
         /*   else {
                // Implement the other cases
                System.err.println("Not implemented yet");
                return -1;
            }*/
        } catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }

    }

}