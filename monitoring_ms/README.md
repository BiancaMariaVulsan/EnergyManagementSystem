# Device Service

## Getting Started
    
    These instructions will help you set up and run the Device Management Service on your local development machine.    

## API Documentation

    Create, Read, Update, and Delete (CRUD) operations for devices.
    Retrieve device information, including description, address, and owner and maximum energy consumption.

### Prerequisites

    Java
    Spring Boot
    Hibernate
    PostgreSQL (or your preferred database)
    Spring Security (for authentication and authorization)
    RESTful API design

### Installing

    1. Clone the repository
    2. Create a database in PostgreSQL
    3. Update the database connection details in the application.properties file
    4. Build the project using Maven
    5. Run the project

### Deployment

    Before building the image run maven clean, install and package commands to make sure everything is prepared.
    Also make sure to change the db url in the application.properties file to the correct one.
    If there are requests to localhist, make sure to change the url to the correct network address.

    Remove any previos image named devices and create a new one with the following command:   
    docker build -t monitoring .
    Note: it is important to use the recommended name for the image, otherwise the docker-compose file will not work.


    Create the image for the user service and for the client accoring to their corresponding Dockerfiles.
    After creating the images for all microservices:
    Navigate in the root directory: 
    cd ..

    Run the following command: 
    docker-compose up -d

    The services will be available on the ports specified in the ../docker-compose.yml file.
    You can also deply the services individually by running 'docker-compose up -d' in the corresponding directory.

