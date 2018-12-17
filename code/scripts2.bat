::script in extracting important data from different iters events.xml
::and consolidating in one folder /output-plans
FOR /L %%A IN (1,1,20) DO (
  7za.exe e output-%%A\ITERS\it.10\10.events.xml.gz -ooutput-plans
  ren output-plans\10.events.xml events-%%A.xml
)