# gemfire-workshop

This repository describes hands-on labs for Pivotal GemFire.

### Prerequisites
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
10. Note: You cannot log in directly as the gemfire user because interactive login has been disabled. Rather, you must first log in as the root user or as a user with sudo privileges. You can then execute commands as the gemfire user by using sudo -u gemfire command_name.
11. If you have not already done so, download and install a compatible JDK or JRE on the computer or VM.
12. Configure the ``` JAVA_HOME ``` environment variable. If you will be using the gfsh command-line utility or managing servers and locators with the ServerLauncher and LocatorLauncher APIs, then you must set ``` JAVA_HOME ``` to a JDK installation. For example: ``` JAVA_HOME=/usr/java/jdk1.7.0_72; export JAVA_HOME ```
13. Add GemFire scripts to your the PATH environment variable. For example: ``` PATH=$PATH:$JAVA_HOME/bin:/opt/pivotal/Pivotal_GemFire_810/bin; export PATH ```. The following step only applies to environments where you are running GemFire processes or GemFire client applications outside of gfsh. The gfsh (as well as the deprecated cacheserver) script sets these environment variable for you. If you are running GemFire processes or applications outside of gfsh, then configure the following environment variables for GemFire.
14. Set the GEMFIRE environment variable to point to your GemFire installation top-level directory. (You should see bin, lib, dtd, and other directories under GEMFIRE.) The following variables definitions are examples; your installation path may vary depending on where you install GemFire and the version you are installing. ``` GEMFIRE=/opt/pivotal/Pivotal_GemFire_810; export GEMFIRE ```
15. Configure your ``` GF_JAVA ``` environment variables as shown in these examples. ``` GF_JAVA ``` must point to the java executable file under your ``` JAVA_HOME ```. (If you have not done so already, you should also set your ``` JAVA_HOME ``` variable to a supported Java installation.) ``` GF_JAVA=$JAVA_HOME/bin/java; export GF_JAVA  ```
Type gfsh version at the command line and verify that the output lists the version of Pivotal GemFire that you wished to install. For example: 
``` 
# gfsh
v8.1.0 
```
If you want more detailed version information such as the date of the build, build number and JDK version being used, type ``` gfsh version --full```


#### Introduction to some terms
##### gfsh

gfsh: Pivotal GemFire gfsh (pronounced "gee-fish") provides a single, powerful command-line interface from which you can launch, manage, and monitor Pivotal GemFire processes, data, and applications.

###### Starting gfsh
Before you start gfsh, confirm that you have set the ``` GEMFIRE ``` and either the ``` GF_JAVA ``` or ``` JAVA_HOME ``` environment variables to the proper locations for your deployment, or that your ``` PATH ``` variable includes the java executable.

At the command prompt type ``` gfsh ```. The GFSH banner is displayed and the ``` gfsh ``` command prompt appears.

The gfsh utility provides useful features for a shell environment, including command auto-complete, preserved command history, and delimiting of multi-line commands. Context-sensitive help is available by command and by topic.

To view a list of available ``` gfsh ``` commands, press Tab at an empty prompt.
