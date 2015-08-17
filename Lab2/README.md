# Lab 2

### Prerequisites
* Completion of Lab1

### Agenda
In this Lab, you will learn the following:
* Use gfsh to start a Locator
* Start GemFire Pulse -- a browerser-based monitoring tool for GemFire
* Start a GemFire server
* Create a replicated, persistent region
* Manipulate data in the region and demonstrate persistence
* Examine the effects of replication
* Restart the cache servers in parallel
* Shut down the system including your locators

##### Use gfsh to start a Locator.
In a terminal window, use the gfsh command line interface to start up a locator. Pivotal GemFire gfsh (pronounced "gee-fish") provides a single, intuitive command-line interface from which you can launch, manage, and monitor Pivotal GemFire processes, data, and applications.

The locator is a Pivotal GemFire process that tells new, connecting members where running members are located and provides load balancing for server use. A locator, by default, starts up a JMX Manager, which is used for monitoring and managing of a GemFire cluster. The cluster configuration service uses locators to persist and distribute cluster configurations to cluster members.

Create a scratch working directory (for example, my_gemfire) and change directories into it. gfsh saves locator and server working directories and log files in this location.
Start gfsh by typing gfsh at the command line (or gfsh.bat if you are using Windows).
```
    _________________________     __
   / _____/ ______/ ______/ /____/ /
  / /  __/ /___  /_____  / _____  /
 / /__/ / ____/  _____/ / /    / /
/______/_/      /______/_/    /_/    v8.1.0

Monitor and Manage GemFire
gfsh>
```
At the gfsh prompt, type:
```
gfsh>start locator --name=locator1
Starting a GemFire Locator in /home/username/my_gemfire/locator1...
.................................
Locator in /home/username/my_gemfire/locator1 on ubuntu.local[10334] as locator1 is currently online.
Process ID: 3529
Uptime: 18 seconds
GemFire Version: 8.1.0
Java Version: 1.7.0_72
Log File: /home/username/my_gemfire/locator1/locator1.log
JVM Arguments: -Dgemfire.enable-cluster-configuration=true -Dgemfire.load-cluster-configuration-from-dir=false 
-Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true 
-Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /home/username/Pivotal_GemFire_810_b50480_Linux/lib/gemfire.jar:
/home/username/Pivotal_GemFire_810_b50480_Linux/lib/locator-dependencies.jar

Successfully connected to: [host=ubuntu.local, port=1099]
```
Cluster configuration service is up and running.

##### Start GemFire Pulse.
Start up the browser-based GemFire Pulse monitoring tool. GemFire Pulse is a Web Application that provides a graphical dashboard for monitoring vital, real-time health and performance of GemFire clusters, members, and regions.
```
gfsh>start pulse
Launched GemFire Pulse
```
This command launches Pulse and automatically connects you to the JMX Manager running in the Locator. At the Pulse login screen, type in the default username admin and password admin.
The Pulse application now displays the locator you just started (locator1):


##### Start a server.
A GemFire server is a Pivotal GemFire process that runs as a long-lived, configurable member of a cluster (also called a distributed system). The GemFire server is used primarily for hosting long-lived data regions and for running standard GemFire processes such as the server in a client/server configuration.

Start the cache server:
```
gfsh>start server --name=server1 --server-port=40411
```
This commands starts a cache server named "server1" on the specified port of 40411.

Observe the changes (new member and server) in Pulse. Try expanding the distributed system icon to see the locator and cache server graphically.

##### Create a replicated, persistent region.
In this step you create a region with the gfsh command line utility. Regions are the core building blocks of the GemFire cluster and provide the means for organizing your data. The region you create for this exercise employs replication to replicate data across members of the cluster and utilizes persistence to save the data to disk. See Data Regions.

Create a replicated, persistent region:
```
gfsh>create region --name=regionA --type=REPLICATE_PERSISTENT
Member  | Status
------- | --------------------------------------
server1 | Region "/regionA" created on "server1"
```

Note that the region is hosted on server1.
Use the gfsh command line to view a list of the regions in the cluster.
```
gfsh>list regions
List of regions
---------------
regionA
```
List the members of your cluster. The locator and cache servers you started appear in the list:
```
gfsh>list members
  Name   | Id
-------- | ---------------------------------------
locator1 | ubuntu(locator1:3529:locator)<v0>:59926
server1  | ubuntu(server1:3883)<v1>:65390
```
To view specifics about a region, type the following:
```
gfsh>describe region --name=regionA
..........................................................
Name            : regionA
Data Policy     : persistent replicate
Hosting Members : server1
                  
Non-Default Attributes Shared By Hosting Members  

 Type  | Name | Value
------ | ---- | -----
Region | size | 0
```
In Pulse, click the green cluster icon to see all the new members and new regions that you just added to your cluster.
Note: Keep this gfsh prompt open for the next steps.
##### Manipulate data in the region and demonstrate persistence.
Pivotal GemFire manages data as key/value pairs. In most applications, a Java program adds, deletes and modifies stored data. You can also use gfsh commands to add and retrieve data.

Run the following put commands to add some data to the region:
```
gfsh>put --region=regionA --key="1" --value="one"
Result      : true
Key Class   : java.lang.String
Key         : 1
Value Class : java.lang.String
Old Value   : <NULL>
```
```
gfsh>put --region=regionA --key="2" --value="two"
Result      : true
Key Class   : java.lang.String
Key         : 2
Value Class : java.lang.String
Old Value   : <NULL>
```

Run the following command to retrieve data from the region:
```
gfsh>query --query="select * from /regionA"

Result     : true
startCount : 0
endCount   : 20
Rows       : 2

Result
------
two
one
```
Note that the result displays the values for the two data entries you created with the put commands.
See Data Entries.
Stop the cache server using the following command:
```
gfsh>stop server --name=server1
Stopping Cache Server running in /home/username/my_gemfire/server1 on ubuntu.local[40411] as server1...
Process ID: 3883
Log File: /home/username/my_gemfire/server1/server1.log
....
```
Restart the cache server using the following command:
```
gfsh>start server --name=server1 --server-port=40411
Run the following command to retrieve data from the region again-- notice that the data is still available:
gfsh>query --query="select * from /regionA"

Result     : true
startCount : 0
endCount   : 20
Rows       : 2

Result
------
two
one
```

Because regionA uses persistence, it writes a copy of the data to disk. When a server hosting regionA starts, the data is populated into the cache. Note that the result displays the values for the two data entries you created prior to stopping the server with the put commands.

##### Examine the effects of replication.
In this step, you start a second cache server. Because regionA is replicated, the data will be available on any server hosting the region.

Start a second cache server:
```
gfsh>start server --name=server2 --server-port=40412
```
Run the describe region command to view information about regionA:
```
gfsh>describe region --name=regionA
..........................................................
Name            : regionA
Data Policy     : persistent replicate
Hosting Members : server1
                  server2

Non-Default Attributes Shared By Hosting Members  

 Type  | Name | Value
------ | ---- | -----
Region | size | 2
```
Note that you do not need to create regionA again for server2. The output of the command shows that regionA is hosted on both server1 and server2. When gfsh starts a server, it requests the configuration from the cluster configuration service which then distributes the shared configuration to any new servers joining the cluster.
Add a third data entry:
```
gfsh>put --region=regionA --key="3" --value="three"
Result      : true
Key Class   : java.lang.String
Key         : 3
Value Class : java.lang.String
Old Value   : <NULL>
```
Open the Pulse application (in a Web browser) and observe the cluster topology. You should see a locator with two attached servers. Click the Data tab to view information about regionA.
Stop the first cache server with the following command:
```
gfsh>stop server --name=server1
Stopping Cache Server running in /home/username/my_gemfire/server1 on ubuntu.local[40411] as server1...
Process ID: 4064
Log File: /home/username/my_gemfire/server1/server1.log
....
```
Retrieve data from the remaining cache server.
```
gfsh>query --query="select * from /regionA"

Result     : true
startCount : 0
endCount   : 20
Rows       : 3

Result
------
two
one
three
```

Note that the data contains 3 entries, including the entry you just added.
Add a fourth data entry:
```
gfsh>put --region=regionA --key="4" --value="four"
Result      : true
Key Class   : java.lang.String
Key         : 3
Value Class : java.lang.String
Old Value   : <NULL>
```
Note that only server2 is running. Because the data is replicated and persisted, all of the data is still available. But the new data entry is currently only available on server 2.
```
gfsh>describe region --name=regionA
..........................................................
Name            : regionA
Data Policy     : persistent replicate
Hosting Members : server2

Non-Default Attributes Shared By Hosting Members  

 Type  | Name | Value
------ | ---- | -----
Region | size | 4
```
Stop the remaining cache server:
```
gfsh>stop server --name=server2
Stopping Cache Server running in /home/username/my_gemfire/server2 on ubuntu.local[40412] as server2...
Process ID: 4185
Log File: /home/username/my_gemfire/server2/server2.log
.....
```
##### Restart the cache servers in parallel.
In this step you restart the cache servers in parallel. Because the data is persisted, the data is available when the servers restart. Because the data is replicated, you must start the servers in parallel so that they can synchronize their data before starting.

Start server1. Because regionA is replicated and persistent, it needs data from the other server to start and waits for the server to start:
```
gfsh>start server --name=server1 --server-port=40411
Starting a GemFire Server in /home/username/my_gemfire/server1...
............................................................................
............................................................................
```
Note that if you look in the server1.log file for the restarted server, you will see a log message similar to the following:
```
[info 2015/01/14 09:08:13.610 PST server1 <main> tid=0x1] Region /regionA has pot
entially stale data. It is waiting for another member to recover the latest data.
  My persistent id:
  
    DiskStore ID: 8e2d99a9-4725-47e6-800d-28a26e1d59b1
    Name: server1
    Location: /192.168.129.145:/home/username/my_gemfire/server1/.
  
  Members with potentially new data:
  [
    DiskStore ID: 2e91b003-8954-43f9-8ba9-3c5b0cdd4dfa
    Name: server2
    Location: /192.168.129.145:/home/username/my_gemfire/server2/.
  ]
  Use the "gemfire list-missing-disk-stores" command to see all disk stores that 
are being waited on by other members.
```
In a second terminal window, change directories to the scratch working directory (for example, my_gemfire) and start gfsh:
```
[username@localhost ~/my_gemfire]$ gfsh
    _________________________     __
   / _____/ ______/ ______/ /____/ /
  / /  __/ /___  /_____  / _____  / 
 / /__/ / ____/  _____/ / /    / /  
/______/_/      /______/_/    /_/    v8.1.0

Monitor and Manage GemFire
```
Run the following command to connect to the cluster:
```
gfsh>connect --locator=localhost[10334]
Connecting to Locator at [host=localhost, port=10334] ..
Connecting to Manager at [host=ubuntu.local, port=1099] ..
Successfully connected to: [host=ubuntu.local, port=1099]
```
Start server2:
```
gfsh>start server --name=server2 --server-port=40412
```
When server2 starts, note that server1 completes its start up in the first gfsh window:
Server in /home/username/my_gemfire/server1 on ubuntu.local[40411] as server1 is currently online.
Process ID: 3402
Uptime: 1 minute 46 seconds
GemFire Version: 8.1.0
Java Version: 1.7.0_72
Log File: /home/username/my_gemfire/server1/server1.log
JVM Arguments: -Dgemfire.default.locators=192.168.129.145[10334] -Dgemfire.use-cluster-configuration=true 
-XX:OnOutOfMemoryError=kill -KILL %p -Dgemfire.launcher.registerSignalHandlers=true 
-Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /home/username/Pivotal_GemFire_810_b50582_Linux/lib/gemfire.jar:
/home/username/Pivotal_GemFire_810_b50582_Linux/lib/server-dependencies.jar
Verify that the locator and two servers are running:
```
gfsh>list members
  Name   | Id
-------- | ---------------------------------------
server2  | ubuntu(server2:3992)<v8>:21507
server1  | ubuntu(server1:3402)<v7>:36532
locator1 | ubuntu(locator1:2813:locator)<v0>:46644
```
Run a query to verify that all the data you entered with the put commands is available:
```
gfsh>query --query="select * from /regionA"

Result     : true
startCount : 0
endCount   : 20
Rows       : 5

Result
------
one
two
four
Three
```
Stop server2 with the following command:
```
gfsh>stop server --dir=server2
Stopping Cache Server running in /home/username/gf80/jul11/test11/server2 on 172.16.196.144[40412] as server2...
Process ID: 12404
Log File: /home/username/gf80/jul11/test11/server2/server2.log
....
```
Run a query to verify that all the data you entered with the put commands is still available:
```
gfsh>query --query="select * from /regionA"

Result     : true
startCount : 0
endCount   : 20
Rows       : 5

Result
------
one
two
four
Three
```
##### Shut down the system including your locators.
To shut down your cluster, do the following:

In the current gfsh session, stop the cluster:
```
gfsh>shutdown --include-locators=true
```
When prompted, type 'Y' to confirm the shutdown of the cluster.
As a lot of data in memory will be lost, including possibly events in queues, 
do you really want to shutdown the entire distributed system? (Y/n): Y
Shutdown is triggered
```
gfsh>
No longer connected to ubuntu.local[1099].
gfsh>
```
Type exit to quit the gfsh shell.

