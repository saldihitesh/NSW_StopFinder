Stop Finder’ Framework
1.	Introduction: ‘‘Stop Finder’ is a service for obtaining details of stops within the New South Wales public transport network. As a test automation consultant you are required to create a quick Proof of Concept (POC) for implementing an API test automation solution. For the purpose of this exercise we suggest limiting the time spent to around 2 hours.

2.	Task Create an automated test for the scenario below in the language (C#, Java etc) and framework (xUnit, BDD etc) of your choice. Note that although the scenario is defined in Gherkin, a BDD implementation is not mandatory

•	Scenario: A stop providing multiple transport options can be located
•	Given Phileas is looking for a stop
•	When he searches for "Wynyard Station"
•	Then a stop named "Wynyard Station, Sydney" is found
•	And the stop provides more than one mode of transport

The URL for Stop Finder is
www.transportnsw.info/web/XML_STOPFINDER_REQUEST?TfNSWSF=true&language=en&name_sf=stopname&outputFormat=rapidJSON&type_sf=any&version=10.2.2.48

For further reference the web page that consumes the api is transportnsw.info/stops#

3.	Solution Proposed: Stop Finder’ is a service for obtaining details of stops within the New South Wales public transport network. Hence, the best approach to automate the testing process of this API will be to develop the Page Object Model (POM) framework. In POM framework every API of the application has a corresponding test class. Test class will be consisting of test cases corresponding to the API of the application. By implementing this framework, we get the advantages, such as, easy to maintain, reusable, readable and easily understandable. For instance, if in future there are any changes in the API parameters than only one file needs to be updated instead of the code.
POM also supports various type of file format, such as, Excel, CSV, txt and Database, which can be used as external file for providing the Query parameter ot path parameter to the test cases.
This framework is compatible with most of the continuous development and integration tools, such as Jenkins, Maven, Git, etc.  

4.	 Technology stacks:
         Automation Framework		: Selenium – Page Object Model

         Programming Language		: Java

         IDE				            : Eclipse

         Testing framework		    : TestNG

         Build Management 		    : Maven

         Subversion Tool			    : Git/GitHub

         CD/CI Tool			        : Jenkins
5.	Pre-condition for the execution:
i.	Maven has to be configured on the system, on which this project will be executed.
6.	Instruction for Installation and Execution:
i.	Extract the project folder at any location, from where test has to be executed.
ii.	Open command prompt and access the project location.
iii.	Execute the command “mvn test”
iv.	Verify the results.
7.	Project Structure:
?	src/test/java/ - has two folders: 
•	com.qa.ahq.nswstopfinder.common – It Contains all common test utilites functions for API Testing. 
•	com.qa.ahq.nswstopfinder.tests – It contains the all test classes of the nsw stopfinder API .
?	src/test/resources/ - has one folder: 
•	TestData – properties file to get the base URL and XLSX file name,file path and  sheet name.
•	Excel file:it contains the Query parameter of API.
8.	TestNG Result:
         [RemoteTestNG] detected TestNG version 6.14.3
         ****************************
         *******Test Output**********
         ****************************
         Status Code:200
         ****************************
         Actual Location:Wynyard Station, Sydney
         ****************************
         Available modes:1
         ****************************

===============================================
NSW Stop Finder Automation Suite
Total tests run: 3, Failures: 0, Skips: 0
===============================================
9.	Github Location:

