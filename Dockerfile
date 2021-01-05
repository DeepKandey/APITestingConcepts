FROM openjdk:14-jdk-alpine3.10

#workspace
WORKDIR /usr/share/APITesting_Practice

#ADD .jar under target from host into this image
ADD target/APITesting_Practice.jar          APITesting_Practice.jar
ADD target/APITesting_Practice-tests.jar    APITesting_Practice-tests.jar
ADD target/libs                             libs

#in case of any other dependency like .csv / .json / .xls
#please add that as well

#ADD suite files
ADD src/test/resources/testNGConfigFiles/testng.xml              testng.xml

ENTRYPOINT java -cp APITesting_Practice.jar:APITesting_Practice-tests.jar:libs/* org.testng.TestNG testng.xml
