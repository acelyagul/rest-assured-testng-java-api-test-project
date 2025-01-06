package tests.pet;

import data.PetTestData;
import io.restassured.response.Response;
import matchers.PetResponseMatcher;
import models.request.PetRequest;
import models.response.PetResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import services.pet.CreatePetService;
import services.pet.DeletePetService;
import services.pet.GetPetService;
import tests.base.BaseTest;
import utils.ResponseHelper;

@Test(groups = "pet-delete")
public class DeletePetTests extends BaseTest {
    private final DeletePetService deletePetService = new DeletePetService();
    private final CreatePetService createPetService = new CreatePetService();
    private final GetPetService getPetService = new GetPetService();

    @Test(description = "Delete an existing pet - positive")
    public void testDeletePet_Positive() {
        PetRequest petRequest = PetTestData.createDefaultPet();
        Response createResponse = createPetService.createPet(petRequest);
        PetResponse createdPet = createResponse.as(PetResponse.class);

        Response deleteResponse = deletePetService.deletePet(createdPet.getId());
        ResponseHelper.validateSuccessResponse(deleteResponse);

        Response getResponse = getPetService.getPetById(createdPet.getId());
        ResponseHelper.validateErrorResponse(getResponse, HttpStatus.SC_NOT_FOUND);
    }

    @Test(description = "Delete non-existent pet - negative")
    public void testDeletePet_Negative() {
        Response response = deletePetService.deletePet(-1L);
        ResponseHelper.validateErrorResponse(response, HttpStatus.SC_NOT_FOUND);
    }
} 