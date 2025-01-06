# API Test Automation Framework

A robust and maintainable API testing framework built with Java, showcasing best practices in test automation.

## Overview

This framework demonstrates a modern approach to API testing with:

- Service-oriented architecture for better maintainability
- Data-driven testing with dynamic data generation
- Flexible test execution strategies
- Centralized response validation
- Clean and reusable code structure

## Project Structure 
<pre>
├── src
│ ├── main/java
│ │ ├── data # Test data generators
│ │ │ └── PetTestData.java
│ │ ├── models # Request/Response POJOs
│ │ │ ├── request
│ │ │ └── response
│ │ ├── services # API service layer
│ │ │ └── pet
│ │ │ ├── CreatePetService.java
│ │ │ ├── DeletePetService.java
│ │ │ ├── GetPetService.java
│ │ │ ├── UpdatePetService.java
│ │ │ └── UploadPetImageService.java
│ │ └── utils # Helper utilities
│ │ ├── BaseHelper.java
│ │ └── ResponseHelper.java
│ └── test/java
│ └── tests
│ └── pet # Test classes
│ ├── CreatePetTests.java
│ ├── DeletePetTests.java
│ ├── GetPetTests.java
│ ├── UpdatePetTests.java
│ └── UploadPetImageTests.java
</pre>

## Key Features

- **Service-Based Architecture**: Each API endpoint has its dedicated service class
- **Data-Driven Testing**: Using Faker for dynamic test data generation
- **Response Validation**: Centralized response validation through ResponseHelper
- **Flexible Test Execution**: Support for different test execution strategies

## Test Execution

### Run All Tests

```bash
mvn test
```

### Run Specific Tests

```bash
mvn test -Dgroups=pet-create
```

### Run Specific Test Method

```bash
mvn clean test -Dtest="GetPetTests#testGetPetById_Positive"
```

### Run Multiple Test Methods

```bash
mvn clean test -Dtest="GetPetTests#testGetPetById_Positive+testFindPetsByStatus_Positive"
```

## Key Components

- **PetRequest**: POJO for API requests with builder pattern
- **ResponseHelper**: Centralized response validation
- **BaseHelper**: Common API configurations and utilities
- **PetTestData**: Test data generation using Faker

## Test Categories

- Create Pet Tests (pet-create)
- Get Pet Tests (pet-get)
- Update Pet Tests (pet-update)
- Delete Pet Tests (pet-delete)
- Upload Image Tests (pet-upload)

## Dependencies

- RestAssured: API testing library
- TestNG: Test framework
- Lombok: Reduce boilerplate code
- Faker: Generate test data
- Jackson: JSON processing 