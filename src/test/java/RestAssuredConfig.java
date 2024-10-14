import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConfig {

    private static RequestSpecification requestSpec;

    // Private constructor to restrict instantiation
    private RestAssuredConfig() {
    }

    // Initialize the configuration with the base URL and headers
    public static void initConfig() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        RestAssured.requestSpecification = requestSpec;
    }

    // Method to get the initialized request specification
    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            initConfig();
        }
        return requestSpec;
    }
}
