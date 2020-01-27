package org.onebusaway.onebusaway_stif_transformer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StifBoroughCategorizor {

    private static String[] boroughByRoutesPaths = new String[]{"routes_manhattan.txt"};
    private ArrayList<Set> boroughsByRoutes;

    public void initialize() {
        for (String path : boroughByRoutesPaths) {
            HashSet<String> boroughByRoutes = new HashSet<String>();
            ClassLoader c = getClass().getClassLoader();
            URL g = getClass().getClassLoader().getResource(path);
            String n = getClass().getClassLoader().getResource(path).getFile();
            File file = new File(getClass().getClassLoader().getResource(path).getFile());
            String tmp = file.getAbsolutePath();
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine())
                    boroughByRoutes.add(sc.nextLine());
            } catch (FileNotFoundException exception) {
                System.out.print("Cannot find resource: boroughsAndRoutes.txt: " + path + ". This will create errors in categorizing by borough");
            }
        }
    }

    public String categorizeByBorough(StifSupport stifSupport){

        return "";
    }
    public String categorizeByHolidayStatus(StifSupport stifSupport){

        return "";
    }
}
