# User Service

## Getting Started
    
    These instructions will help you set up and run the User Management Service on your local development machine.    

## API Documentation

    Create, Read, Update, and Delete (CRUD) operations for users.
    Retrieve user information, including first name, last name, username, email and role.

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

    Remove any previos image named devices and create a new one with the following command:   
    docker build -t users .
    Note: it is important to use the recommended name for the image, otherwise the docker-compose file will not work.

    Create the image for the user service and for the client accoring to their corresponding Dockerfiles.
    Navigate in the root directory: 
    cd ..

    Run the following command: 
    docker-compose up -d

    The services will be available on the ports specified in the ../docker-compose.yml file.
    You can also deply the services individually by running 'docker-compose up -d' in the corresponding directory.

