# all-samples-hub
will have all my practice samples separated in modules

Updates fron chewbacca team for SSEUS Decomm project

1. We have decided which repository will have code for SSE US project -
	a. Trade flow will go in legacy Trade-Flow repository.
	b. Settlement flow will go in existing cage clenup repository.
2. Migrataed existing cage clenup component to Java 1.8 and Spring 4.
3. Existing Cage clean up component is using Dynacanrun library and it has quartz 1.8 as dependency.
    Since quartz 1.8 is not supported by Spring 4, We had to update quartz libary in DynaCanRun from 1.8 to 2.2.  
4. In existing flow Cage is fetching data from BPSA DB. We have analysed that it will not require for new flow to go for
   BPSA DB, as all require data will be available in Rhino DB for Trade.
5. Mohammed is working to complete fitness test for settlemt flow.

