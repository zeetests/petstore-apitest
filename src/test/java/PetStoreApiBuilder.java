import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetStoreApiBuilder {

    private String endpoint;
    private Object body;
    private String petId;
    private String id;
    private String orderId;
    private String username;
    private String password;
    private String name;
    private String[] tags;
    private String[] status;

    private PetStoreApiBuilder() {
    }

    public static PetStoreApiBuilder builder() {
        return new PetStoreApiBuilder();
    }

    public PetStoreApiBuilder withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public PetStoreApiBuilder withTags(String[] tags) {
        this.tags = tags;
        return this;
    }

    public PetStoreApiBuilder withBody(Object body) {
        this.body = body;
        return this;
    }

    public PetStoreApiBuilder withPetId(String petId) {
        this.petId = petId;
        return this;
    }

    public PetStoreApiBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PetStoreApiBuilder withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }
    public PetStoreApiBuilder withUsername(String username) {
        this.username = username;
        return this;
    }
    public PetStoreApiBuilder withPassword(String username) {
        this.password = password;
        return this;
    }

    public PetStoreApiBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PetStoreApiBuilder withStatus(String[] status) {
        this.status = status;
        return this;
    }

    public Response executePost() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .body(body)
                .when()
                .post(endpoint);
    }

    public Response executePostWithAPIKey() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .header("api_key", "special-key")
                .body(body)
                .when()
                .post(endpoint);
    }

    public Response executePut() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .body(body)
                .when()
                .put(endpoint);
    }

    public Response executePutByUsername() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                //.queryParam("username", username)
                //.pathParam("username", username)
                .body(body)
                .when()
                .put(endpoint.replace("{username}", username));
    }

    public Response executeGetByStatus() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .queryParam("status", status)
                .when()
                .get(endpoint);
    }

    public Response executeGetByTags() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .queryParam("tags", tags)
                .when()
                .get(endpoint);
    }

    public Response executeGet() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .when()
                .get(endpoint);
    }

    public Response executeLoginGet() {
        return given()
                .queryParam("username", username)
                .queryParam("password", password)
                .spec(RestAssuredConfig.getRequestSpec())
                .when()
                .get(endpoint);
    }

    public Response executeGetByPetId() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .queryParam("petId", petId)
                .when()
                .get(endpoint.replace("{petId}", petId));
    }

    public Response executeGetByPetIdWithAPIKey() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .header("api_key", "special-key")
                .queryParam("petId", petId)
                .when()
                .get(endpoint.replace("{petId}", petId));
    }

    public Response executeGetByOrderId() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .queryParam("orderId", orderId)
                .when()
                .get(endpoint.replace("{orderId}", orderId));
    }

    public Response executePostWithFileUpload(String filePath, String additionalMetadata) {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .multiPart("file", filePath)
                .contentType("multipart/form-data") // Set content type
                .multiPart("additionalMetadata", additionalMetadata)
                .when()
                .post(endpoint.replace("{petId}", petId));
    }

    public Response executePostWithFileUploadWithAPIKey(String filePath, String additionalMetadata) {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .multiPart("file", filePath)
                .contentType("multipart/form-data") // Set content type
                .multiPart("additionalMetadata", additionalMetadata)
                .header("api_key", "special-key")
                .when()
                .post(endpoint.replace("{petId}", petId));
    }

    public Response executeDelete() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .when()
                .delete(endpoint.replace("{id}", id));
    }

    public Response executeDeleteByUsername() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                //.pathParam("username", username)
                .when()
                .delete(endpoint.replace("{username}", username));
    }

    public Response executeGetByUsername() {
        return given()
                .spec(RestAssuredConfig.getRequestSpec())
                .queryParam("username", username)
                .when()
                .get(endpoint.replace("{username}", username));
    }
    public Response executePostWithFormData(String name, String status) {
        return given()
                .contentType("application/x-www-form-urlencoded") // Set content type
                .formParam("name", name)
                .formParam("status", status)
                .log().all() // Log the complete request details, including URI and parameters
                .when()
                .post(endpoint.replace("{petId}", petId));
    }


}
