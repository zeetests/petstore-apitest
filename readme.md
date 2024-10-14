
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

1. **Create Users with List Input**: Tests the API endpoint to create multiple users using a list.
2. **Create Users with Array Input**: Tests the API endpoint to create multiple users using an array.
3. **Delete Users**: Tests the API endpoint to delete users based on their usernames.

### Example Test Cases

- **Create Users with List Input**
    - This test sends a POST request to `/user/createWithList` with a JSON array containing user details.

- **Create Users with Array Input**
    - This test sends a POST request to `/user/createWithArray` with a JSON array containing user details.

- **Delete Users**
    - This test sends DELETE requests to `/user/{username}` for multiple usernames, checking for successful deletion or handling errors.

## How to Run Tests

1. Clone the repository:

   ```bash
   git clone https://github.com/zeetests/petstore-apitest.git
   ```

2. Resolve maven dependencies. You can double check by clicking pom.xml
   
3. Navigate to src > test > java. Right click on preferred test to run. i.e, PetCRUDTest 
   
4. Choose Run


## API Endpoints Tested For Pets Resource

### Pets Creation
- **Endpoint**: `/pet`
    - **Method**: `POST`
    - **Description**: Add a new pet to the store.

### Pets Update
- **Endpoint**: `/pet`
    - **Method**: `PUT`
    - **Description**: Update an existing pet.

- **Endpoint**: `/pet/{petId}`
  - **Method**: `PUT`
  - **Description**: Update a single pet via Form Data.

### Pets Details Retrieval
- **Endpoint**: `/pet/{petId}`
    - **Method**: `GET`
    - **Description**: Returns a single pet via ID.

- **Endpoint**: `/pet/findByTags`
    - **Method**: `GET`
    - **Description**: Returns results of pet(s) via tags.

- **Endpoint**: `/pet/findByStatus`
    - **Method**: `GET`
    - **Description**: Returns results of pet(s) via status.


### Pet Deletion
- **Endpoint**: `/user/{username}`
    - **Method**: `DELETE`
    - **Description**: Deletes a user by username. This can only be done by the logged-in user.

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

Inventory resource
- Can place order with 0 inventory

### Future Improvements: 
- Organize code and clean up
   - Refactor and reduce repetitive methods
  
- Improve test reporting