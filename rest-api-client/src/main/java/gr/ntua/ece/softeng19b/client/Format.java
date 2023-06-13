package gr.ntua.ece.softeng19b.client;

import com.google.gson.stream.JsonReader;
import gr.ntua.ece.softeng19b.data.model.*;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


public enum Format implements ResponseBodyProcessor {

    JSON {
        @Override
        public List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualDataLoadRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return Format.readActualDataLoadRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return Format.readActualDataLoadRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<DARecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadDataLoadRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<DARecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return Format.readDayAheadDataLoadRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<DARecordForSpecificYear> consumeDayAheadTotalLoadForecastRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return Format.readDayAheadDataLoadRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return Format.readAggregatedGenerationPerTypeRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return Format.readAggregatedGenerationPerTypeRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<AVSFRecordForSpecificDay> consumeActualVsForecastRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readAVSFRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<AVSFRecordForSpecificMonth> consumeActualVsForecastRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return Format.readAVSFRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        public List<AVSFRecordForSpecificYear> consumeActualVsForecastRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return Format.readAVSFRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
    },
    CSV {
        @Override
        public List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<ATLRecordForSpecificDay> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    ATLRecordForSpecificDay record = new ATLRecordForSpecificDay();

                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
                    record.setResolutionCode(nextRecord[5]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
                    record.setMonth(Integer.parseInt(nextRecord[7]));
                    record.setDay(Integer.parseInt(nextRecord[8]));
                    record.setDateTimeUTC(nextRecord[9]);
                    record.setActualTotalLoadValue( Double.parseDouble(nextRecord[10]));
                    record.setUpdateTimeUTC(nextRecord[11]);
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
                     throw new UnsupportedOperationException("Implement this");
        }

        public List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<ATLRecordForSpecificMonth> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    ATLRecordForSpecificMonth record = new ATLRecordForSpecificMonth();

                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
                    record.setResolutionCode(nextRecord[5]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
                    record.setMonth(Integer.parseInt(nextRecord[7]));
                    record.setDay(Integer.parseInt(nextRecord[8]));
                    record.setActualTotalLoadByDayValue( Double.parseDouble(nextRecord[9]));
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<ATLRecordForSpecificYear> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    ATLRecordForSpecificYear record = new ATLRecordForSpecificYear();

                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
                    record.setResolutionCode(nextRecord[5]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
                    record.setMonth(Integer.parseInt(nextRecord[7]));
                    record.setActualDataLoadByMonthValue( Double.parseDouble(nextRecord[8]));
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<DARecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<DARecordForSpecificDay> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    DARecordForSpecificDay record = new DARecordForSpecificDay();

                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
                    record.setResolutionCode(nextRecord[5]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
                    record.setMonth(Integer.parseInt(nextRecord[7]));
                    record.setDay(Integer.parseInt(nextRecord[8]));
                    record.setDateTimeUTC(nextRecord[9]);
                    record.setDayAheadTotalLoadForecastValue( Double.parseDouble(nextRecord[10]));
                    record.setUpdateTimeUTC(nextRecord[11]);
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<DARecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<DARecordForSpecificMonth> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    DARecordForSpecificMonth record = new DARecordForSpecificMonth();

                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
                    record.setResolutionCode(nextRecord[5]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
                    record.setMonth(Integer.parseInt(nextRecord[7]));
                    record.setDay(Integer.parseInt(nextRecord[8]));
                    record.setDayAheadTotalLoadForecastByDayValue( Double.parseDouble(nextRecord[9]));
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<DARecordForSpecificYear> consumeDayAheadTotalLoadForecastRecordsForSpecificYear(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<DARecordForSpecificYear> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    DARecordForSpecificYear record = new DARecordForSpecificYear();

                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
                    record.setResolutionCode(nextRecord[5]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
                    record.setMonth(Integer.parseInt(nextRecord[7]));
                    record.setDayAheadTotalLoadForecastByMonthValue( Double.parseDouble(nextRecord[8]));
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<AGPTRecordForSpecificDay> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    AGPTRecordForSpecificDay record = new AGPTRecordForSpecificDay();

                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
                    record.setResolutionCode(nextRecord[5]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
                    record.setMonth(Integer.parseInt(nextRecord[7]));
                    record.setDay(Integer.parseInt(nextRecord[8]));
                    record.setDateTimeUTC(nextRecord[9]);
                    record.setProductionType (nextRecord[10]);
                    record.setActualGenerationOutputValue( Double.parseDouble(nextRecord[11]));
                    record.setUpdateTimeUTC(nextRecord[12]);
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<AGPTRecordForSpecificMonth> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    AGPTRecordForSpecificMonth record = new AGPTRecordForSpecificMonth();

                    record.setAreaName(nextRecord[2]);
                //    System.out.println("Areanaem :" +record.getAreaName());
                    record.setAreaTypeCode(nextRecord[3]);
              //      System.out.println("AreaTypeCode :" +record.getAreaTypeCode());
                    record.setMapCode(nextRecord[4]);
            //        System.out.println("MapCode :" +record.getMapCode());
                    record.setResolutionCode(nextRecord[5]);
          //          System.out.println("ResolCode :" +record.getResolutionCode());
                    record.setYear(Integer.parseInt(nextRecord[6]));
        //            System.out.println("Year :" +record.getYear());
       //             System.out.println("nextrecord[7] =" +nextRecord[7]);
                    record.setMonth(Integer.parseInt(nextRecord[7]));
               //     System.out.println("Month :" +record.getMonth());
                    record.setDay(Integer.parseInt(nextRecord[8]));
             //       System.out.println("Day :" +record.getDay());
                    record.setProductionType (nextRecord[9]);
           //         System.out.println("ProductionType : "+record.getProductionType());
                    record.setActualGenerationOutputByDayValue( Double.parseDouble(nextRecord[10]));
         //           System.out.println("Aggregated :" +record.getActualGenerationOutputByDayValue());
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<AGPTRecordForSpecificYear> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    AGPTRecordForSpecificYear record = new AGPTRecordForSpecificYear();

                /*    for(int j=0; j<nextRecord.length; j++){
                        System.out.println("nextrecord["+j+"]=" +nextRecord[j]);
                    }
*/
                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
  //                  System.out.println("MapCode : " +record.getMapCode());
                    record.setResolutionCode(nextRecord[5]);
    //                System.out.println("ResolutionCode :" +record.getResolutionCode());
                    record.setYear(Integer.parseInt(nextRecord[6]));
              //      System.out.println("after");
              //      System.out.println(record.getYear());
              //      System.out.println("nextrecord[7]=" +nextRecord[7]);
                    record.setMonth(Integer.parseInt(nextRecord[7]));
              //      System.out.println(record.getMonth());
               //     System.out.println("nextrecord[9]=" +nextRecord[9]);
                //    System.out.println("nextrecord[10]=" +nextRecord[10]);
                    record.setProductionType (nextRecord[8]);
               //     System.out.println("after");
               //     System.out.println(record.getProductionType());
                    record.setActualGenarationOutputByMonthValue( Double.parseDouble(nextRecord[9]));
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<AVSFRecordForSpecificDay> consumeActualVsForecastRecordsForSpecificDay(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<AVSFRecordForSpecificDay> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    AVSFRecordForSpecificDay record = new AVSFRecordForSpecificDay();

                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
                    record.setResolutionCode(nextRecord[5]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
                    record.setMonth(Integer.parseInt(nextRecord[7]));
                    record.setDay(Integer.parseInt(nextRecord[8]));
                    record.setDateTimeUTC(nextRecord[9]);
                    record.setDayAheadTotalLoadForecastValue(Double.parseDouble(nextRecord[10]));
                    record.setActualTotalLoadValue( Double.parseDouble(nextRecord[11]));
                  //  record.setUpdateTimeUTC(nextRecord[12]);
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<AVSFRecordForSpecificMonth> consumeActualVsForecastRecordsForSpecificMonth(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<AVSFRecordForSpecificMonth> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    AVSFRecordForSpecificMonth record = new AVSFRecordForSpecificMonth();

                    record.setAreaName(nextRecord[2]);
               //     System.out.println("nextrecord[3]=" +nextRecord[3]);
                    record.setAreaTypeCode(nextRecord[3]);
               //     System.out.println("nextrecord[4]=" +nextRecord[4]);
                    record.setMapCode(nextRecord[4]);
               //     System.out.println("nextrecord[5]=" +nextRecord[5]);
                    record.setResolutionCode(nextRecord[5]);
               //     System.out.println("nextrecord[6]=" +nextRecord[6]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
               //     System.out.println("nextrecord[7]=" +nextRecord[7]);
                    record.setMonth(Integer.parseInt(nextRecord[7]));
               //     System.out.println("nextrecord[8]=" +nextRecord[8]);
                    record.setDay(Integer.parseInt(nextRecord[8]));
               //     System.out.println("nextrecord[10]=" +nextRecord[10]);
                    record.setDayAheadTotalLoadForecastByDayValue(Double.parseDouble(nextRecord[10]));
               //     System.out.println("nextrecord[11]=" +nextRecord[11]);
                    record.setActualTotalLoadByDayValue( Double.parseDouble(nextRecord[11]));
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

        public List<AVSFRecordForSpecificYear> consumeActualVsForecastRecordsForSpecificYear(Reader reader) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
                String[] nextRecord;
                List<AVSFRecordForSpecificYear> result = new ArrayList<>();
                int i=0;
                while ((nextRecord = csvReader.readNext()) != null) {
                    AVSFRecordForSpecificYear record = new AVSFRecordForSpecificYear();

                    record.setAreaName(nextRecord[2]);
                    record.setAreaTypeCode(nextRecord[3]);
                    record.setMapCode(nextRecord[4]);
                    record.setResolutionCode(nextRecord[5]);
                    record.setYear(Integer.parseInt(nextRecord[6]));
                    record.setMonth(Integer.parseInt(nextRecord[7]));
                    record.setDayAheadTotalLoadForecastByMonthValue(Double.parseDouble(nextRecord[10]));
                    record.setActualDataLoadByMonthValue( Double.parseDouble(nextRecord[11]));
                    result.add(i,record);
                    i++;
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UnsupportedOperationException("Implement this");
        }

    };

    private static List<ATLRecordForSpecificDay> readActualDataLoadRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualDataLoadRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<ATLRecordForSpecificMonth> readActualDataLoadRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualDataLoadRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<ATLRecordForSpecificYear> readActualDataLoadRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualDataLoadRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<DARecordForSpecificDay> readDayAheadDataLoadRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<DARecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(ReadDayAheadDataLoadRecordsForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<DARecordForSpecificMonth> readDayAheadDataLoadRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<DARecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(ReadDayAheadDataLoadRecordsForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<DARecordForSpecificYear> readDayAheadDataLoadRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<DARecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(ReadDayAheadDataLoadRecordsForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<AGPTRecordForSpecificDay> readAggregatedGenerationPerTypeRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<AGPTRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(ReadAggregatedGenerationPerTypeRecordsForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<AGPTRecordForSpecificMonth> readAggregatedGenerationPerTypeRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<AGPTRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(ReadAggregatedGenerationPerTypeRecordsForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<AGPTRecordForSpecificYear> readAggregatedGenerationPerTypeRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<AGPTRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(ReadAggregatedGenerationPerTypeRecordsForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<AVSFRecordForSpecificDay> readAVSFRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<AVSFRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(ReadAVSFRecordsForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<AVSFRecordForSpecificMonth> readAVSFRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<AVSFRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(ReadAVSFRecordsForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<AVSFRecordForSpecificYear> readAVSFRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<AVSFRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(ReadAVSFRecordsForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }


    private static ATLRecordForSpecificDay readActualDataLoadRecordForSpecificDay(JsonReader reader)
            throws IOException {
        ATLRecordForSpecificDay rec = new ATLRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ActualTotalLoadValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;
                case "DateTimeUTC":
                    rec.setDateTimeUTC(reader.nextString());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpdateTimeUTC(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static ATLRecordForSpecificMonth readActualDataLoadRecordForSpecificMonth(JsonReader reader)
            throws IOException {
        ATLRecordForSpecificMonth rec = new ATLRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ActualTotalLoadByDayValue":
                    rec.setActualTotalLoadByDayValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static ATLRecordForSpecificYear readActualDataLoadRecordForSpecificYear(JsonReader reader)
            throws IOException {
        ATLRecordForSpecificYear rec = new ATLRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "ActualTotalLoadByMonthValue":
                    rec.setActualDataLoadByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static DARecordForSpecificDay ReadDayAheadDataLoadRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        DARecordForSpecificDay rec = new DARecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                case "DateTimeUTC":
                    rec.setDateTimeUTC(reader.nextString());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpdateTimeUTC(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static DARecordForSpecificMonth ReadDayAheadDataLoadRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        DARecordForSpecificMonth rec = new DARecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DayAheadTotalLoadForecastByDayValue":
                    rec.setDayAheadTotalLoadForecastByDayValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static DARecordForSpecificYear ReadDayAheadDataLoadRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        DARecordForSpecificYear rec = new DARecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "DayAheadTotalLoadForecastByMonthValue":
                    rec.setDayAheadTotalLoadForecastByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static AGPTRecordForSpecificDay ReadAggregatedGenerationPerTypeRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        AGPTRecordForSpecificDay rec = new AGPTRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ActualGenerationOutputValue":
                    rec.setActualGenerationOutputValue(reader.nextDouble());
                    break;
                case "DateTimeUTC":
                    rec.setDateTimeUTC(reader.nextString());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpdateTimeUTC(reader.nextString());
                    break;
                case "ProductionType":
                    rec.setProductionType(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static AGPTRecordForSpecificMonth ReadAggregatedGenerationPerTypeRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        AGPTRecordForSpecificMonth rec = new AGPTRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ActualGenerationOutputByDayValue":
                    rec.setActualGenerationOutputByDayValue(reader.nextDouble());
                    break;
                case "ProductionType":
                    rec.setProductionType(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static AGPTRecordForSpecificYear ReadAggregatedGenerationPerTypeRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        AGPTRecordForSpecificYear rec = new AGPTRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "ActualGenerationOutputByMonthValue":
                    rec.setActualGenarationOutputByMonthValue(reader.nextDouble());
                    break;
                case "ProductionType":
                    rec.setProductionType(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static AVSFRecordForSpecificDay ReadAVSFRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        AVSFRecordForSpecificDay rec = new AVSFRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ActualTotalLoadValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;
                case "DateTimeUTC":
                    rec.setDateTimeUTC(reader.nextString());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpdateTimeUTC(reader.nextString());
                    break;
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static AVSFRecordForSpecificMonth ReadAVSFRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        AVSFRecordForSpecificMonth rec = new AVSFRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ActualTotalLoadByDayValue":
                    rec.setActualTotalLoadByDayValue(reader.nextDouble());
                    break;
                case "DayAheadTotalLoadForecastByDayValue":
                    rec.setDayAheadTotalLoadForecastByDayValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static AVSFRecordForSpecificYear ReadAVSFRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        AVSFRecordForSpecificYear rec = new AVSFRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "ActualTotalLoadByMonthValue":
                    rec.setActualDataLoadByMonthValue(reader.nextDouble());
                    break;
                case "DayAheadTotalLoadForecastByMonthValue":
                    rec.setDayAheadTotalLoadForecastByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

}
