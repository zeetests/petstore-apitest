
# API Testing with RestAssured For Pet Store

This project contains automated tests for API  Swagger Petstore API documentation https://petstore.swagger.io/ using the RestAssured framework in Java. The tests cover various endpoints to create, retrieve, update, and delete pets, users and inventory.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Tests Overview](#tests-overview)
- [How to Run Tests](#how-to-run-tests)
- [API Endpoints Tested](#api-endpoints-tested)

## Prerequisites

Before running the tests, ensure you have the following installed:

- **Java JDK 8 or higher**
- **Maven** (for managing dependencies)
- **An IDE** (like IntelliJ IDEA or Eclipse)
  - pom.xml will take care of other dependencies


## Tests Overview

The following tests are implemented:

1. **Create Pets**: Tests the API endpoint to create pets in the Petstore.
2. **Update Pets**: Tests the API endpoint to update pet details in the Petstore.
3. **Delete Pets**: Tests the API endpoint to delete pet details
4. **Read Pets Details**: Tests the API endpoint to retrieve pet details
### Example Test Cases

- **Create Pets**
    - This test sends a POST request to `/pet` with a JSON containing user details.

- **Find Pet Details Status **
    - This test sends a GET request to `/pet/findByStatus`.

- **Delete Pets**
    - This test sends DELETE requests to `/pet/{petId}`.

## How to Run Tests

1. Clone the repository:

   ```bash
   git clone https://github.com/zeetests/petstore-apitest.git
   ```

2. Resolve maven dependencies. You can double check by clicking pom.xml
   
3-A. If you're using IntelliJ - Navigate to src > test > java. Right click on preferred test to run. i.e, PetCRUDTest. Then Choose Run

3-B. Run via terminal
1. **Open Terminal**: Navigate to your project directory where the `pom.xml` file is located.

2. **Run the Test**: Use the following command to run your JUnit tests:

   ```bash
   mvn test
   ```

   This command will execute all tests in your project.

3. **Run a Specific Test**: If you want to run a specific test class, you can use:

   ```bash
   mvn -Dtest=YourTestClassName test
   ```

   Replace `YourTestClassName` with the name of the test class you want to run.

4. **Run a Specific Test Method**: To run a specific test method within a test class, use:

   ```bash
   mvn -Dtest=YourTestClassName#yourTestMethod test
   ```

   Replace `yourTestMethod` with the name of the method you want to execute.



## API Endpoints Tested For Pets Resource

### Pets Creation
- **Endpoint**: `/pet`
    - **Method**: `POST`
    - **Description**: Tests add a new pet to the store.

- **Endpoint**: `/pet/{petId}/uploadImage`
  - **Method**: `POST`
  - **Description**: Tests uploading of an image


### Pets Update
- **Endpoint**: `/pet`
    - **Method**: `PUT`
    - **Description**: Tests updating an existing pet.

- **Endpoint**: `/pet/{petId}`
  - **Method**: `PUT`
  - **Description**: Tests updating a single pet via Form Data.

### Pets Details Retrieval
- **Endpoint**: `/pet/{petId}`
    - **Method**: `GET`
    - **Description**: Tests getting response of a single pet via ID.

- **Endpoint**: `/pet/findByTags`
    - **Method**: `GET`
    - **Description**: Marked deprecated. Tests getting response results of pet(s) via tags.

- **Endpoint**: `/pet/findByStatus`
    - **Method**: `GET`
    - **Description**: Tests getting response results of pet(s) via status.


### Pet Deletion
- **Endpoint**: `/pet/{petId}`
    - **Method**: `DELETE`
    - **Description**: Deletes a pet by id.

### Notes
- The tests assume that the API server is running and accessible.
- Adjust the base URL in the `RestAssuredConfig` class if the API server runs on a different host or port.
- Ensure that appropriate user credentials are set up if authentication is required for any endpoints.

### Observations

Pets resource

- Upload an image accepts other file types
- Missing information about what additionalMetadata can be provided.
- Pet can be created without details such as name.
- Getting error when trying to access https://petstore.swagger.io/oauth/authorize
- Getting error="invalid_client", error_description="Bad client credentials"
  - Endpoints does not require API key = `special-key` to do the operations

### Extras: 
Users resource
- Update User can be executed without username
- Pet status is not validated when placing order for pet
- Login doesn't have validation of username and password

Inventory resource
- Can place order with 0 inventory

### Future Improvements: 
- Organize code and clean up
   - Refactor and reduce repetitive methods
  
- Improve test reporting