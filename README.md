# Transaction Insight System

## Overview

The Transaction Insight System is a web application designed to enable financial organizations to efficiently query, filter, and analyze large volumes of financial transactions in real-time with minimal latency. It provides insights into transaction data, allowing users to make quick decisions and identify potential risks or opportunities.

## Architecture

The system follows a microservices architecture, with each microservice responsible for a specific set of functionalities. This approach promotes scalability, maintainability, and independent deployment.

### Microservices

*   **API Gateway:** Entry point for all external requests. Handles routing, authentication, and rate limiting.
*   **Transaction Service:** Responsible for handling transaction-related operations such as querying, filtering, and retrieving transaction details.
*   **Selection Set Service:** Manages the creation, storage, and retrieval of "Transaction Selection Sets" (reusable queries).
*   **Insights Service:** Processes transaction events, generates insights, and provides a query interface for retrieving insights.

### Technologies

*   **Backend:** Java with Spring Boot
*   **Database:** PostgreSQL
*   **Message Broker:** RabbitMQ
*   **Cache:** Redis
*   **Containerization:** Docker


## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 17 or higher
*   Maven
*   Docker
*   Docker Compose

### Setup Instructions

1.  **Clone the repository:**

    ```bash
    git clone git@github.com:mehta-gaurav/Transaction-Insights.git
    cd Transaction-Insights
    ```

2.  **Build and run the microservices using Docker Compose:**

    ```bash
    docker-compose up --build
    ```

    This command will build the Docker images for all the microservices and start them in a containerized environment.

3.  **Access the application:**

    Once the microservices are running, you can access the API Gateway at `http://localhost:8080`.

### Configuration

Each microservice can be configured using environment variables or application properties files (`application.properties` or `application.yml`). Refer to the documentation for each microservice for specific configuration options.

**Common Configuration:**

*   **Database Connection:** Configure the database connection properties in each microservice that requires database access.
*   **RabbitMQ Connection:** Configure the RabbitMQ connection properties in each microservice that publishes or consumes messages.
*   **Redis Connection:** Configure the Redis connection properties in the Insights Service.

## API Documentation

The API documentation for each microservice is available using OpenAPI (Swagger). You can access the Swagger UI for each microservice by navigating to the `/swagger-ui.html` endpoint. For example, the API Gateway Swagger UI might be located at `http://localhost:8080/swagger-ui.html`.

## Data Flow (Transaction Creation)

1.  A user sends a request to the API Gateway to create a new transaction.
2.  The API Gateway routes the request to the Transaction Service.
3.  The Transaction Service saves the transaction to the database.
4.  The Transaction Service publishes a "TransactionCreated" event to RabbitMQ.
5.  The Insights Service consumes the "TransactionCreated" event and adds the transaction ID to the Redis cache.
6.  The API Gateway returns a success response to the user.

## Contributing

We welcome contributions to the Transaction Insight System! Please follow these guidelines when contributing:

1.  **Fork the repository.**
2.  **Create a new branch for your feature or bug fix.**
3.  **Make your changes and commit them with clear, concise commit messages.**
4.  **Submit a pull request to the main branch.**

Please ensure that your code adheres to the project's coding standards and includes appropriate unit tests.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

If you have any questions or feedback, please contact us at [Gaurav Mehta](mailto:gaurav7902@gmail.com).