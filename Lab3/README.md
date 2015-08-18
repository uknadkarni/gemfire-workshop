# Lab 3

In this module we will do the following:
* Start a Locator
* Start a cache server
* Programmatically add (retrieve) user-defined data to (from) a GemFire cluster from a client.


## Using Spring Boot to create a Gemfire-project

We will use Spring Boot to build this application.
Go to ```https://start.spring.io``` and fill out the Project Metadata form with the following values

| Metadata            | Value |
|---------------------|-------|
| Group               | com.handson.labs |
| Artifact            | gemfire-lab3     |
| Name                | gemfire-lab3     |
| Description         | Gemfire Labs  |
| Package Name        | com.handson.lab3 |
| Type                | Maven Project |
| Packaging           | Jar           |
| Java Version        | 1.8 |
| Language            | Java |
| Spring Boot Version | 1.2.5 |

Select `gemfire` from the Project Dependencies

Click on `Generate Project`
A file called `<artifact>.zip` is downloaded to your machine. Unzip it and place the unzipped folder in your IDE's workspace.

Import the contents of your folder as a Maven or Gradle Project. Since I downloaded the artifact as a Maven project, I'll choose to import it into my IDE as a Maven Project.

## Creating the `Account` class

Lets create a class ```Account``` in package ```com.handson.lab3``` to model a Bank Account. To keep things simple, let's provide only three member variables to it.
This class should implement ```Serializable```
```
class Account implements Serializable {
	private String id;
	private String name;
	private double balance;
}
```
Most Data stored in Gemfire is shared and moved across JVMs. Classes that model such data should implement ```java.io.Serializable```

Use an IDE like Eclipse to do the following:
* Create getter and setter methods
* Generate a toString method

## Serialization
Classes in Gemfire can be serialized by one of the following:
* ```java.io.Serializable```
* ```PdxSerializable``` helps you avoid the larger costs of performing deserialization
* ```DataSerializable``` is about 25% faster than PDX serialization


## Start a Locator
Type the following command at the gfsh prompt
`gfsh>start locator --name=locator1 --port=55221`

## Configuring Gemfire Caches
Gemfire caches can be configured using an xml file. Although this file can have any name, it is generally referred to as `cache.xml`. Use this file to set up general cache facilities and behavior and to create and initialize cached data regions.
A samle cache XML file is included with the product distribution.
Let's configure a cache using the xml file.

## Start a Server
Type the following command at the gfsh prompt
`gfsh>start server --name=server1 --server-port=40412 --cache-xml-file=xml/Server.xml`


