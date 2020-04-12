# RadiationWeb

## About Project

- IOTService back-end project for FCSE Subject: Web Programming and potential future SaaS project.
- Conrtibutors: 
  Ardian Abazi (171506) and Metodija Novkovski (171529)
- SaaS project for connecting IOT devices and recieving data. Processing and visualizing that data. Integrated with own devices for measuring radiation and displaying a radiation heat map in our city.

## Quick start

- Java EE Spring Boot 2 needs to be installed.
- PostgreSQL needs to be installed and connected with the Spring app with the port specified in application-dev.properties (The default port is 5432 which is the same one in our Spring app).
- In application-dev.properties you should insert the password of your PostgreSQL database instance.
- Test the database connection if fine, run the application.
- You can test some of the api within the test.http file in test folder (don't forget to add the JWT that you get after registering in the header after the keyword "Bearer " when calling other API controllers).

[Authentication](https://www.javainuse.com/spring/boot-jwt)
[Authorization](https://www.baeldung.com/spring-security-method-security)
