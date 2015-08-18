# Lab 3

In this module we will programmatically add (retrieve) user-defined data to (from) a GemFire cluster.

We will use Spring Boot to build this application.
Go to ```https://start.spring.io``` and fill out the Project Metadata form with the following values

| Metadata            | Value |
|---------------------|-------|
| Group               | com.handson.labs |
| Artifact            | gemfire-labs     |
| Name                | gemfire-labs     |
| Description         | Gemfire Labs  |
| Package Name        | com.handson.lab3 |
| Type                | Maven Project |
| Packaging           | Jar           |
| Java Version        | 1.8 |
| Language            | Java |
| Spring Boot Version | 1.2.5 |

In an IDE of your choice, let's create a class ```Account``` to model a Bank Account. To keep things simple, let's provide only three member variables to it.
```
class Account {
	private String id;
	private String name;
	private double balance;
}
```
Use IDE to generate getter and setter methods for this class.





