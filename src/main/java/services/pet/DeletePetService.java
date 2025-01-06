package services.pet;

import io.restassured.response.Response;
import utils.BaseHelper;

public class DeletePetService extends BaseHelper {

    public Response deletePet(Long petId) {
        return sendRequest(Method.DELETE, buildPath(PET_PATH, String.valueOf(petId)), null);
    }
} 