# Lab 1

This repository describes hands-on labs for Pivotal GemFire.

### Prerequisites
* Administrative privileges to your laptop
* A basic knowledge of Java and familiarity with an IDE.
* These lab setup and examples have been written on a Linux-based OS -- Ubuntu.
* Confirm that your system meets the hardware and software requirements described in Pivotal GemFire Supported Configurations.
* If you have not already done so, download and install a compatible JDK on the Ubuntu computer or VM.

### Lab 1
#### Procedure
1. From the Pivotal GemFire product page, locate Downloads.
2. Download the Debian package ``` pivotal-gemfire_8.1.0-1_all.deb ```.
3. From the Ubuntu computer on which you will install GemFire, log in as the root user (or as an unprivileged user who has sudo privileges) and start a terminal.
4. Note: If you are not logged in as the root user, you must use the ``` sudo ``` command to run the commands in the following steps.
5. In the terminal window, change directories to the directory where you downloaded the Debian package.
6. Execute the following command:
``` $ dpkg -i pivotal-gemfire_8.1.0-1_all.deb ```
7. If necessary, use sudo to run the preceding command if you are not logged in as the root user:
 ``` $ sudo dpkg -i pivotal-gemfire_8.1.0-1_all.deb ```
8. The dpkg command begins the install process, resolves dependencies, and displays the packages it will install. The GemFire software is installed into the ``` /opt/pivotal/gemfire/Pivotal_GemFire_NNN ``` directory where NNN corresponds to the version of Pivotal GemFire that you installed.
9. If necessary, the install process creates a gemfire non-interactive user in the pivotal group. This user owns the installed GemFire directories and files, including any disk store files that you create later.
10. Note: You cannot log in directly as the gemfire user because interactive login has been disabled. Rather, you must first log in as the root user or as a user with sudo privileges. You can then execute commands as the gemfire user by using ``` sudo -u gemfire command_name ```.
11. If you have not already done so, download and install a compatible JDK or JRE on the computer or VM.
12. Configure the ``` JAVA_HOME ``` environment variable. If you will be using the gfsh command-line utility or managing servers and locators with the ServerLauncher and LocatorLauncher APIs, then you must set ``` JAVA_HOME ``` to a JDK installation. For example: ``` JAVA_HOME=/usr/java/jdk1.7.0_72; export JAVA_HOME ```
13. Add GemFire scripts to your the PATH environment variable. For example: ``` PATH=$PATH:$JAVA_HOME/bin:/opt/pivotal/gemfire/Pivotal_GemFire_810/bin; export PATH ```. The following step only applies to environments where you are running GemFire processes or GemFire client applications outside of gfsh. The gfsh (as well as the deprecated cacheserver) script sets these environment variable for you. If you are running GemFire processes or applications outside of gfsh, then configure the following environment variables for GemFire.
14. Set the GEMFIRE environment variable to point to your GemFire installation top-level directory. (You should see bin, lib, dtd, and other directories under GEMFIRE.) The following variables definitions are examples; your installation path may vary depending on where you install GemFire and the version you are installing. ``` GEMFIRE=/opt/pivotal/Pivotal_GemFire_810; export GEMFIRE ```
15. Configure your ``` GF_JAVA ``` environment variables as shown in these examples. ``` GF_JAVA ``` must point to the java executable file under your ``` JAVA_HOME ```. (If you have not done so already, you should also set your ``` JAVA_HOME ``` variable to a supported Java installation.) ``` GF_JAVA=$JAVA_HOME/bin/java; export GF_JAVA  ```
Type gfsh version at the command line and verify that the output lists the version of Pivotal GemFire that you wished to install. For example: 
``` 
# gfsh
v8.1.0 
```
If you want more detailed version information such as the date of the build, build number and JDK version being used, type ``` gfsh version --full```


#### Introduction to some terms
##### GemFire distributed system
JVMs running GemFire form a distributed system. Each JVM is a GemFire peer member in the distributed system. A peer member connects to a GemFire distributed system and shares data with other peer members through data regions that are configured to
distribute events among themselves.
To start a GemFire peer, you create a GemFire cache in each member. The cache manages the connectivity to other GemFire peers. Peers discover each other through multicast messaging or a TCP locator service.

##### Regions
Regions are an abstraction on top of the distributed system. A region lets you store data in many JVMs in the system without regard to which peer's memory the data is stored in. Regions give you a map interface that transparently fetches your data from the appropriate cache. The Region class extends the java.util.Map interface, but it also supports querying and transactions.

##### Locator
The locator is a Pivotal GemFire process that tells new, connecting members where running members are located and provides load balancing for server use.
You can run locators as peer locators, server locators, or both:
Peer locators give joining members connection information to members already running in the locator's distributed system.
Server locators give clients connection information to servers running in the locator's distributed system. Server locators also monitor server load and send clients to the least-loaded servers.
By default, locators run as peer and server locators.
You can run the locator standalone or embedded within another GemFire process. Running your locators standalone provides the highest reliability and availability of the locator service as a whole.

###### Start the Locator
Use the following guidelines to start the locator:

Standalone locator.
Use the gfsh command-line utility. See Using the Pivotal GemFire SHell (gfsh) for more information on using gfsh. The syntax for starting the
Example commands:
```
gfsh>start locator --name=locator1
gfsh> start locator --name=locator2 --bind-address=192.168.129.205 --port=13489
```
###### Check Locator Status
If you are connected to the distributed system in gfsh, you can check the status of a running Locator by providing the Locator name. For example:
``` 
gfsh>status locator --name=locator1 
```

If you are not connected to a distributed system, you can check the status of a local Locator by providing the process ID, the Locator's hostname and port, or the Locator's current working directory. For example:
``` 
gfsh>status locator --pid=2986 
```
or
``` 
gfsh>status locator --host=host1 --port=1035 
```
or
``` 
$ gfsh status locator --dir=<locator_working_directory> 
```
###### Stop the Locator
If you are connected to the distributed system in gfsh, you can stop a running locator by providing the locator name. For example:
``` 
gfsh>stop locator --name=locator1 
```
If you are not connected to a distributed system, you can stop a local locator by specifying the locator's process ID or the locator's current working directory. For example:
``` 
gfsh>stop locator --pid=2986 
```
or
``` 
gfsh>stop locator --dir=<locator_working_directory> 
```
where <locator_working_directory> corresponds to the local working directory where the locator is running.

##### gfsh
gfsh: Pivotal GemFire gfsh (pronounced "gee-fish") provides a single, powerful command-line interface from which you can launch, manage, and monitor Pivotal GemFire processes, data, and applications.
###### Starting gfsh
Before you start gfsh, confirm that you have set the ``` GEMFIRE ``` and either the ``` GF_JAVA ``` or ``` JAVA_HOME ``` environment variables to the proper locations for your deployment, or that your ``` PATH ``` variable includes the java executable.
At the command prompt type ``` gfsh ```. The GFSH banner is displayed and the ``` gfsh ``` command prompt appears.
The gfsh utility provides useful features for a shell environment, including command auto-complete, preserved command history, and delimiting of multi-line commands. Context-sensitive help is available by command and by topic.
To view a list of available ``` gfsh ``` commands, press Tab at an empty prompt.
