/* Code modified from example on https://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/
Accessed 4 April 2018. Last modified: 6 April 2018
Owner: Matthew Yuan */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class demandGen {

    public static void main(String[] args) {

        String csvFile = "./nw_ambulances.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        double rad = 6378137.0;/* in meters on the equator */
				int count = 0;
        int i;
        FileWriter writer = null;
				//Map<Integer,Double> points = new HashMap<>();
				TreeMap<Integer,Double> points = new TreeMap<Integer,Double>();
				Map<Double,Double> coor = new HashMap<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            writer = new FileWriter("./data.csv");
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
								int pop = Integer.valueOf(country[7]);
                double lat = Double.valueOf(country[8]);
                double lon = Double.valueOf(country[9]);
                //converting coordinate systems
                double nlat = Math.log(Math.tan(Math.PI / 4 + Math.toRadians(lat) / 2)) * rad;
                double nlon = Math.toRadians(lon) * rad;
								points.put(count,nlon);
								coor.put(nlon,nlat);
								count += pop;

            }
            //generating 120 random points from pop to data
            for(i = 0; i < 120; i++) {
              int num = (int) (Math.random() * count);
  						int selected = points.floorKey(num);
  						double slon = points.get(selected);
  						double slat = coor.get(slon);
              writer.append(Double.toString(slon));
              writer.append(',');
              writer.append(Double.toString(slat));
              writer.append("\n");
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
