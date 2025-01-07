package services.pet;

import io.restassured.response.Response;
import utils.BaseHelper;

public class GetPetService extends BaseHelper {
    
    public Response getPetById(Long petId) {
        return sendRequest(Method.GET, buildPath(PET_PATH, String.valueOf(petId)), null);
    }

    public Response findPetsByStatus(String status) {
        return sendRequest(Method.GET, buildPath(PET_PATH, "findByStatus") + "?status=" + status, null);
    }
} 