# Appointment Booking Service

This service provides functionality to query appointment slots for customers with sales managers. It ensures that sales managers are not double-booked and matches customers to sales managers based on specific criteria.

## Features

- Query available slots.
- Match customers to sales managers based on language, products, and customer rating
- Ensure no overlapping bookings for sales managers

## Technologies Used

- Java
- Spring Boot
- PostgreSQL
- Maven
- Docker
- Node.js

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6.3 or higher
- PostgreSQL 16
- Node.js 16.13.0 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/appointment-booking-service.git
    cd appointment-booking-service
    ```
2. Database setup:
    - Run the following commands in the database folder. This requires that you have Docker installed on your local environment:
   ```sh
   docker build -t enpal-coding-challenge-db .
   docker run --name enpal-coding-challenge-db -p 5432:5432 -d enpal-coding-challenge-db
   ```
    - Once the Docker container is up and running, ensure you can connect to it using your favorite DB query tool (e.g., DBeaver or pgAdmin). The default connection string is: `postgres://postgres:mypassword123!@localhost:5432/coding-challenge`
    - If you want to use a local database installation instead, you can also get the init.sql file and run it in your local database.
2. Configure the database:
    - Update the `src/main/resources/application.properties` file with your PostgreSQL database credentials.

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```
5. The application will be accessible at `http://localhost:3000`.
6. The application will automatically create the necessary tables and insert initial data on startup. 
7. Setup tests
   - Run the following commands in the test-app folder. This requires
   that you have node installed on your local environment
   ```
       npm install
       npm run test
   ```
    - All tests should pass if the setup was successful.
### API Endpoints

- **Query Available Slots**
    - **URL:** `/calendar/query`
    - **Method:** `POST`
    - **Parameters:**
        - `date` (required): The date of the appointment
        - `language` (required): Language preference. Supports German, English
        - `rating` (required): Customer rating. Supports Gold, Silver, Bronze
        - `products` (required): List of products to discuss. Supports SolarPanels, Heatpumps
    
      **Sample Request**:
    ```json
    {
        "date": "2024-05-03",
        "products": ["SolarPanels", "Heatpumps"],
        "language": "German",
        "rating": "Gold"
    }
    ```
    - **Response:**
    ```json
    [
        {
            "available_count": 1,
            "start_date": "2024-05-03T10:30:00.000Z"
        },
        {
            "available_count": 2,
            "start_date": "2024-05-03T12:00:00.000Z"
        }
    ]
    ```

## Contributing

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.