package tests.pet;

import data.PetTestData;
import io.restassured.response.Response;
import matchers.PetResponseMatcher;
import models.request.PetRequest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import services.pet.CreatePetService;
import tests.base.BaseTest;
import utils.ResponseHelper;

@Test(groups = "pet-create")
public class CreatePetTests extends BaseTest {
    private final CreatePetService createPetService = new CreatePetService();

    @Test(description = "Create a new pet - positive")
    public void testCreatePet_Positive() {
        PetRequest petRequest = PetTestData.createDefaultPet();
        Response response = createPetService.createPet(petRequest);
        
        ResponseHelper.validateSuccessResponse(response);
        PetResponseMatcher.validatePetResponse(response, mapRequestToResponse(petRequest));
    }

    @Test(description = "Create pet with invalid input - negative")
    public void testCreatePet_Negative() {
        PetRequest invalidPet = PetTestData.createInvalidPet();
        Response response = createPetService.createPet(invalidPet);
        
        ResponseHelper.validateErrorResponse(response, HttpStatus.SC_INTERNAL_SERVER_ERROR); 
    }
} 