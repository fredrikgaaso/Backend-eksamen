KandidatNr: 2019 and 2024

When you first open the project folder inn your IDE, you may need to load maven scripts and set up a JDK for which we are using Corretto-19 and enable annotations processing for lombok.

We weren't able to make end-to-end tests with MockMVC for some of our controller functionality, so instead we focused on integration tests and unit test for our business logic and services.

To be able to view the database in http://localhost:8080/h2-console/ you need to change the username to admin and the password is empty. From there you can check all the data we put inn and can also try to edit, insert and delete information from the database.