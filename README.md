[//]: # (cat v0.md v1.md > README.md)
# A-Demo-WebApp-for-User-Data-Collection

## A demo Web Application to take user input from the end-users

## How To Run ?

### This is inspired from [Spring-IO source](https://spring.io/guides/gs/rest-service/#scratch) and [Spring-IO rest-service](https://start.spring.io/)

Since we are using Maven, we can run the application by

(1) building the JAR file with

```shell
./mvnw clean package
````

(2) and then run the JAR file as:

```shell
java -jar target/UserDataCollection-0.0.1-SNAPSHOT.jar
```

(3) Now, enjoy

```sh
open:- http://localhost:8080/
```
# v1 #

1. Added home/landing page that looks like
   this: </br><img src="documentation/v1/v1_home.png" alt="drawing" width="350"/>
2. Click to ```Signup``` for new user opens this signup
   page: </br><img src="documentation/v1/v1_user_signup_page.png" alt="drawing" width="350"/>
3. Fill-up the details  (something like
   this): </br><img src="documentation/v1/v1_user_signup_POST.png" alt="drawing" width="350"/>
4. After filling up details, clicking the ```Submit``` button stores this as new user to database (with newly created
   usedId): </br><img src="documentation/v1/v1_user_signup_result.png" alt="drawing" width="350"/>

## Tech-Features ##

1. Using Spring and MVC Concepts
    1. Model Objects
    2. View
    3. Controller Module
2. Connecting to an actual toy database with url: ```jdbc:mysql://sql9.freesqldatabase.com:3306/sql9580318```
3. Using JDBC
4. Added DAO layer
5. Using Hibernate
6. Added lombok
7. Added logging ```lombok.extern.slf4j.Slf4j```

## Potential for v2 ##

1. Throw error if existing email is used for new signup.
2. User should be able to edit the details via userId/email.
3. Maybe, add test cases ?
