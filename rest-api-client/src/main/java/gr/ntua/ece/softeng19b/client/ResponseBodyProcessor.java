package gr.ntua.ece.softeng19b.client;

import gr.ntua.ece.softeng19b.data.model.*;

import java.io.Reader;
import java.util.List;

public interface ResponseBodyProcessor {

    List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader);

    List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader);

    List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader);

    List<DARecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader);

    List<DARecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader);

    List<DARecordForSpecificYear> consumeDayAheadTotalLoadForecastRecordsForSpecificYear(Reader reader);

    List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader);

    List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader);

    List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader);

    List<AVSFRecordForSpecificDay> consumeActualVsForecastRecordsForSpecificDay(Reader reader);

    List<AVSFRecordForSpecificMonth> consumeActualVsForecastRecordsForSpecificMonth(Reader reader);

    List<AVSFRecordForSpecificYear> consumeActualVsForecastRecordsForSpecificYear(Reader reader);
}
