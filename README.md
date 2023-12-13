# To Execute the QEChallenge2

The automation test design is based on the Cucumber BDD using the below plugins:
- Cucumber
- Gherkin
- Maven 
- Java 1.8 jars
- Selenium webdriver jars
- TestNG 
- Mysql jar

IDE Used: IntelliJ
The dependencies are present in the pom.xml

To run the cases on the IDE use
1. Create a pull request/clone or download the QEChallenge2 folder
2. Increment the natid number in the Oppenheimer-Sheet1.csv file
3. Run the testing using Maven - >Test
4. Report generated is shown as link in the IDE. The test run details are shown in the html format when clicked on the link.

Issues Seen:
1. The "Generate Tax Relief" button remains disabled for long time with the message "Egress Tax Relief file process in progress".
2. Get request for "http://localhost:9997/api/v1/hero/owe-money?natid" for any natid returns 500 server error. 
   

