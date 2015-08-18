# Lab 3

In this module we will programmatically add (retrieve) user-defined data to (from) a GemFire cluster.

We will use Spring Boot to build this application.
Go to ```https://start.spring.io``` and fill out the Project Metadata form with the following values

| Metadata            | Value |
|---------------------|-------|
| Group               | com.handson.labs |
| Artifact            | gemfire-labs     |
| Name                | gemfire-lab3     |
| Description         | Gemfire Labs  |
| Package Name        | com.handson.lab3 |
| Type                | Maven Project |
| Packaging           | Jar           |
| Java Version        | 1.8 |
| Language            | Java |
| Spring Boot Version | 1.2.5 |

Select the following Project Dependencies
* Gemfire

Click on `Generate Project`
A file called `<artifact>.zip` is downloaded to your machine. Unzip it and place the unzipped folder in your IDE's workspace.

Import the contents of your folder as a Maven or Gradle Project. Since I downloaded the artifact as a Maven project, I'll choose to import it into my IDE as a Maven Project.

Lets create a class ```Account``` in package ```com.handson.lab3``` to model a Bank Account. To keep things simple, let's provide only three member variables to it.
```
class Account {
	private String id;
	private String name;
	private double balance;
}
```
Use an IDE like Eclipse to do the following:
* Create getter and setter methods
* Generate a toString method





