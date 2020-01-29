package org.onebusaway.onebusaway_stif_transformer;

import org.onebusaway.onebusaway_stif_transformer.model.TimetableRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;

public class StifBoroughCategorizor {

    private static Logger _log = LoggerFactory.getLogger(StifBoroughCategorizor.class);

    private static String[] routesByBoroughPaths = new String[]{"mtabc_routes.txt","staten-island_routes.txt","brooklyn_routes.txt","bronx_routes.txt","manhattan_routes.txt","queens_routes.txt"};
    private Map<String,Set> routesInBoroughs =  new HashMap<String,Set>();
    Collection<String> stifRoutes = new HashSet<String>();
    String stifPathInfo;
    boolean isHoliday;

    public void initialize(StifSupport stifSupport) {
        Iterator<File> it = stifSupport.getStifFilePathsParents().iterator();
        if (it.hasNext()){
            stifPathInfo = it.next().getAbsolutePath();
        }
        for (String path : routesByBoroughPaths) {
            HashSet<String> routesInBorough = new HashSet<String>();

            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        routesInBorough.add(line);
                    }

                    reader.close();
                }
                catch(IOException exception) {
                    _log.error("Cannot find resource" + path, exception);

        }
            routesInBoroughs.put(path,routesInBorough);
        }

        int countOfHolidayRecords = 0;
        int countOfNonHolidayRecords = 0;
        Map<String, TimetableRecord> timetableRecordsForFileId = stifSupport.getTimetableRecordForFileId();
        for (Map.Entry<String,TimetableRecord> entry : timetableRecordsForFileId.entrySet()){
            TimetableRecord record = entry.getValue();
            stifRoutes.add(record.getBoroughCode() + record.getRouteIdentifier());
            if(record.getHolidayCode().equals("")){
                countOfNonHolidayRecords++;
            }
            else{
                countOfHolidayRecords++;
            }
        }
        if (!(countOfHolidayRecords == 0 || countOfNonHolidayRecords==0)){
            throw new Error("Directory contains both holiday and nonholiday files!");
        }
        else if (countOfHolidayRecords > 1){
            isHoliday = true;
        }
        else if (countOfNonHolidayRecords > 1){
            isHoliday = false;
        }
    }

    public String categorizeByBorough(){
        borough: for(Map.Entry<String,Set> entry: routesInBoroughs.entrySet()){
            Set routesInBorough = entry.getValue();
            boolean isBorough = true;
            for (String route : stifRoutes){
                if (!routesInBorough.contains(route)){
                    isBorough = false;
                    break;
                }
            }
            if (isBorough)
               return "stifs_" + entry.getKey().split("_")[0];
        }
        throw new Error("This data contains uncatagorized routes! " + stifPathInfo);
    }
    public String categorizeByHolidayStatus(){
        if (isHoliday){
            return "holiday";
        }
        else{
            return "non_holiday";
        }
    }
}
