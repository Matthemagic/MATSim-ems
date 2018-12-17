/* Code modified from example on https://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/
Accessed 4 April 2018. Last modified: 6 April 2018
Owner: Matthew Yuan */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class demandGen3 {

    public static void main(String[] args) {

      //people who live elsewhere, work in New Windsor
        String csvFile = "./nw_workers.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        double rad = 6378137.0;/* in meters on the equator */
        int i;
        FileWriter writer = null;
				//Map<Integer,Double> points = new HashMap<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            writer = new FileWriter("./data3.csv");
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
								int pop = Integer.valueOf(country[2]);
                double lat = Double.valueOf(country[3]);
                double lon = Double.valueOf(country[4]);
                //converting coordinate systems
                double nlat = Math.log(Math.tan(Math.PI / 4 + Math.toRadians(lat) / 2)) * rad;
                double nlon = Math.toRadians(lon) * rad;
								for(i=0; i<pop; i++) {
                  writer.append(Double.toString(nlon));
                  writer.append(',');
                  writer.append(Double.toString(nlat));
                  writer.append(',');
                  if(Math.random()<0.6) {
                    writer.append(Double.toString(-8240795.432));
                    writer.append(',');
                    writer.append(Double.toString(5079402.776));
                    writer.append("\n");
                  }
                  else if(Math.random()<0.75) {
                    writer.append(Double.toString(-8239852.3));
                    writer.append(',');
                    writer.append(Double.toString(5089010.863));
                    writer.append("\n");
                  }
                  else if(Math.random()<0.825) {
                    writer.append(Double.toString(-8245281.619));
                    writer.append(',');
                    writer.append(Double.toString(5089039.17));
                    writer.append("\n");
                  }
                  else if(Math.random()<0.875) {
                    writer.append(Double.toString(-8243678.818));
                    writer.append(',');
                    writer.append(Double.toString(5076471.643));
                    writer.append("\n");
                  }
                  else if(Math.random()<0.95) {
                    writer.append(Double.toString(-8252599.528));
                    writer.append(',');
                    writer.append(Double.toString(5090261.305));
                    writer.append("\n");
                  }
                  else {
                    writer.append(Double.toString(-8260876.6));
                    writer.append(',');
                    writer.append(Double.toString(5079962.837));
                    writer.append("\n");
                  }
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
