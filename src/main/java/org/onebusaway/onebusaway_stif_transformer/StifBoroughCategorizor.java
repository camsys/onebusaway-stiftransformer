package org.onebusaway.onebusaway_stif_transformer;

import org.onebusaway.onebusaway_stif_transformer.model.TimetableRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class StifBoroughCategorizor {

    private static String[] routesByBoroughPaths = new String[]{"staten"};
    private ArrayList<Set> routesInBoroughs;
    Collection<String> stifRoutes = new HashSet<String>();
    boolean isHoliday;

    public void initialize(StifSupport stifSupport) {
        for (String path : routesByBoroughPaths) {
            HashSet<String> routesInBorough = new HashSet<String>();
            ClassLoader c = getClass().getClassLoader();
            URL g = getClass().getClassLoader().getResource(path);
            String n = getClass().getClassLoader().getResource(path).getFile();
            File file = new File(getClass().getClassLoader().getResource(path).getFile());
            String tmp = file.getAbsolutePath();
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine())
                    routesInBorough.add(sc.nextLine());
            } catch (FileNotFoundException exception) {
                System.out.print("Cannot find resource: boroughsAndRoutes.txt: " + path + ". This will create errors in categorizing by borough");
            }
            routesInBoroughs.add(routesInBorough);
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
        borough: for(Set routesInBorough: routesInBoroughs){
            for (String route : stifRoutes){
                if (!routesInBorough.contains(route)){
                    break borough;
                }
            }
        }
        return "";
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
