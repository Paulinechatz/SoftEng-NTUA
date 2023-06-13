package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.Format;
import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.*;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
        name="DayAheadTotalLoadForecast"
)
public class DayAheadTotalLoadForecast extends EnergyCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }


        try {
            if (dateArgs.date != null ) {
                List<DARecordForSpecificDay> records = new RestAPI().
                        getDayAheadTotalLoad(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
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
                        System.out.println("DayAheadTotalLoadForecastValue : " +records.get(i).getDayAheadTotalLoadForecastValue()+ ",");
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
                    System.out.println("Source,Dataset,AreaName,AreaTypeCode,MapCode,ResolutionCode,Year,Month,Day,DateTimeUTC,DayAheadTotalLoadForecastValue,UpdateTimeUTC");
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
                        System.out.print(records.get(i).getDayAheadTotalLoadForecastValue()+ ",");
                        System.out.println(records.get(i).getUpdateTimeUTC());
                    }
                }
                return 0;
            }
            else if (dateArgs.month != null){
                List<DARecordForSpecificMonth> records = new RestAPI().
                        getDayAheadTotalLoadMonth(areaName, timeres.name(), dateArgs.month, format);
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
                        System.out.println("DayAheadTotalLoadForecastByDayValue : " +records.get(i).getDayAheadTotalLoadForecastByDayValue());
                        if (i==records.size()-1){
                            System.out.println("} ]");
                        }
                        else {
                            System.out.println("}");
                        }
                    }
                }

                else {
                    System.out.println("Source,Dataset,AreaName,AreaTypeCode,MapCode,ResolutionCode,Year,Month,Day,DayAheadTotalLoadForecastByDayValue");
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
                        System.out.println(records.get(i).getDayAheadTotalLoadForecastByDayValue());
                    }
                }

            }
            else if (dateArgs.year != null){
                List<DARecordForSpecificYear> records = new RestAPI().
                        getDayAheadTotalLoadYear(areaName, timeres.name(), dateArgs.year, format);
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
                        System.out.println("DayAheadTotalLoadForecastByMonthValue : " +records.get(i).getDayAheadTotalLoadForecastByMonthValue());
                        if (i==records.size()-1){
                            System.out.println("} ]");
                        }
                        else {
                            System.out.println("}");
                        }
                    }
                }

                else {
                    System.out.println("Source,Dataset,AreaName,AreaTypeCode,MapCode,ResolutionCode,Year,Month,DayAheadTotalLoadForecastByMonthValue");
                    for (int i=0; i<records.size(); i++){
                        System.out.print(records.get(i).getSource()+ ",");
                        System.out.print(records.get(i).getDataSet()+ ",");
                        System.out.print(records.get(i).getAreaName()+ ",");
                        System.out.print(records.get(i).getAreaTypeCode()+ ",");
                        System.out.print(records.get(i).getMapCode()+ ",");
                        System.out.print(records.get(i).getResolutionCode()+ ",");
                        System.out.print(records.get(i).getYear()+ ",");
                        System.out.print(records.get(i).getMonth()+ ",");
                        System.out.println(records.get(i).getDayAheadTotalLoadForecastByMonthValue());
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
