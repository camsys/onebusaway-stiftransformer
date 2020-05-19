package org.onebusaway.onebusaway_stif_transformer_impl;

import org.onebusaway.onebusaway_stif_transformer_impl.model.TimetableRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
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
        _log.info("Processing logs for borough and holiday analysis");
        for (Map.Entry<String,TimetableRecord> entry : timetableRecordsForFileId.entrySet()){
            TimetableRecord record = entry.getValue();
            _log.debug("processing record:");
            _log.debug(record.toString());
            _log.debug("Route: " + record.getBoroughCode() + record.getRouteIdentifier());
            _log.debug("Holiday Code: " + record.getHolidayCode());
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
        else if (countOfHolidayRecords > 0){
            isHoliday = true;
        }
        else if (countOfNonHolidayRecords > 0){
            isHoliday = false;
        }
    }

    public String categorizeByBorough(){
        Map<String, Integer> routesForBorough = new HashMap<String, Integer>();

        for (String route : stifRoutes){
            boolean matched = false;
            for(Map.Entry<String,Set> entry: routesInBoroughs.entrySet()) {
                if (entry.getValue().contains(route)){
                    matched = true;
                    int matchesInBorough = routesForBorough.getOrDefault(entry.getKey(),0);
                    matchesInBorough++;
                    routesForBorough.put(entry.getKey(),matchesInBorough);
                }
            }
            if (matched == false){
                _log.error("Detected unrecognized route! Please update the appropriate [borough]_route.txt: " + route);
            }
        }
        String out = "";
        int highest = 0;
        for(Map.Entry<String,Integer> entry: routesForBorough.entrySet()){
            if (entry.getValue()>highest){
                if (highest > 0){
                    _log.error("Resource files [borough]_route.txt indicate both " + entry.getKey()
                            + " and " + out + " contain routes from " + stifPathInfo
                            + " dataset. Please update the [borough]_route.txt resource files immediately");
                }
                highest = entry.getValue();
                out = entry.getKey();
            }
        }
        return "stifs_" + out.split("_")[0];
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
