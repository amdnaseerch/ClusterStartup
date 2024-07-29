## Overview

Imagine an environment consisting of multiple nodes, where each node is a separate JVM process that could potentially be running on a different physical machine. This project demonstrates an application that coordinates between nodes to ensure that the message `System.out.println("We are started!")` is printed exactly once across the entire cluster, regardless of whether there are 1 or 10 nodes running.

In this distributed environment, the solution takes into account various scenarios, such as:
- Nodes starting simultaneously.
- Nodes starting at different times, with some potentially starting seconds or minutes later.
- Nodes restarting before others have started for the first time.
- Some nodes never starting at all.


## Prerequisites

Before you start, make sure you have the following installed and configured:

- **Java 17**: This project requires Java Development Kit (JDK) version 17. You can download it from [Oracle's official site](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or use any other distribution like OpenJDK.
- **Maven**: Apache Maven 3.8.6 is used for project management and build automation. You can download it from [Maven's official site](https://maven.apache.org/download.cgi).
- **ZooKeeper**: Apache ZooKeeper must be running on your local machine on port 2181. You can download ZooKeeper from [ZooKeeper's official site](https://zookeeper.apache.org/releases.html).

### Starting ZooKeeper

To start ZooKeeper, follow these steps:

1. Download and extract ZooKeeper.
2. Navigate to the extracted directory.
3. Copy the sample configuration file:

   ```sh
   cp conf/zoo_sample.cfg conf/zoo.cfg
   ```

4. Ensure the configuration file has the correct client port set (default is 2181):

   ```sh
   clientPort=2181
   ```

5. Start ZooKeeper:

   ```sh
   bin/zkServer.sh start
   ```

## Getting Started
Follow these steps.

### Clone the Repository

```sh
git clone https://github.com/amdnaseerch/ClusterStartup.git
cd ClusterStartup/hazelcast-assignment
```

### Build the Project

Use Maven to build the project. In the project root directory, run:

```sh
mvn clean package
```

### Run the Application

After building the project, you can start the application using the following command:

```sh
java -jar target/cluster-node-zookeeper-1.0-SNAPSHOT.jar
```

Note: The `pom.xml` is configured to specify the main class, so you don't need to provide it as a command-line argument when running the jar file.
