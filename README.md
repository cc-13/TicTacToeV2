# Tic Tac Toe v2

## Overview

An simple implementation of Tic Tac Toe.

## How to run it
To build the application JDK 1.8 and Maven is required.
Make sure environment variables JAVA_HOME and PATH are set properly.
For example:
```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home
export PATH=$PATH:<path-to-maven-installtion>/apache-maven-3.6.3/bin
```
If all prerequisite software is installed and the code is extracted to a directory, change to that directory and run the package command.
```
mvn clean package
```
Once the build is successful, the jar package will be located into target directory. Following command will start the game.
```
cd target
java -cp my-game-2-1.0-SNAPSHOT-jar-with-dependencies.jar
```
## Difference between v1 and v2
- V2 follows the MVC pattern. It hides implementation of user interface to the data model, and also offer a controller layer on which more features or changes can be made (For example. web or network capability).
- V2 builds executable Jar file.

