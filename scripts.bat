:: An exercise in completely automating the MATSim run process, from demand generation 
:: to output.
:: Sources: https://superuser.com/questions/1217905/how-can-i-write-windows-batch-file-command-with-loop-and-operator
:: loop format: start, step, end
::javac demandGen.java
javac WriteXMLFile.java
FOR /L %%A IN (1,1,1) DO (
::java demandGen
  java WriteXMLFile
  java -Xmx4g -cp ..\..\matsim-0.10.1.jar org.matsim.run.Controler config.xml
  rename output output-%%A
)
