# Sky Takeout Platform

## Project Overview

Sky Takeout Platform is a fully-featured online food ordering system that supports two core functions: merchant management and user ordering. The project adopts a modular design, comprising multiple modules including common utilities, data model definitions, and server-side logic processing.

## Features

- **Employee Management**: Supports employee login, logout, and information management
- **Dish Management**: Provides functionalities for dish categorization, dish information maintenance, and flavor management
- **Meal Set Management**: Supports meal set definition and dish association management
- **Order Processing**: Covers the complete workflow including order status management, order statistics, order submission, and payment
- **Shopping Cart System**: Supports temporary storage of multiple dishes or meal sets
- **User System**: Includes user login and address book management
- **Data Statistics**: Provides data visualization features such as turnover statistics, order completion rate, and top 10 best-selling items
- **Payment Integration**: Integrated with WeChat Pay
- **File Storage**: Supports file uploads to Alibaba Cloud OSS

## Module Structure

- **sky-common**: Common utilities, constant definitions, exception handling, configuration properties, etc.
- **sky-pojo**: Definitions of Data Transfer Objects (DTOs), Entity classes, and View Objects (VOs)
- **sky-server**: Core business logic module containing controllers, service classes, and Mapper interfaces

## Technology Stack

- Spring Boot
- MyBatis Plus
- JWT
- WeChat Pay
- Alibaba Cloud OSS
- Swagger/OpenAPI
- ThreadLocal context management

## Quick Start

1. Clone the project
```bash
git clone https://gitee.com/lumenl1u/sky-take-out.git
```

2. Create a database and import the initialization script
```bash
mysql -u root -p < sql/sky.sql
```

3. Configure database connection details and third-party service keys in `application.yml`

4. Start the project
```bash
cd sky-take-out
mvn spring-boot:run
```

## Usage Instructions

- Access the admin backend API documentation via `/swagger-ui.html`
- Access the user-facing API documentation via `/user-api.html`
- Employee login endpoint: POST /admin/employee/login
- User login endpoint: POST /user/user/login

## Error Handling

Unified response format:
```json
{
  "code": 0,
  "message": "Success",
  "data": {}
}
```

Error code conventions:
- 0: Success
- 1: Failure
- 2: Not logged in

## Contribution Guidelines

Contributions are welcome! Please follow these guidelines:
1. Fork the project and create a new branch
2. Ensure all unit tests pass before submitting code
3. Maintain consistent code style
4. Provide detailed descriptions of changes when submitting a PR

## License

This project is licensed under the Apache-2.0 License.