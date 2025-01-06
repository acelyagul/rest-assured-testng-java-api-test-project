package services.pet;

import io.restassured.response.Response;
import models.request.PetRequest;
import utils.BaseHelper;

public class CreatePetService extends BaseHelper {

    public Response createPet(PetRequest request) {
        return sendRequest(Method.POST, PET_PATH, request);
    }
} 