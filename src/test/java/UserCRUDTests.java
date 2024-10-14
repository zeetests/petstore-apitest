import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


// Use TestMethodOrder with OrderAnnotation to control test execution order
@TestMethodOrder(OrderAnnotation.class)
public class UserCRUDTests {
    private static String userId; // Class-level variable to store the userId

   // @Before
    @BeforeEach
    public void setup() {
        // Initialize RestAssured configuration before running tests
        RestAssuredConfig.initConfig();
    }

    @Test
    @Order(1)
    public void testCreateUsersWithListInput() { //dynamic size
        JSONArray usersArray = new JSONArray();
        // Create first user object
        JSONObject user1 = new JSONObject();
        user1.put("id", 1);
        user1.put("username", "user1");
        user1.put("firstName", "John");
        user1.put("lastName", "Doe");
        user1.put("email", "john.doe@example.com");
        user1.put("password", "password");
        user1.put("phone", "1234567890");
        user1.put("userStatus", 1);

        // Create second user object
        JSONObject user2 = new JSONObject();
        user2.put("id", 2);
        user2.put("username", "user2");
        user2.put("firstName", "Alice");
        user2.put("lastName", "Smith");
        user2.put("email", "alice.smith@example.com");
        user2.put("password", "password2");
        user2.put("phone", "1231231234");
        user2.put("userStatus", 1);

        // Create third user object
        JSONObject user3 = new JSONObject();
        user3.put("id", 3);
        user3.put("username", "user3");
        user3.put("firstName", "Bob");
        user3.put("lastName", "Brown");
        user3.put("email", "bob.brown@example.com");
        user3.put("password", "password3");
        user3.put("phone", "9876543210");
        user3.put("userStatus", 1);

        // Add user objects to the users array
        usersArray.put(user1);
        usersArray.put(user2);
        usersArray.put(user3);

        System.out.println(usersArray);

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/user/createWithArray")
                .withBody(usersArray.toString())
                .executePost();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void testGetUserByUsername() {
        String username = "user1";

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/user/{username}")
                .withUsername(username)
                .executeGetByUsername();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200);
        String actualValue = response.jsonPath().getString("id");
        assertEquals("1", actualValue); // Ensure that we get some results
    }


    @Test
    @Order(3)
    public void testUpdateUser() {
        String username = "user2";

        JSONObject updatedUser = new JSONObject();
        updatedUser.put("id", 1);
        updatedUser.put("username", "user1");
        updatedUser.put("firstName", "Jane");
        updatedUser.put("lastName", "Doe");
        updatedUser.put("email", "jane.doe@example.com");
        updatedUser.put("password", "newpassword");
        updatedUser.put("phone", "0987654321");
        updatedUser.put("userStatus", 1);

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/user/{username}")
                .withUsername(username)
                .withBody(updatedUser.toJSONString())
                .executePutByUsername();
        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200); // Validate that the pet was updated correctly

        String actualValue = response.jsonPath().getString("message");
       assertEquals("1", actualValue); // Ensure that we get some results

    }

    @Test
    @Order(5)
    public void testLoginUser() {
        String username = "user1";
        String password = "password";

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/user/login")
                .withUsername(username)
                .withPassword(password)
                .executeLoginGet();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200); // Ensure that we get some results
        String actualValue = response.jsonPath().getString("message");
        assertNotNull(actualValue);
    }

    @Test
    @Order(6)
    public void testLogoutUser() {

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/user/logout")
                .executeGet();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200); // Ensure that we get some results
    }

    @Test
    @Order(7)
    public void testDeleteUser() {
        String username = "user1";
        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/user/{username}")
                .withUsername(username)
                .executeDeleteByUsername();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200); // Validate that the pet is deleted successfully

    }

    @Test
    @Order(8)
    public void testCreateUsersWithArrayInput() {
        // Using an array
        JSONObject[] usersArray = new JSONObject[3];

        // Create first user object
        JSONObject user1 = new JSONObject();
        user1.put("id", 1);
        user1.put("username", "user1");
        user1.put("firstName", "John");
        user1.put("lastName", "Doe");
        user1.put("email", "john.doe@example.com");
        user1.put("password", "password");
        user1.put("phone", "1234567890");
        user1.put("userStatus", 1);

        // Create second user object
        JSONObject user2 = new JSONObject();
        user2.put("id", 2);
        user2.put("username", "user2");
        user2.put("firstName", "Alice");
        user2.put("lastName", "Smith");
        user2.put("email", "alice.smith@example.com");
        user2.put("password", "password2");
        user2.put("phone", "1231231234");
        user2.put("userStatus", 1);

        // Create third user object
        JSONObject user3 = new JSONObject();
        user3.put("id", 3);
        user3.put("username", "user3");
        user3.put("firstName", "Bob");
        user3.put("lastName", "Brown");
        user3.put("email", "bob.brown@example.com");
        user3.put("password", "password3");
        user3.put("phone", "9876543210");
        user3.put("userStatus", 1);

        // Add user objects to the users array
        usersArray[0] = user1;
        usersArray[1] = user2;
        usersArray[2] = user3;

        System.out.println(usersArray);

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/user/createWithArray")
                .withBody(Arrays.asList(usersArray).toString())
                .executePost();

        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200);
    }

    @Test
    @Order(9)
    public void testCreateUser() {
        JSONObject userObject = new JSONObject();
        userObject.put("id", 4);
        userObject.put("username", "user4");
        userObject.put("firstName", "Jeanette");
        userObject.put("lastName", "Doesnt");
        userObject.put("email", "jeanette.doesnt@example.com");
        userObject.put("password", "password");
        userObject.put("phone", "1234567890");
        userObject.put("userStatus", 1);

        System.out.println(userObject);

        Response response = PetStoreApiBuilder.builder()
                .withEndpoint("/user")
                .withBody(userObject.toString())
                .executePost();

        userId = response.jsonPath().getString("id");
        // Log the response details for debugging
        response.then().log().all();

        response.then()
                .statusCode(200);
        System.out.println(userId);
    }

    @Test
    @Order(100)
    public void testDeleteAllUser() {
        String[] usernames = {"user1", "user2", "user3", "user4" };

        for (String username : usernames) {
            Response response = PetStoreApiBuilder.builder()
                    .withEndpoint("/user/{username}")
                    .withUsername(username)
                    .executeDeleteByUsername();

            // Log the response details for debugging
            response.then().log().all();

            response.then()
                    .statusCode(200); // Validate that the pet is deleted successfully
        }
    }
}
