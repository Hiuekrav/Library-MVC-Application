# Library - Web Application
This repository contains the implementation of a web application that serves as the presentation layer (GUI) for a previously implemented REST service. The application is built using the Spring Boot framework, following the MVC (Model-View-Controller) design pattern, and adheres to the principles of Dependency Injection and Inversion of Control.

## Overview
The application is divided into three modules:
1. **DTO**: Contains data transfer objects (DTOs) for create, update, and output operations used by REST service.
2. **REST**: The previously implemented REST service, which remains unchanged and is reused in this project.
3. **MVC**: The newly implemented module that handles the web interface, user interactions, and communication with the REST service.

This project focuses solely on the MVC module, as the REST and DTO modules were implemented in a previous project and remain unchanged. For details on the REST implementation, please refer to the original project repository (https://github.com/Hiuekrav/Library-Spring-Boot-Application).

## MVC Module
The MVC module is responsible for handling user interactions, rendering dynamic HTML pages, and communicating with the REST service. It is structured as follows:
1. **Controllers**:
    - ```BookMvcController```: Handles requests related to books, such as displaying the list of books and searching by title.
    - ```RentMvcController```: Manages rental operations, including creating, ending, and removing rentals.
    - ```UserMvcController```: Handles user registration.
    - ```LibraryController```: Serves the main page of the application.
2. **Forms**:
    - ```EndRemoveRentForm```: A data structure for ending or removing a rental used to capture and transfer form data between the client and server in the MVC module.
    - ```RentForm```: A data structure for creating a rental with a specified time range used to capture and transfer form data between the client and server in the MVC module.
    - ```RentNowForm```: A data structure for creating an immediate rental used to capture and transfer form data between the client and server in the MVC module.
3. **Services**:
    - ```BookMvcService```: Communicates with the REST service to fetch book-related data.
    - ```RentMvcService```: Handles rental-related operations by interacting with the REST service.
    - ```UserMvcService```: Manages user registration by sending requests to the REST service.
4. **Views**:
    - HTML pages (```main```, ```register```, ```books```, ```rents/active```, ```rents/future```, ```rents/archive```) are located in the resources/templates directory.  
    - Client-side functionality is handled with scripts and it includes renting books, ending rents, removing rents, searching books and validation for registration and book rental.
5. **Internationalization**:
    - The application supports both English (```en```) and Polish (```pl```) languages. 

## Functionality
The application provides the following features:
1. **User Registration**:
    - Anonymous users can register as clients via a registration form.
    - Input validation ensures data correctness, and errors are displayed if validation fails.
2. **Book Management**:
    - Users can view a list of available books.
    - Books can be searched by title.
3. **Rental Management**:
    - Users can create rentals for books, specifying a time range or renting immediately.
    - Active, future, and archived rentals can be viewed.
    - Rentals can be ended or removed.

## Setup and Testing the Project
### Prerequisites
- Docker
- Java 21
- Maven

### Installation
1. Clone this repository.
2. Backend setup:
    - Set up MongoDB replica set by running services directly from docker-compose using IDE or by executing ```docker-compose up -d``` command.
    - Navigate to the REST directory (```cd REST```).
    - Build the project: ```mvn clean install```
    - Run the application: use ```mvn spring-boot:run``` command from REST directory in your terminal (or run main method from RestApplication class in your IDE).
3. Frontend setup:
    - Run main method from MvcApplication class in your IDE.

### Testing
Access the application at http://localhost:80 in your browser.

## Screenshots
![book list](https://github.com/user-attachments/assets/f0752557-4829-4429-b07b-40d954298cf4)
This page introduce book list view. It allows user to rent available books with rental period specification (rental can be in future) or without - rental begins after confirmation. If the book is currently rented it cannot be rented by any other user.


![active rents list](https://github.com/user-attachments/assets/3084646c-26cf-4818-98b1-af019e8a4365)
This page introduce active rents list view. It allows to browse users' active rents and end or remove the selected rent on request.


![future rents list](https://github.com/user-attachments/assets/4da2c8b2-662c-433f-8f7d-645651c8e324)
This page introduce future rents list view. It allows to browse users' upcomming rents and remove the selected rent on request.


![register form](https://github.com/user-attachments/assets/5dd7b595-c49f-4023-9d35-4c823f1ba346)
This page introduce registration form view. User can register in the Library System by signing up through the form.


## Authors

### Wiktoria Bilecka
### Grzegorz Janasek
