## Steps required to run the application:
1. To create a JAR file for the application in Spring Tool Suite, follow the below mentioned steps:
    * Right click on the ems project folder
    * Navigate to Run As -> Maven build...
    * Add the name ems-build-jar for the run configuration
    * In the Goals field, add ```clean package```
    * Apply the changes
    * Run the configuration
    * Once the build is successful the output JAR file (ems-0.0.1-SNAPSHOT.jar) will be in the ems\target directory
2. To use the development Spring profile (which uses the H2 in-memory database), run the following command: ```java -jar target\ems-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev```
3. To use the production Spring profile (which uses the MySQL database), run the following command: ```java -jar target\ems-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod```
