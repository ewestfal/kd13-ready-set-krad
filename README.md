kd13-ready-set-krad
===================

Example projects for Ready, Set, KRAD! at Kuali Days 2013

This is a simple project to build an application to manage sessions at a typical conference, like Kuali Days!

The following instructions take you through what setup you need in order to run this project, as well as how to recreate it as per the "Ready, Set, KRAD!" session at Kuali Days 2013.

## Setup

### Install Database

* Install MySQL 5.5.x or 5.6.x - <http://dev.mysql.com/downloads/mysql/>

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

The steps below can be used to create a project very similar to this final product. There is a branch of this repository named `rebuild` which we will use as a starting point and contains a lot of the initial configuration and setup already put into place. If you want to learn how this starting point was created, see  [Appendex: Recreating the Project Starting Point](#app1) section at the end of this document (running the quickstart archetype, etc.).

### Grab the Starting Point

To grab the starting code base for the demo application, if you have this repository already cloned from Git, checkout and switch to the `rebuild` branch using the `Team -> Switch To…` menu option in Eclipse, or execute the following from the command line:

`git checkout rebuild`

### Import Project into Eclipse

Follow the instructions earlier for "Running this Project" to import the project into Eclipse so it can be run using Tomcat.

### Some Standard Locations

As you work with the project, there are some standard directory structures and locations that are useful to know:

* `src/main/config/db` - directory containing database sql scripts
* `src/main/config/workflow` - directory containing workflow XML configuration
* `src/main/java` - location of Java source files, most of the source files will be under an org.kuali.kd2013 package in this directory
* `src/main/resources` - location of non-Java resource and configuration files
* `src/main/webapp` - web application root

### Create and Load the Database

In the root of the project is a file named `setup_database.launch`. Right click on this in Eclipse and choose `Run As -> setup_database`. This will setup all required database tables, including some new ones to hold our conference and presenter data.

### Do the JPA Mapping

Since we are going to use JPA to map our Java objects to the database, we need to fill out the details and necessary annotations on our "POJO's" for the `ConferenceSession` and `SessionPresenter` data objects. The `PresenterInfo` data object is already mapped for you so you can look at that for examples as you work through the other two objects.

#### ConferenceSession JPA Mapping

Open `ConferenceSession` and add the following annotations at the top of the class:

```
@Entity
@Table(name="KD13_CONF_SESS_T")
```

Next, add the following properties with their associated JPA annotations as follows:

```
@Id
@Column(name="SESS_ID",length=10)
@GeneratedValue(generator="KD13_CONF_SESS_ID_S")
@PortableSequenceGenerator(name="KD13_CONF_SESS_ID_S")
protected String sessionId;

@Column(name="TITLE",length=60,nullable=false)
protected String sessionTitle;

@Column(name="SESS_DATE")
@Temporal(TemporalType.DATE)
protected Date date;

@Column(name="START_TIME",length=8)
protected String startTime;

@Column(name="END_TIME",length=8)
protected String endTime;

@Column(length=20)
protected String room;

@Column(name="SESS_TYPE_CODE",length=4)
protected String sessionTypeCode;

@Column(name="DESCRIPTION",length=200)
protected String description;

@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
@JoinColumn(name="SESS_ID",referencedColumnName="SESS_ID")
protected List<SessionPresenter> presenters = new ArrayList<SessionPresenter>();
```

The `@PortableSequenceGenerator` annotation is one that was created as part of the krad-data module to support existing "sequence-like" behavior in MySQL.

Also, `java.util.Date` should be used for the `Date` field above. This requires the `@Temporal` annotation to be used as above.

Generate getters and setters for these using the `Source -> Generate Getters and Setters…` menu option in Eclipse.

#### SessionPresenter JPA Mapping

Open `SessionPresenter` and add the following annotations at the top of the class:

```
@Entity
@Table(name="KD13_SESS_PRES_T")
```

Next, add the following properties with their associated JPA annotations as follows:

```
@Id
@Column(name="SESS_PRES_ID",length=10)
@GeneratedValue(generator="KD13_SESS_PRES_ID_S")
@PortableSequenceGenerator(name="KD13_SESS_PRES_ID_S")
protected String sessionPresenterId;

@Column(name="SESS_ID",length=10)
protected String sessionId;

@Column(name="PRES_ID",length=10)
protected String presenterId;

@Column(name="PRIMARY_IND")
protected Boolean primary = Boolean.FALSE;

@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH,CascadeType.DETACH})
@JoinColumn(name="PRES_ID",referencedColumnName="PRES_ID",updatable=false,insertable=false)
protected PresenterInfo presenter;
```

Next, generate the getters and setters for these properties.

There are a few things to note about the above mapping.

First, on the Boolean field here, this column will store "Y" and "N" for true/false. The krad-data module automatically registers a JPA 2.1 `@Converter` for Boolean to behave this way. Custom converters in JPA can be defined as well and annotated explicitly on columns.

Second, on the PresenterInfo relation, we are mapping two fields to the `PRES_ID` column. So we ave to make sure that one of them is set to updatable and insertable of "false".

### Apply UIF (User Interface Framework) Defaulting

The KRAD UIF has capabilities built into it to allow us to easily create Lookups (search views) and Inquiries (information views) for our data objects. This functionality will also take advantage of various pieces of metadata we get from our JPA annotations. We will do this for `ConferenceSession` and `SessionPresenter`.

#### ConferenceSession UIF Defaulting

Open the `ConferenceSession` object and add the following annotation above the class:

```
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY,UifAutoCreateViewType.LOOKUP})
```

Next, add the following "display hints" to `startTime` and `endTime`:

```
@UifDisplayHints({
	@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
	, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
	, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
})
```

This tells the framework not to include this field in the lookup criteria, results, or on the inquiry.
 
Next, add some `@NonPersistentProperty` annotations that are used for display purposes in the UI only. Add the following methods to this class, place the first one between `endTime` and `room` properties and the last one at the end of the property list. This is because the defaulting will process the properties in the order they are defined:

```
@NonPersistentProperty
@Label("Time")
@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
public String getDateRangeString() {
	return StringUtils.trimToEmpty(startTime)
			+ " - "
			+ StringUtils.trimToEmpty(endTime);
}
```

Note the use of the `@Label` annotation here. The "smart defaults" in KRAD will automatically construct a label for you based on the property name. However, in this case it would have chosen "Date Range String" which isn't what we really want, so "Time" makes more sense.

Next, add the following:

```
@NonPersistentProperty
@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
public String getPresenterNames() {
	StringBuilder sb = new StringBuilder();
	if ( presenters != null ) {
		for ( SessionPresenter sp : presenters ) {
			sb.append( sp.getPresenter().getName() );
			sb.append( "\n" );
		}
	}
	return sb.toString();
}
```

Additionally, we want a drop-down menu to show up for the `sessionTypeCode` so add the following values finder annotation above this property:

```
@KeyValuesFinderClass(SessionTypeValuesFinder.class)
```

At this point our `ConferenceSession` data object is ready to go.

#### SessionPresenter UIF Defaulting

The `SessionPresenter` is really contained within the conference session, so it doesn't need it's own lookup or inquiry, but there is some stuff we can do in this class to allow our display to work a bit better.

Add the following annotation above the `presenter` property:

```
@InheritProperties({ 
		@InheritProperty(name = "name", label = @Label("Presenter Name")),
		@InheritProperty(name = "institution")})
```

This allows us to essentially "inherit" properties from the `PresenterInfo` data object so that we can display them with our `SessionPresenter` object. We can override the label as part of this as well. We do that in this case because, while we have the id of the presenter, that isn't really useful information for the end user. Instead, we want to display their name. This can also be used as a shortcut for definining a `@NonPersistentProperty`.

At this point, our `SessionPresenter` data object is ready to go.

### Register our Data Objects in the Data Dictionary

Before we can test out our defaulted views, we need to register our data objects in the Data Dictionary. To do that, open the `ConferenceSession.xml` file under the "org.kuali.kd2013.datadictionary" package under `src/main/resources`.

Add the following lines:

```
<bean id="ConferenceSession" parent="DataObjectEntry"
	p:dataObjectClass="org.kuali.kd2013.dataobject.ConferenceSession" />

<bean id="PresenterInfo" parent="DataObjectEntry"
	p:dataObjectClass="org.kuali.kd2013.dataobject.PresenterInfo" />

<bean id="SessionPresenter" parent="DataObjectEntry"
	p:dataObjectClass="org.kuali.kd2013.dataobject.SessionPresenter" />
```

This simply tells KRAD that these are Data Objects that we want the framework to recognize. It will then read all of the metadata available on those objects (including the UIF defaulting) and index that within the data dictionary. Note that you can also override metadata, define attribtues, etc. within these `DataObjectEntry` elements as well if you prefer to do so there instead of using annotations.

But using the annotations and the "smart defaults", we have way less XML than we use to have in Kuali Nervous Styem (KNS)-based applications.

### Start the App and Try it Out

Start the application in Eclipse and navigate to the base URL (login as "admin").

You should see links to lookups for the Conference Session and Presenter. Click on these and you can see how these user interfaces have been created automatically for us.

At this point you can't create a new Conference Session, but that is what we will do next.

### Create the ConferenceSession Maintenance Document

Unfortunately, we don't have easy defaulting in place for maintenance documents yet, so we need to build this in the traditional KRAD way using Spring XML configuration.

Add the following to the `ConferenceSession.xml` data dictionary file:

```
<bean id="ConferenceSessionMaintenanceDocument" parent="uifMaintenanceDocumentEntry">
	<property name="dataObjectClass" value="org.kuali.kd2013.dataobject.ConferenceSession" />
	<property name="documentTypeName" value="ConferenceSession" />
	<property name="lockingKeys">
		<list>
			<value>sessionId</value>
		</list>
	</property>
</bean>

<bean id="ConferenceSession-MaintenanceView" parent="Uif-MaintenanceView">
	<property name="headerText" value="Conference Session Maintenance" />
	<property name="dataObjectClassName" value="org.kuali.kd2013.dataobject.ConferenceSession" />
	<property name="items">
		<list merge="true">
			<bean parent="Uif-MaintenanceGridSection">
				<property name="headerText" value="Session Information" />
				<property name="items">
					<list>
						<bean parent="Uif-InputField" p:propertyName="sessionId" p:readOnly="true" />
						<bean parent="Uif-InputField" p:propertyName="sessionTitle" />
						<bean parent="Uif-InputField" p:propertyName="date" />
						<bean parent="Uif-InputField" p:propertyName="startTime" />
						<bean parent="Uif-InputField" p:propertyName="endTime" />
						<bean parent="Uif-InputField" p:propertyName="sessionTypeCode" />
						<bean parent="Uif-InputField" p:propertyName="description" />
					</list>
				</property>
			</bean>
			<bean parent="Uif-MaintenanceStackedCollectionSection">
				<property name="headerText" value="Presenters" />
				<property name="collectionObjectClass" value="org.kuali.kd2013.dataobject.SessionPresenter" />
				<property name="propertyName" value="presenters" />
				<property name="layoutManager.summaryTitle" value="Presenter" />
				<property name="items">
					<list>
						<bean parent="Uif-InputField" p:propertyName="presenterId" />
						<bean parent="Uif-InputField" p:propertyName="presenter.name" p:readOnly="true" />
					</list>
				</property>
			</bean>
		</list>
	</property>
</bean>
```

### Start the App and Ingest the Workflow Configuration

Since the maintenance framework requires a KEW workflow document type to exist, we must ingest one.

There is a skeleton file provided for you in the project located at `src/main/config/workflow/ConferenceSession.xml`.

Modify the `<name>` and `<label>` elements as follows:

```
<name>ConferenceSession</name>
<label>Conference Session</label>
``` 

Next, start the application. Then naviagte to the "XML Ingester" screen. Once there, click on the file upload button and choose your workflow ConferenceSession.xml file. Note that our project has two files named `ConferenceSession.xml`, one is the workflow file and the other is the data dictionary configuration file. Make sure you choose the right onw!

Once the Ingestion is complete your document type has been created. You can now navigate back to the Conference Session lookup and there should be a "Create New" link on there. If you click that you can create and route a new Conference Session.

## <a href="app1"></a> Appendix: Recreating the Project Starting Point

What follows is some information on how to create much of the project starting point (the `rebuild` branch of this repository in GitHub).

### Using the Kuali Rice "Quickstart" Archetype

To create the initial project structure, use the Kuali Rice "Quickstart" archetype. To do this, execute the following maven command from the command line. You'll want to do this from inside of a directory where you want your project to be generated:

```
mvn -U archetype:generate \
-DarchetypeCatalog=http://nexus.kuali.org/content/repositories/kuali-snapshot/ \
-DarchetypeGroupId=org.kuali.rice \
-DarchetypeArtifactId=rice-archetype-quickstart \
-DarchetypeVersion=2.4.0-M3-kd2013-SNAPSHOT \
-DgroupId=org.kuali.kd2013 \
-DartifactId=kd13-ready-set-krad \
-Dversion=1.0-SNAPSHOT \
-Ddatasource_ojb_platform=MySQL \
-Ddatasource_url=jdbc:mysql://localhost:3306/kd13rsk \
-Ddatasource_username=kd13rsk \
-Ddatasource_password=kd13rsk
```

This will generate a new KRAD application which is configured to use MySQL with the database configuration referenced on the command line above.

At this point, you are ready to begin modifying the application to add functionality.

### Configure JPA

The archetype is great, but it doesn't get us all the way there. We need to do some additional configuration before the project setup is ready. Our first task is to configure our project to use the Java Persistence API (JPA).

Add the following to `src/main/resources/org/kuali/kd2013/BootStrapSpringBeans.xml`:

```
<import resource="classpath:org/kuali/rice/core/CommonSpringBeans.xml"/>
<import resource="classpath:org/kuali/rice/krad/config/KRADDependentModuleCommonImports.xml" />

<alias alias="kradApplicationDataSource" name="riceDataSource" />

<bean id="jpaPersistenceUnitName" class="java.lang.String">
  <constructor-arg value="ready-set-krad" />
</bean>

<util:list id="jpaPackagesToScan">
  <value>org.kuali.kd2013</value>
</util:list>
<util:list id="managedClassNames" />
<util:list id="additionalMetadataProviders" />
<util:list id="springMetadataFileLocations" />

<import resource="classpath:org/kuali/rice/krad/config/KRADSpringBeans-jpa-common.xml" />
	
<bean id="readySetKradModuleConfiguration" class="org.kuali.rice.krad.bo.ModuleConfiguration">
  <property name="namespaceCode" value="KR-RSK"/>
  <property name="packagePrefixes">
    <list>
      <value>org.kuali.kd2013</value>
    </list>
  </property>
  <property name="initializeDataDictionary" value="true"/>
  <property name="dataDictionaryPackages">
    <list>
      <value>classpath:org/kuali/kd2013/datadictionary</value>
    </list>
  </property>
  <property name="entityManager" ref="sharedEntityManager"/>
  <property name="providers">
    <list>
      <ref bean="jpaPersistenceProvider"/>
      <ref bean="metadataProvider"/>
    </list>
  </property>
</bean>

<bean id="readySetKradModuleService" class="org.kuali.rice.krad.service.impl.ModuleServiceBase">
  <property name="moduleConfiguration" ref="readySetKradModuleConfiguration"/>
</bean>

<bean class="org.kuali.rice.core.framework.resourceloader.RiceSpringResourceLoaderConfigurer">
  <property name="localServiceName" value="ReadySetKradContext"/>
</bean>
```

This includes some common spring file imports which are handy and then configures and enables JPA (Java Persistence API) for the application.
