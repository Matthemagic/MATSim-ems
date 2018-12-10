/*Code copied from www.mkyong.com, 26 Feb 2018

and from www.java2s.com/Code/Java/XML, 17 Apr 2018
Modified by Matthew Yuan, 17 Apr 2018
*/


import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

	public static void main(String argv[]) {

		BufferedReader points,pointstwo,pointsthree,pointsfour = null;
		String line = "";
	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		//ambulance data
		points = new BufferedReader(new FileReader("./data.csv"));
		//internal data
		pointstwo = new BufferedReader(new FileReader("./data2.csv"));
		//workers data
		pointsthree = new BufferedReader(new FileReader("./data3.csv"));
		//residents data
		pointsfour = new BufferedReader(new FileReader("./data4.csv"));
		// root elements
		Document doc = docBuilder.newDocument();
		//add doctype to doc
		Element rootElement = doc.createElement("plans");
		doc.appendChild(rootElement);

		int i,count;

  //ambulance elements
	//120 ambulances distributed evenly throughout the workday
	//5 leaving each hour, on the hour
	for(i = 1; i <= 120; i++) {
		// person elements
		Element person = doc.createElement("person");

		// set attribute to person element
		person.setAttribute("id", Integer.toString(i));
		rootElement.appendChild(person);

		Element plan = doc.createElement("plan");
		person.appendChild(plan);
		// activity1 elements
		Element act1 = doc.createElement("act");
		act1.setAttribute("type","home");
		act1.setAttribute("x","-8244645");
		act1.setAttribute("y","5083720");
	  if(i<5) act1.setAttribute("end_time","07:00:00");
		else if(i<10) act1.setAttribute("end_time","08:00:00");
		else if(i<15) act1.setAttribute("end_time","09:00:00");
		else if(i<20) act1.setAttribute("end_time","10:00:00");
		else if(i<25) act1.setAttribute("end_time","11:00:00");
		else if(i<30) act1.setAttribute("end_time","12:00:00");
		else if(i<35) act1.setAttribute("end_time","13:00:00");
		else if(i<40) act1.setAttribute("end_time","14:00:00");
		else if(i<45) act1.setAttribute("end_time","15:00:00");
		else if(i<50) act1.setAttribute("end_time","16:00:00");
		else if(i<55) act1.setAttribute("end_time","17:00:00");
		else if(i<60) act1.setAttribute("end_time","18:00:00");
		else if(i<65) act1.setAttribute("end_time","19:00:00");
		else if(i<70) act1.setAttribute("end_time","20:00:00");
		else if(i<75) act1.setAttribute("end_time","21:00:00");
		else if(i<80) act1.setAttribute("end_time","22:00:00");
		else if(i<85) act1.setAttribute("end_time","23:00:00");
		else if(i<90) act1.setAttribute("end_time","00:00:00");
		else if(i<95) act1.setAttribute("end_time","01:00:00");
		else if(i<100) act1.setAttribute("end_time","02:00:00");
		else if(i<105) act1.setAttribute("end_time","03:00:00");
		else if(i<110) act1.setAttribute("end_time","04:00:00");
		else if(i<115) act1.setAttribute("end_time","05:00:00");
		else act1.setAttribute("end_time","06:00:00");
		plan.appendChild(act1);

		// travel elements
		Element leg = doc.createElement("leg");
		leg.setAttribute("mode","ambulance");
		plan.appendChild(leg);

		//readfile
		line = points.readLine();
		String[] point = line.split(",");
		//System.out.println(point[0]);
		// act2 elements
		Element act2 = doc.createElement("act");
		act2.setAttribute("type","work");
		act2.setAttribute("x",point[0]);
		act2.setAttribute("y",point[1]);
		act2.setAttribute("dur","00:10:00");
		//assumed 10 min of process time
		plan.appendChild(act2);

		Element leg2 = doc.createElement("leg");
		leg2.setAttribute("mode","ambulance");
		plan.appendChild(leg2);

		// ending elements
		Element act3 = doc.createElement("act");
		// if(Math.random()<0.1) {
		// 	act3.setAttribute("type","home");
		// 	act3.setAttribute("x","-8244645");
		// 	act3.setAttribute("y","5083720");
		// 	//home ambulance station
		// 	plan.appendChild(act3);
		// }
		// else {
			act3.setAttribute("type","shop");
			act3.setAttribute("x","-8239250");
			act3.setAttribute("y","5086920");
			act3.setAttribute("dur","00:15:00");
			//assumed 15 min of hospital time
			//hospital
			plan.appendChild(act3);
			Element leg3 = doc.createElement("leg");
			leg3.setAttribute("mode","ambulance");
			plan.appendChild(leg3);
			Element act4 = doc.createElement("act");
			act4.setAttribute("type","home");
			act4.setAttribute("x","-8244645");
			act4.setAttribute("y","5083720");
			//home ambulance station
			plan.appendChild(act4);
		// }
	}
	count=0;
	//background elements
	//work and live in New Windsor
	//1327 total
	//select on avg. 214 people, to match actual census stats
	for(i = 121; i < 1447; i++) {
		// person elements
		if(Math.random()>0.1613) {//=214/1327
			//System.out.println("dead");
			continue;
		}
		count++;
		//System.out.println("alive");
		Element person = doc.createElement("person");

		// set attribute to person element
		person.setAttribute("id", Integer.toString(i));
		rootElement.appendChild(person);

		Element plan = doc.createElement("plan");
		person.appendChild(plan);

		//readfile
		line = pointstwo.readLine();
		String[] point = line.split(",");
		// activity1 elements
		Element act1 = doc.createElement("act");
		act1.setAttribute("type","home");
		act1.setAttribute("x",point[0]);
		act1.setAttribute("y",point[1]);
		act1.setAttribute("end_time","09:00:00");
		plan.appendChild(act1);

		// travel elements
		Element leg = doc.createElement("leg");
		leg.setAttribute("mode","car");
		plan.appendChild(leg);


		//System.out.println(point[0]);
		// act2 elements
		Element act2 = doc.createElement("act");
		act2.setAttribute("type","work");
		act2.setAttribute("x",point[2]);
		act2.setAttribute("y",point[3]);
		act2.setAttribute("dur","08:00:00");
		//assumed 8 hour workday
		plan.appendChild(act2);

		Element leg3 = doc.createElement("leg");
		leg3.setAttribute("mode","car");
		plan.appendChild(leg3);
		Element act4 = doc.createElement("act");
		act4.setAttribute("type","home");
		act4.setAttribute("x",point[0]);
		act4.setAttribute("y",point[1]);
		//home
		plan.appendChild(act4);
		// }
	}
	System.out.println(count);
	//people who live elsewhere, work in New Windsor
	//total of 10428 workers
	//select 1871 for realism according to census
	for(i = 1448; i < 11876; i++) {
		// person elements
		if(Math.random()>0.1794) {//=1871/10428
			//System.out.println("dead");
			continue;
		}
		count++;
		//System.out.println("alive");
		Element person = doc.createElement("person");

		// set attribute to person element
		person.setAttribute("id", Integer.toString(i));
		rootElement.appendChild(person);

		Element plan = doc.createElement("plan");
		person.appendChild(plan);

		//readfile
		line = pointsthree.readLine();
		String[] point = line.split(",");
		// activity1 elements
		Element act1 = doc.createElement("act");
		act1.setAttribute("type","home");
		act1.setAttribute("x",point[0]);
		act1.setAttribute("y",point[1]);
		act1.setAttribute("end_time","09:00:00");
		plan.appendChild(act1);

		// travel elements
		Element leg = doc.createElement("leg");
		leg.setAttribute("mode","car");
		plan.appendChild(leg);


		//System.out.println(point[0]);
		// act2 elements
		Element act2 = doc.createElement("act");
		act2.setAttribute("type","work");
		act2.setAttribute("x",point[2]);
		act2.setAttribute("y",point[3]);
		act2.setAttribute("dur","08:00:00");
		//assumed 8 hour workday
		plan.appendChild(act2);

		Element leg3 = doc.createElement("leg");
		leg3.setAttribute("mode","car");
		plan.appendChild(leg3);
		Element act4 = doc.createElement("act");
		act4.setAttribute("type","home");
		act4.setAttribute("x",point[0]);
		act4.setAttribute("y",point[1]);
		//home
		plan.appendChild(act4);
		// }
	}
	System.out.println(count);
	//people who live in New Windsor, work elsewhere
	//select 3843 of 9521 according to census data
	for(i = 11877; i < 21398; i++) {
		// person elements
		if(Math.random()>0.4036) {//=3843/9521
			//System.out.println("dead");
			continue;
		}
		count++;
		//System.out.println("alive");
		Element person = doc.createElement("person");

		// set attribute to person element
		person.setAttribute("id", Integer.toString(i));
		rootElement.appendChild(person);

		Element plan = doc.createElement("plan");
		person.appendChild(plan);

		//readfile
		line = pointsfour.readLine();
		String[] point = line.split(",");
		// activity1 elements
		Element act1 = doc.createElement("act");
		act1.setAttribute("type","home");
		act1.setAttribute("x",point[0]);
		act1.setAttribute("y",point[1]);
		act1.setAttribute("end_time","09:00:00");
		plan.appendChild(act1);

		// travel elements
		Element leg = doc.createElement("leg");
		leg.setAttribute("mode","car");
		plan.appendChild(leg);


		//System.out.println(point[0]);
		// act2 elements
		Element act2 = doc.createElement("act");
		act2.setAttribute("type","work");
		act2.setAttribute("x",point[2]);
		act2.setAttribute("y",point[3]);
		act2.setAttribute("dur","08:00:00");
		//assumed 8 hour workday
		plan.appendChild(act2);

		Element leg3 = doc.createElement("leg");
		leg3.setAttribute("mode","car");
		plan.appendChild(leg3);
		Element act4 = doc.createElement("act");
		act4.setAttribute("type","home");
		act4.setAttribute("x",point[0]);
		act4.setAttribute("y",point[1]);
		//home
		plan.appendChild(act4);
		// }
	}
	System.out.println(count);
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc, "http://www.matsim.org/files/dtd/plans_v4.dtd");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://www.matsim.org/files/dtd/plans_v4.dtd");
		StreamResult result = new StreamResult(new File("./plans.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");
	  } catch (FileNotFoundException e) {
			e.printStackTrace();
	  } catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}
