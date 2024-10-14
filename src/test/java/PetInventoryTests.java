import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Use TestMethodOrder with OrderAnnotation to control test execution order
@TestMethodOrder(OrderAnnotation.class)
public class PetInventoryTests {
    private static String orderId; // Class-level variable to store the orderId

    //@Before
    @BeforeEach
    public void setup() {
        // Initialize RestAssured configuration before running tests
        RestAssuredConfig.initConfig();
    }

    @Test
    @Order(1)
    public void testPlaceOrder() {
        JSONObject newPetOrder = new JSONObject();
        newPetOrder.put("petId", 54321);
        newPetOrder.put("quantity", 2);
        newPetOrder.put("shipDate", "2024-10-11T00:00:00.000Z");
        newPetOrder.put("status", "placed");
        newPetOrder.put("complete", "true");

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/store/order")
                .withBody(newPetOrder.toJSONString())
                .executePost();

        orderId = response.jsonPath().getString("id");
        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200)
                .body("petId", equalTo(54321))
                .body("quantity", equalTo(2));
        System.out.println(orderId);
    }

    @Test
    @Order(2)
    public void testGetInventory() {
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/store/inventory")
                .executeGet();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200)
                .body("available", greaterThan(0)); // Ensure that we get some results
    }


    @Test
    @Order(3)
    public void testGetOrderById() {
        System.out.println(orderId);
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/store/order/{orderId}")
                .withOrderId(orderId)
                .executeGetByOrderId();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200);
        String actualValue = response.jsonPath().getString("id");
        //Assert.assertEquals(orderId, actualValue);
        assertEquals(orderId, actualValue);

    }

    @Test
    @Order(4)
    public void testDeleteOrder() {
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/store/order/{id}")
                .withId(orderId)
                .executeDelete();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200); // Validate that the pet is deleted successfully
    }

    @Test
    @Order(5)
    public void testGetOrderByInvalidId() {
        String invalidOrderId = "1000";
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/store/order/{orderId}")
                .withOrderId(invalidOrderId)
                .executeGetByOrderId();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(404);

    }

    @Test
    @Order(6)
    public void testDeleteInvalidOrder() {
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/store/order/{id}")
                .withId("123456")
                .executeDelete();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(404); // Validate that the pet is deleted successfully
    }

}
