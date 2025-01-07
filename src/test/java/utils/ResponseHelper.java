package utils;

import io.restassured.response.Response;
import models.request.PetRequest;
import models.response.PetResponse;
import org.testng.Assert;

public class ResponseHelper {
    
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, 
            "Status code mismatch! Expected: " + expectedStatusCode + 
            ", Actual: " + response.getStatusCode());
    }

    public static void validateSuccessResponse(Response response) {
        validateStatusCode(response, 200);
        Assert.assertNotNull(response.getBody());
    }

    public static void validateErrorResponse(Response response, int expectedErrorCode) {
        validateStatusCode(response, expectedErrorCode);
        
        if (response.getContentType() != null && 
            response.getContentType().contains("application/json")) {
            if (response.getBody().asString().length() > 0) {
                PetResponse.ApiResponse errorResponse = response.as(PetResponse.ApiResponse.class);
                Assert.assertNotNull(errorResponse.getMessage(), "Error message cannot be null!");
            }
        }
    }
} 