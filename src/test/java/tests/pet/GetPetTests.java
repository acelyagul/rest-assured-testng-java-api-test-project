package tests.pet;

import data.PetTestData;
import io.restassured.response.Response;
import matchers.PetResponseMatcher;
import models.request.PetRequest;
import models.response.PetResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import services.pet.CreatePetService;
import services.pet.GetPetService;
import tests.base.BaseTest;
import utils.ResponseHelper;

@Test(groups = "pet-get")
public class GetPetTests extends BaseTest {
    private final GetPetService getPetService = new GetPetService();
    private final CreatePetService createPetService = new CreatePetService();

    @Test(description = "Get pet by ID - positive")
    public void testGetPetById_Positive() {
        PetRequest petRequest = PetTestData.createDefaultPet();
        Response createResponse = createPetService.createPet(petRequest);
        PetResponse createdPet = createResponse.as(PetResponse.class);

        Response response = getPetService.getPetById(createdPet.getId());
        
        ResponseHelper.validateSuccessResponse(response);
        PetResponseMatcher.validatePetResponse(response, createdPet);
    }

    @Test(description = "Find pets by status - positive")
    public void testFindPetsByStatus_Positive() {
        PetRequest petRequest = PetTestData.createDefaultPet();
        createPetService.createPet(petRequest);

        Response response = getPetService.findPetsByStatus(petRequest.getStatus());
        ResponseHelper.validateSuccessResponse(response);
        PetResponseMatcher.validatePetsListResponse(response);
    }

    @Test(description = "Get pet with non-existent ID - negative")
    public void testGetPetById_Negative() {
        Response response = getPetService.getPetById(-1L);
        ResponseHelper.validateErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }
} 