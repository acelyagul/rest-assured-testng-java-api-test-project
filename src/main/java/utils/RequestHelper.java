package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestHelper {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_ENDPOINT = "/pet";

    public static RequestSpecification getBaseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }


    public static RequestSpecification createPostRequest(Object requestBody) {
        return given()
                .spec(getBaseRequestSpec())
                .body(requestBody);
    }

    public static RequestSpecification createGetRequest() {
        return given()
                .spec(getBaseRequestSpec());
    }

    public static RequestSpecification createPutRequest(Object requestBody) {
        return given()
                .spec(getBaseRequestSpec())
                .body(requestBody);
    }


    public static RequestSpecification createDeleteRequest() {
        return given()
                .spec(getBaseRequestSpec());
    }


    public static String createPath(String... pathParams) {
        StringBuilder path = new StringBuilder(PET_ENDPOINT);
        for (String param : pathParams) {
            path.append("/").append(param);
        }
        return path.toString();
    }


    public static RequestSpecification addQueryParam(RequestSpecification request, String paramName, Object paramValue) {
        return request.queryParam(paramName, paramValue);
    }


    public static RequestSpecification addHeader(RequestSpecification request, String headerName, String headerValue) {
        return request.header(headerName, headerValue);
    }
} 