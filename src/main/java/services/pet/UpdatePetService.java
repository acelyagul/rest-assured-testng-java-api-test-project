package services.pet;

import io.restassured.response.Response;
import models.request.PetRequest;
import utils.BaseHelper;
import static io.restassured.RestAssured.given;

public class UpdatePetService extends BaseHelper {
    
    public Response updatePet(PetRequest request) {
        return sendRequest(Method.PUT, PET_PATH, request);
    }

    public Response updatePetWithFormData(Long petId, String name, String status) {
        return given()
                .spec(getBaseSpec())
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", name)
                .formParam("status", status)
                .when()
                .post(buildPath(PET_PATH, String.valueOf(petId)));
    }
} 