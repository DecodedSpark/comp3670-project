==== CONTRIBUTIONS ====

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

==== NOTES ====

1) Task 1 of the final project is Job 5 of the application.

2) Task 2 of the final project is Job 3 of the application.

3) Task 3 of the final project is a while counter and variable used in FPJobCreator.java.
   It is also present in each of the individual test case files.

4) Task 4 of the final project is UserInterface.java.

==== BUGS ====

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
