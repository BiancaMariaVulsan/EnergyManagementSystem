# Chat Service

## Getting Started
    
    These instructions will help you set up and run the Chat Management Service on your local development machine.    

## API Documentation

    Thie ms receives requests about chat conversations via web sockets and sends responses via web sockets.

### Prerequisites

    Java
    Spring Boot
    Redis
    Spring Security (for authentication and authorization)

### Installing

    1. Clone the repository
    2. Start Redis local server
        2.1. Make sure you have WSL. Open a WSL terminal and run the following commands:

        curl -fsSL https://packages.redis.io/gpg | sudo gpg --dearmor -o /usr/share/keyrings/redis-archive-keyring.gpg

        echo "deb [signed-by=/usr/share/keyrings/redis-archive-keyring.gpg] https://packages.redis.io/deb $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/redis.list
        
        sudo apt-get update
        sudo apt-get install redis

        sudo service redis-server start

    4. Build the project using Maven
    5. Run the project

### Deployment

    Before building the image run maven clean, install and package commands to make sure everything is prepared.

    Remove any previos image named devices and create a new one with the following command:   
    docker build -t chat .
    Note: it is important to use the recommended name for the image, otherwise the docker-compose file will not work.


    Create the image for the user service and for the client accoring to their corresponding Dockerfiles.
    After creating the images for all microservices:
    Navigate in the root directory: 
    cd ..

    Run the following command: 
    docker-compose up -d

    The services will be available on the ports specified in the ../docker-compose.yml file.
    You can also deply the services individually by running 'docker-compose up -d' in the corresponding directory.

