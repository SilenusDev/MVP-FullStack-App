# MVP Fullstack app Angular /Spring Boot

This application is designed for developers who want to subscribe to topics and follow related articles.
It provides features such as:
- Topic subscription
- Article creation
- Commenting on articles

## Technologies Used
- **Frontend:** Angular 14
- **Backend:** Spring Boot 3.4, Java 17
- **Database:** SQL

## Install BDD
1. Create your database.
2. Execute the SQL script located at `./resources/data.sql` to initialize the database.

## Install Backend
In back folder
> cd back

Create an .env file at the root of the back folder and fill in the database variables 
replace XXXX by your own variables

DATABASE_URL=jdbc:mysql://localhost:3306/mvp?serverTimezone=UTC
DATABASE_USERNAME=xxxxx
DATABASE_PASSWORD=xxxxx
JWT_SECRET=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

Install dependencies:
> mvn clean install

Run the application:
> mvn spring-boot\:run or ./mvnw spring-boot:run 

The API documentation (Swagger) is available at:
http://localhost:3001/swagger-ui/index.html#/

## Install Frontend
In front folder 
> cd front

Install dependencies:
> npm install

Start the application:
> ng serve

The frontend will be available at:
http://localhost:4200

## basic connection
email
> user@user.com

password 
> user1234