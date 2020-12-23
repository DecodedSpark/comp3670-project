# comp3670-project
This ReadMe file has 2 parts:
1) Contributions, Notes & Bugs. This is also written in the file "ReadMe.txt".
2) Deployment & Testing Instructions. This is also written in the file "Instructions.txt".

====== CONTRIBUTIONS, NOTES & BUGS ======

==== Contributions ====

For our final project, we went with Option 1: extending the
network application we coded in Assignments 2 and 3.

Option 1 consisted of 4 parts:

Task 1 >>>> Traceroute between one or more job seekers and any other node in the network
            and find the nearest job seeker(s) to the target node.

Task 2 >>>> Spy on your neighbours. the JobCreator could direct the JobSeekers to report the IP address
           and MAC address for every live host who shares the same LAN with the JobSeeker. The JobCreator
           should detect the JobSeekers that share the same LAN if any.

Task 3 >>>> Create a simple user-interface or config files to enable testing your project (including all the
            parts from the assignments and the new extension).

Task 4 (Bonus 5%) >>>> If the job fails (e.g. the job seeker or creator crash), you should be able to resume
                       from the crash point and not start/restart the entire job.

We distributed the workload as follows:

Leanna Lariviere >>>> Task 1
Dariq Ahmed      >>>> Task 2
Curtis Deslippe  >>>> Task 3
Yuvi Nanuan      >>>> Task 4

I (Dariq Ahmed) also created and ran the test cases, and wrote down the instructions for deployment and testing
with the help of Curtis and Leanna.

==== Notes ====

1) Task 1 of the final project is Job 5 of the application.

2) Task 2 of the final project is Job 3 of the application.

3) Task 3 of the final project is a while counter and variable used in FPJobCreator.java.
   It is also present in each of the individual test case files.

4) Task 4 of the final project is UserInterface.java.

==== Bugs ====

There were a few bugs when running the program.

1) Despite following the instructions given when we researched the packages,
   my terminal was unable to recognize the existence of either Pcap4J or ShortPasta packages,
   even after install. Hours of debugging failed to solve the problem. But if you can get
   those packages properly installed on your device in Intellij Idea, the code for Job 4 (TCP flood attack from A3),
   Job 5 (Final Project Task 1) and Job 6 (ICMP flood attack from A3) should work properly.

2) For some reason, even though testing SpyNeighbors.java directly proves that the code for Task 2 works
   correctly, FPJobSeeker.java fails to call on SpyNeighbors.java when the job is assigned. FPJobSeeker
   instead appears to enter an infinite loop. Hours of debugging failed to solve the issue.
   
3) The bonus code for Task 4 does not seem to work properly. It does not make the application continue from where
   it left off in the event of a crash.


====== DEPLOYMENT & TESTING INSTRUCTIONS ======

==== Deployment Instructions ====
Before running the application, you must install two packages:
                Pcap4J (org.pcap4j.packet) and
                ShortPasta (org.shortpasta.icmp2)

== How To Add Pcap4J Package ==

1) From the main menu, select "File" > "Project Structure" and click "Libraries".
2) Click "+" button to add, and select "From Maven".
3) Click the magnifying glass and search "pcap4j".
4) Find "org.pcap4j:pcap4j-core:1.7.5" and install.
5) Under the "Dependencies" tab, check the checkbox for this new package to be allowed.
6) Click OK.
7) In the pom.xml file, enter these dependencies:
            <dependency>
              <groupId>org.pcap4j</groupId>
              <artifactId>pcap4j-core</artifactId>
              <version>[1.0, 2.0)</version>
            </dependency>
            <dependency>
              <groupId>org.pcap4j</groupId>
              <artifactId>pcap4j-packetfactory-static</artifactId>
              <version>[1.0, 2.0)</version>
            </dependency>
8) You are now ready to use Pcap4J.

== How To Add ShortPasta Package ==

1) From the main menu, select "File" > "Project Structure" and click "Modules".
2) Open "Dependencies" tab.
3) Click on "+" to add, and select "JARs or Directories".
4) Locate your project directory, enter its path followed by "/external/shortpasta-icmp2.jar" and click OK.
        (Or just go to the folder "external" and the file is there.)
5) Check the checkbox for this new package to be allowed.
6) Click OK.
7) You can now use ShortPasta.

==== Testing Instructions ====

1) Except for SpyNeighbors.java, all jobs can be tested directly by running UserInterface.java and
   following the instructions.
2) SpyNeighbors.java, the code behind Job 3, can be tested by compiling and running directly in the terminal.
3) Each of the 6 jobs have their own specific test case Java files.
4) Each test case saves its output to its own separate TXT file.
5) UserInterface.java also saves its output to a file called outputInterface.txt.
6) FPJobCreator.java saves its output to a file called output.txt.
7) SpyNeighbors.java saves its output to a file called spyOutput.txt.
8) The screenshots of the test case runs are all contained in the folder called "Test Runs".


© 2020 GitHub, Inc.
