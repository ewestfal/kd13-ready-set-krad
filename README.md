kd13-ready-set-krad
===================

Example projects for Ready, Set, KRAD! at Kuali Days 2013

The following instructions take you through what setup you need in order to run this project, as well as how to recreate it as per the "Ready, Set, KRAD!" session at Kuali Days 2013.

## Setup

### Install and Load database

* Install MySQL 5.5.x or 5.6.x - <http://dev.mysql.com/downloads/mysql/>
* Checkout "Ready, Set, KRAD!" Kuali Rice version from SVN using your IDE or the SVN command line: `svn co http://svn.kuali.org/repos/rice/sandbox/ready-set-krad`
	* This is a special version of Rice 2.4 milestone 3 with some fixes that were made for the purposes of this presentation
* Change directory into the location of the "master" database and run an impex import (be sure to type the "mvn" command all on one line!):

```
cd db/impex/master/
mvn clean install -Pdb,mysql -Dimpex.username=kd13rsk -Dimpex.password=kd13rsk -Dimpex.dba.password=NONE -Dimpex.database=kd13rsk
```
* Alter the above depending on what your MySQL dba or "root" password is
* When this completes you should see a message indicating that the database import was a success.

### Install Maven

* Download and install Maven 3 - <http://maven.apache.org>
* We will be working with a SNAPSHOT version of Rice, so we need to make sure our settings are configured such that we can download from the Kuali snapshots repository
* Open the ~/.m2/settings.xml file in a text editor (or create it if it does not exist)
* Ensure that you have a default activated profile defined which adds the Kuali snapshot repository as an available repository:

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">
                      
  ...
  
  <profiles>
    <profile>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <repositories>
        <repository>
          <id>Kuali-Snapshot</id>
          <url>http://nexus.kuali.org/content/repositories/kuali-snapshot</url>
       </repository>
     </repositories>
  </profiles>
  
</settings>
```

### Install Java 7

* Download and install the latest Java SE 7 version and install it
* <http://www.oracle.com/technetwork/java/javase/downloads/index.html?ssSourceSiteId=ocomen>

### Install Tomcat 7 and Supporting Jars

* Download the "Core" zip distribution of Tomcat 7 and extract it to a directory of your choice: <http://tomcat.apache.org/download-70.cgi>
* Download the MySQL JDBC driver and copy it to the "lib" directory of your extracted Tomcat distribution: <http://search.maven.org/remotecontent?filepath=mysql/mysql-connector-java/5.1.26/mysql-connector-java-5.1.26.jar>
* Download the Tomcat Spring Instrumentation jar and copy it to the "lib" directory of your extracted Tomcat distribution: <http://search.maven.org/remotecontent?filepath=org/springframework/spring-instrument-tomcat/3.2.3.RELEASE/spring-instrument-tomcat-3.2.3.RELEASE.jar>

### Install Eclipse and Configure Tomcat Server

* Download "Eclipse IDE for Java EE Developers" version 4.3 (Kepler) and extract to a location of your choice. Will be the second link on the following page (and the first link under "Package Solutions"): <http://eclipse.org/downloads/> install eclipse version 4.3.1, j2ee version, kepler
* Launch Eclipse and point it to a workspace location of your choosing if prompted
* Navigate to `Window -> Show Views -> Servers` to bring up the "Servers" view.
* Click the link in this view to create a new server
* Select `Apache -> Tomcat v7.0 Server` then click "Next"
* Browse for where you uninstall your Tomcat server, then click "Finish"
* Double-click on the newly created server in the Servers view in order to bring up the editor for the Tomcat server configuration
* Click "Open Launch Configuration", go to the "Arguments" tab, and then enter `-Xmx512m -XX:MaxPermSize=256m` to the end of the "VM Arguments".
	* This increases the amount of memory given to the Java virtual machine that runs the Tomcat serfer
* Back on the editor for your Tomcat server, under "Timeouts" change the start timeout from `45` to `300`
* Now save the Tomcat server configuration (`File -> Save`) and close the editor pane



## Running this Project

* Clone the project from GitHub: `git clone https://github.com/ewestfal/kd13-ready-set-krad.git`
* Open Eclipse
* Import the project into Eclipse by going to `File -> Import…` and then navigating to `Maven -> Existing Maven Projects`.
* Find the `kd13-ready-set-krad` project and import it
* **Note:** You may see some validation-related errors after the project builds. These can be ignored. Additionally, you may see a couple of JAX-RS related errors which can also be ignored. They will look like the following:
	* `JAX-RS (REST Web Services) 2.0 can not be installed : One or more constraints have not been satisfied.`
	* `JAX-RS (REST Web Services) 2.0 requires Java 1.7 or newer.`
* Open the project tree.  Right click on the `setup_database.launch` file and select Run As...
	* Select `setup_database` and wait for the process to complete.
	* This will install the supporting tables and data for the demo.
	* This step is just running `mvn -P db` from the command line.  For reference, the scripts being run are in the `src/main/config/db` directory.
* Open the "Servers" view by going to `Window -> Show Views -> Servers`
* Click-and-drag the `kd13-ready-set-krad` project to the Tomcat server you set up in the servers view during the setup instructions.
* Start the Tomcat server from the Servers view
* After startup, navigate to the main page by going to <http://localhost:8080/kd13-ready-set-krad>
* Log in as the `admin` user and you should see the application portal!

## Steps to Recreate this Project

### Using the Kuali Rice "Quickstart" Archetype

To create the initial project structure, use the Kuali Rice "Quickstart" archetype. To do this, execute the following maven command from the command line. You'll want to do this from inside of a directory where you want your project to be generated:

```
mvn -U archetype:generate -DarchetypeCatalog=http://nexus.kuali.org/content/repositories/kuali-snapshot/ -DarchetypeGroupId=org.kuali.rice -DarchetypeArtifactId=rice-archetype-quickstart -DarchetypeVersion=2.4.0-M3-kd2013-SNAPSHOT -Ddatasource_ojb_platform=MySQL -Ddatasource_password=kd13rsk -Ddatasource_url=jdbc:mysql://localhost:3306/kd13rsk -Ddatasource_username=kd13rsk
```

This will generate a new KRAD application which is configured to use MySQL with the database configuration referenced on the command line above.

**TODO:** @kellerj fill in details here :)
