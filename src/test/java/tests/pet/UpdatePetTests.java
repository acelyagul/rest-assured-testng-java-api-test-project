package tests.pet;

import data.PetTestData;
import io.restassured.response.Response;
import matchers.PetResponseMatcher;
import models.request.PetRequest;
import models.response.PetResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.pet.CreatePetService;
import services.pet.UpdatePetService;
import tests.base.BaseTest;
import utils.ResponseHelper;

@Test(groups = "pet-update")
public class UpdatePetTests extends BaseTest {
    private final UpdatePetService updatePetService = new UpdatePetService();
    private final CreatePetService createPetService = new CreatePetService();

    @Test(description = "Update an existing pet - positive")
    public void testUpdatePet_Positive() {
        PetRequest petRequest = PetTestData.createDefaultPet();
        Response createResponse = createPetService.createPet(petRequest);
        PetResponse createdPet = createResponse.as(PetResponse.class);

        petRequest.setName("Updated " + petRequest.getName());
        Response updateResponse = updatePetService.updatePet(petRequest);
        
        ResponseHelper.validateSuccessResponse(updateResponse);
        PetResponseMatcher.validatePetResponse(updateResponse, mapRequestToResponse(petRequest));
    }

    @Test(description = "Update pet with form data - positive")
    public void testUpdatePetWithFormData_Positive() {
        PetRequest petRequest = PetTestData.createDefaultPet();
        Response createResponse = createPetService.createPet(petRequest);
        PetResponse createdPet = createResponse.as(PetResponse.class);

        Response updateResponse = updatePetService.updatePetWithFormData(
            createdPet.getId(), 
            "Updated Name", 
            "sold"
        );
        
        ResponseHelper.validateSuccessResponse(updateResponse);
    }

    @Test(description = "Update non-existent pet - negative")
    public void testUpdatePet_Negative() {
        PetRequest nonExistentPet = PetTestData.createNonExistentPet();
        Response response = updatePetService.updatePet(nonExistentPet);
        
        ResponseHelper.validateSuccessResponse(response);
        PetResponse updatedPet = response.as(PetResponse.class);
        Assert.assertEquals(updatedPet.getId().longValue(), 999999L);
        Assert.assertEquals(updatedPet.getName(), "Non-existent Pet");
    }
} 