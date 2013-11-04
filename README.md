kd13-ready-set-krad
===================

Example projects for Ready, Set, KRAD! at Kuali Days 2013

The following instructions take you through what setup you need in order to run this project, as well as how to recreate it as per the "Ready, Set, KRAD!" session at Kuali Days 2013.

Setup:

- Install and load database
-- Install MySQL 5.6.x
-- Checkout "Ready, Set, KRAD!" Kuali Rice version from SVN:
--- svn co http://svn.kuali.org/repos/rice/sandbox/ready-set-krad
--- This is a special version of Rice 2.4 milestone 3 with some fixes that were made for the purposes of this presentation
-- Change directory into master database and run impex import:
--- d db/impex/master/
--- mvn clean install -Pdb,mysql -Dimpex.username=kd13rsk -Dimpex.password=kd13rsk -Dimpex.dba.password=NONE -Dimpex.database=kd13rsk
--- Alter the above depending on what your MySQL dba or "root" password is
- Install maven 3
-- need something like the following in your settings.xml since we are working with snapshots
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
- install java 7
- install Tomcat 7
-- put spring tomcat instrument and mysql connector jars in lib directory
- install eclipse version 4.3.1, j2ee version, kepler
-- setup "servers" view with Tomcat 7
--- be sure to give tomcat more memory!  -Xmx512m -XX:MaxPermSize=256m
--- increase timeout to 300 seconds


Running the Project:

To recreate this project, follow these steps:

- Execute the Kuali Rice "quickstart" archetype
-- mvn -U archetype:generate -DarchetypeCatalog=http://nexus.kuali.org/content/repositories/kuali-snapshot/ -DarchetypeGroupId=org.kuali.rice -DarchetypeArtifactId=rice-archetype-quickstart -DarchetypeVersion=2.4.0-M3-kd2013-SNAPSHOT -Ddatasource_ojb_platform=MySQL -Ddatasource_password=kd13rsk -Ddatasource_url=jdbc:mysql://localhost:3306/kd13rsk -Ddatasource_username=kd13rsk
- Next up, we import into Eclipse... (verison 4.3.1, j2ee version, kepler)
- Open Eclipse
- File -> Import...
- Maven -> Existing Maven Projects
- Find kd13-ready-set-krad project and import it
- open servers view, click and drag project into tomcat server
- launch tomcat server
- after startup, navigate to main page and login with "admin", http://localhost:8080/kd13-ready-set-krad
- you will see two errors after it builds (these can be ignored, appears to be a bug)
-- Description	Resource	Path	Location	Type
JAX-RS (REST Web Services) 2.0 can not be installed : One or more constraints have not been satisfied.	kd13-ready-set-krad		line 1	Maven Java EE Configuration Problem
-- Description	Resource	Path	Location	Type
JAX-RS (REST Web Services) 2.0 requires Java 1.7 or newer.	kd13-ready-set-krad		line 1	Maven Java EE Configuration Problem
- TODO! Jonathan fill in here ;)
