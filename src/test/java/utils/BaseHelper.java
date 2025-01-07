package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

@Slf4j
public class BaseHelper {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    
    protected static final String PET_PATH = "/pet";
    protected static final String STORE_PATH = "/store";
    protected static final String USER_PATH = "/user";
    
    private static final Logger logger = LoggerFactory.getLogger(BaseHelper.class);

    protected enum Method {
        GET, POST, PUT, DELETE
    }

    protected static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    protected static Response sendRequest(Method method, String path, Object body) {
        RequestSpecification request = given().spec(getBaseSpec());
        
        if (body != null) {
            request.body(body);
        }

        logger.info("Sending {} request to: {}", method, path);
        
        switch (method) {
            case GET:
                return request.get(path);
            case POST:
                return request.post(path);
            case PUT:
                return request.put(path);
            case DELETE:
                return request.delete(path);
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    protected static String buildPath(String basePath, String... pathParams) {
        StringBuilder path = new StringBuilder(basePath);
        for (String param : pathParams) {
            path.append("/").append(param);
        }
        return path.toString();
    }
} 