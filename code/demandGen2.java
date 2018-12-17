/* Code modified from example on https://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/
Accessed 4 April 2018. Last modified: 6 April 2018
Owner: Matthew Yuan */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class demandGen2 {

    public static void main(String[] args) {

      //people who work and live in New Windsor
        String csvFile = "./nw_internal.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        double rad = 6378137.0;/* in meters on the equator */
        int i;
        FileWriter writer = null;
				//Map<Integer,Double> points = new HashMap<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            writer = new FileWriter("./data2.csv");//currently working
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
								int pop = Integer.valueOf(country[2]);
                double lat = Double.valueOf(country[3]);
                double lon = Double.valueOf(country[4]);
                double lat2 = Double.valueOf(country[5]);
                double lon2 = Double.valueOf(country[6]);
                //converting coordinate systems
                double nlat = Math.log(Math.tan(Math.PI / 4 + Math.toRadians(lat) / 2)) * rad;
                double nlon = Math.toRadians(lon) * rad;
                double nlat2 = Math.log(Math.tan(Math.PI / 4 + Math.toRadians(lat2) / 2)) * rad;
                double nlon2 = Math.toRadians(lon2) * rad;
								for(i=0; i<pop; i++) {
                  writer.append(Double.toString(nlon));
                  writer.append(',');
                  writer.append(Double.toString(nlat));
                  writer.append(',');
                  writer.append(Double.toString(nlon2));
                  writer.append(',');
                  writer.append(Double.toString(nlat2));
                  writer.append("\n");
                }

            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    writer.flush();
                    writer.close();
                    System.out.println("File saved!");
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
