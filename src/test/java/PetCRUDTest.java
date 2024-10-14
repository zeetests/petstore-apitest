import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(OrderAnnotation.class)
public class PetCRUDTest {

    //@Before
    @BeforeEach
    public void setup() {
        // Initialize RestAssured configuration before running tests
        RestAssuredConfig.initConfig();
    }

    @Test
    @Order(1)
    public void testAddNewPet() {
     //Setup

        JSONObject newPet = new JSONObject();
        newPet.put("id", 12345);
        newPet.put("name", "Doggie");
        newPet.put("status", "available");

        // Add photoUrls array to the pet object
        JSONArray photoUrls = new JSONArray();
        photoUrls.put("https://example.com/photo1.jpg");
        photoUrls.put("https://example.com/photo2.jpg");

        newPet.put("photoUrls", photoUrls);

        // Add tags array to the pet object
        JSONArray tags = new JSONArray();
        JSONObject tag1 = new JSONObject();
        tag1.put("id", 1);
        tag1.put("name", "tag1");
        tags.put(tag1);

        JSONObject tag2 = new JSONObject();
        tag2.put("id", 2);
        tag2.put("name", "tag2");
        tags.put(tag2);

        newPet.put("tags", tags);

        System.out.println(newPet);

        //Trigger
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(newPet.toJSONString())
                .executePost();

        //Assert
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("id", equalTo(12345))
                .body("name", equalTo("Doggie"))
                .body("status", equalTo("available"))
                .body("photoUrls", hasItems("https://example.com/photo1.jpg", "https://example.com/photo2.jpg"))
                .body("tags.name", hasItems("tag1", "tag2")); // Validate that the pet tags are added correctly

    }


    @Test
    @Order(2)
    public void testUpdateNameOfExistingPet() {
        // Create the pet object with updated details

        JSONObject updatedPet = new JSONObject();
        updatedPet.put("id", 12345);
        updatedPet.put("name", "UpdatedPetName");

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(updatedPet.toJSONString())
                .executePut();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("id", equalTo(12345))
                .body("name", equalTo("UpdatedPetName")); // Validate that the pet was updated correctly
    }

    @Test
    @Order(3)
    public void testUpdateStatusExistingPet() {
        // Create the pet object with updated details

        JSONObject updatedPet = new JSONObject();
        updatedPet.put("id", 12345);
        updatedPet.put("status", "sold");

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(updatedPet.toJSONString())
                .executePut();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("id", equalTo(12345))
                .body("status", equalTo("sold")); // Validate that the pet was updated correctly
    }

    @Test
    @Order(4)
    public void testUpdateTagsOfExistingPet() {
        // Create the pet object with updated details
        JSONObject updatedPet = new JSONObject();
        updatedPet.put("id", 12345);
        // Add tags array to the pet object
        JSONArray tags = new JSONArray();
        JSONObject tag1 = new JSONObject();
        tag1.put("id", 1);
        tag1.put("name", "new tag1");
        tags.put(tag1);

        JSONObject tag2 = new JSONObject();
        tag2.put("id", 2);
        tag2.put("name", "new tag2");
        tags.put(tag2);

        updatedPet.put("tags", tags);

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(updatedPet.toJSONString())
                .executePut();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("id", equalTo(12345))
                .body("tags.name", hasItems("new tag1", "new tag2")); // Validate that the pet tags are added correctly
    }

    @Test
    @Order(5)
    public void testUpdatePhotoUrlsExistingPet() {
        // Create the pet object with updated details

        JSONObject updatedPet = new JSONObject();
        updatedPet.put("id", 12345);

        // Add photoUrls array to the pet object
        JSONArray photoUrls = new JSONArray();
        photoUrls.put("https://example.com/photo1.jpg");
        photoUrls.put("https://example.com/photo2.jpg");

        updatedPet.put("photoUrls", photoUrls);

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(updatedPet.toJSONString())
                .executePut();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("id", equalTo(12345))
                .body("photoUrls", hasItems("https://example.com/photo1.jpg", "https://example.com/photo2.jpg")); // Validate that the pet was updated correctly
    }

    @Test
    @Order(6)
    public void testUpdateExistingPetAllDetals() {
        //Setup

        JSONObject updatedPet = new JSONObject();
        updatedPet.put("id", 12345);
        updatedPet.put("name", "Doggie");
        updatedPet.put("status", "available");

        // Add photoUrls array to the pet object
        JSONArray photoUrls = new JSONArray();
        photoUrls.put("https://example.com/photo1.jpg");
        photoUrls.put("https://example.com/photo2.jpg");

        updatedPet.put("photoUrls", photoUrls);

        // Add tags array to the pet object
        JSONArray tags = new JSONArray();
        JSONObject tag1 = new JSONObject();
        tag1.put("id", 1);
        tag1.put("name", "tag1");
        tags.put(tag1);

        JSONObject tag2 = new JSONObject();
        tag2.put("id", 2);
        tag2.put("name", "tag2");
        tags.put(tag2);

        updatedPet.put("tags", tags);

        //Trigger
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(updatedPet.toJSONString())
                .executePut();

        //Assert
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("id", equalTo(12345))
                .body("name", equalTo("Doggie"))
                .body("status", equalTo("available"))
                .body("photoUrls", hasItems("https://example.com/photo1.jpg", "https://example.com/photo2.jpg"))
                .body("tags.name", hasItems("tag1", "tag2")); // Validate that the pet tags are added correctly

    }

    @Test
    @Order(7)
    public void testGetPetById() {
        String petId = "12345";
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{petId}")
                .withPetId(petId)
                .executeGetByPetId();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200);
        String actualValue = response.jsonPath().getString("id");
        //Assert.assertEquals(petId, actualValue);
        assertEquals(petId, actualValue);

    }

    @Test
    @Order(8)
    public void testFindPetsByStatus() {
        String [] status = {"available", "pending", "sold"};
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/findByStatus")
                .withStatus(status)
                .executeGetByStatus();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200);
    }

    @Test
    @Order(9)
    public void testUploadImage() {
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{petId}/uploadImage")
                .withPetId("12345")
                .executePostWithFileUpload("path/to/image.png", "Sample Image");

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200);
    }

    @Test
    @Order(10)
    public void testFindPetsByTags() { //Note: Deprecated
        String[] tags = {"pet"};
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/findByTags")
                .withTags(tags)
                .executeGetByTags();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200)
                .body("tags", notNullValue()); // Validate that pets with the specified tags are found, assuming this is expected
    }

    @Test
    @Order(11)
    public void testUpdatePetViaFormData() {
        //Trigger
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{petId}")
                .withPetId("12345")
                .executePostWithFormData("newName", "sold");

        //Assert
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200); // Validate that the pet tags are added correctly

        String actualValue = response.jsonPath().getString("message");
        assertEquals("12345", actualValue);
    }

    @Test
    @Order(12)
    public void testUpdatePetViaFormDataAllFields() {

        //Setup
        JSONObject newPet = new JSONObject();
        newPet.put("id", 12345);
        newPet.put("name", "Doggie");
        newPet.put("status", "available");

        // Add photoUrls array to the pet object
        JSONArray photoUrls = new JSONArray();
        photoUrls.put("https://example.com/photo1.jpg");
        photoUrls.put("https://example.com/photo2.jpg");

        newPet.put("photoUrls", photoUrls);

        // Add tags array to the pet object
        JSONArray tags = new JSONArray();
        JSONObject tag1 = new JSONObject();
        tag1.put("id", 1);
        tag1.put("name", "tag1");
        tags.put(tag1);

        JSONObject tag2 = new JSONObject();
        tag2.put("id", 2);
        tag2.put("name", "tag2");
        tags.put(tag2);

        newPet.put("tags", tags);

        System.out.println(newPet);

        //Trigger
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{petId}")
                .withPetId("12345")
                .executePostWithFormData("newName", "sold");

        //Assert
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200); // Validate that the pet tags are added correctly
        String actualValue = response.jsonPath().getString("message");
        assertEquals("12345", actualValue);
    }

    @Test
    @Order(13)
    public void testUploadImageWithAPIKey() {
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{petId}/uploadImage")
                .withPetId("12345")
                .executePostWithFileUploadWithAPIKey("path/to/image.png", "Sample Image");

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200);
    }

    @Test
    @Order(14)
    public void testGetPetByIdWithApiKey() {
        String petId = "12345";
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{petId}")
                .withPetId(petId)
                .executeGetByPetIdWithAPIKey();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200);
        String actualValue = response.jsonPath().getString("id");
        assertEquals(petId, actualValue);

    }

    @Test
    @Order(15)
    public void testDeletePet() {
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{id}")
                .withId("12345")
                .executeDelete();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200); // Validate that the pet is deleted successfully
    }


    @Test
    @Order(16)
    public void testAddNewPetWithAPIKey() {
        //Setup

        JSONObject newPet = new JSONObject();
        newPet.put("id", 12345);
        newPet.put("name", "Doggie");
        newPet.put("status", "available");

        // Add photoUrls array to the pet object
        JSONArray photoUrls = new JSONArray();
        photoUrls.put("https://example.com/photo1.jpg");
        photoUrls.put("https://example.com/photo2.jpg");

        newPet.put("photoUrls", photoUrls);

        // Add tags array to the pet object
        JSONArray tags = new JSONArray();
        JSONObject tag1 = new JSONObject();
        tag1.put("id", 1);
        tag1.put("name", "tag1");
        tags.put(tag1);

        JSONObject tag2 = new JSONObject();
        tag2.put("id", 2);
        tag2.put("name", "tag2");
        tags.put(tag2);

        newPet.put("tags", tags);

        System.out.println(newPet);

        //Trigger
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(newPet.toJSONString())
                .executePostWithAPIKey();

        //Assert
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("id", equalTo(12345))
                .body("name", equalTo("Doggie"))
                .body("status", equalTo("available"))
                .body("photoUrls", hasItems("https://example.com/photo1.jpg", "https://example.com/photo2.jpg"))
                .body("tags.name", hasItems("tag1", "tag2")); // Validate that the pet tags are added correctly

        testDeletePet();
    }

    @Test
    @Order(17)
    public void testFailedGetPetByNonExistentId() {
        String petId = "888888888888";
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{petId}")
                .withPetId(petId)
                .executeGetByPetId();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(404);

    }


    @Test
    @Order(18)
    public void testFailedDeleteNonExistingPet() {
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{id}")
                .withId("5555")
                .executeDelete();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(404); // Validate that the pet is deleted successfully
    }

    @Test
    @Order(19)
    public void testAddNewPetWithOnlyName() {
        //Setup

        JSONObject newPet = new JSONObject();
        newPet.put("name", "DoggieNameOnly");

        //Trigger
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(newPet.toJSONString())
                .executePost();

        //Assert
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("name", equalTo("DoggieNameOnly"));

    }

    @Test
    @Order(20)
    public void testAddNewPetWithOnlyID() {
        //Setup

        JSONObject newPet = new JSONObject();
        newPet.put("id", 12345678);

        //Trigger
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(newPet.toJSONString())
                .executePost();

        //Assert
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("id", equalTo(12345678));

    }

    @Test
    @Order(21)
    public void testAddNewPetWithEmptyBody() {
        //Setup

        JSONObject newPet = new JSONObject();

        //Trigger
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet")
                .withBody(newPet.toJSONString())
                .executePost();

        //Assert
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200); // Note: this is the current behavior, should fail since empty body, no validation

    }

    @Test
    @Order(22)
    public void testUploadImageWrongFileType() {
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{petId}/uploadImage")
                .withPetId("12345")
                .executePostWithFileUpload("path/to/doc.docx", "Sample Doc");

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200); //Note: currently passing without file validation
    }

    @Test
    @Order(23)
    public void testUploadImageWithoutAdditionalMetadata() {
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/{petId}/uploadImage")
                .withPetId("12345")
                .executePostWithFileUpload("path/to/image.png", "");

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200);
    }

    @Test
    @Order(24)
    public void testFindPetsByOneStatusOnly() {
        String [] status = {"pending"};
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/findByStatus")
                .withStatus(status)
                .executeGetByStatus();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("status", everyItem(equalTo("pending"))); // Validate that all returned pets are pending
    }

    @Test
    @Order(25)
    public void testFindPetsWithNoStatus() {
        String [] status = {""};
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/findByStatus")
                .withStatus(status)
                .executeGetByStatus();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200); // // Note, returning 200 and brackets when no result
    }

    @Test
    @Order(26)
    public void testFindPetsWithWrongStatus() {
        String [] status = {"FakeStatus"};
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/pet/findByStatus")
                .withStatus(status)
                .executeGetByStatus();

        // Log the response details for debugging
        response.then().log().all();
        System.out.println("Actual status code:" +response.statusCode());
        System.out.println("Time taken:" +response.getTime());

        response.then()
                .statusCode(200)
                .body("status", everyItem(not(equalTo("FakeStatus")))); // Note, returning 200 and brackets when no result
    }
}
