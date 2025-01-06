package tests.pet;

import data.PetTestData;
import io.restassured.response.Response;
import matchers.PetResponseMatcher;
import models.request.PetRequest;
import models.response.PetResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.pet.CreatePetService;
import services.pet.UploadPetImageService;
import tests.base.BaseTest;
import java.io.File;
import utils.ResponseHelper;

@Test(groups = "upload")
public class UploadPetImageTests extends BaseTest {
    private final UploadPetImageService uploadPetImageService = new UploadPetImageService();
    private final CreatePetService createPetService = new CreatePetService();
    private Long existingPetId;

    @BeforeMethod
    public void setUp() {
        PetRequest petRequest = PetTestData.createDefaultPet();
        Response createResponse = createPetService.createPet(petRequest);
        existingPetId = createResponse.as(PetResponse.class).getId();
    }

    @Test(description = "Upload pet image - positive")
    public void testUploadPetImage_Success() {
        File imageFile = new File("src/test/resources/test-image.jpg");
        String additionalMetadata = "Test image for pet";

        Response response = uploadPetImageService.uploadPetImage(
            existingPetId,
            imageFile,
            additionalMetadata
        );
        
        ResponseHelper.validateSuccessResponse(response);
    }

    @Test(description = "Upload pet image without file - negative")
    public void testUploadPetImage_NoFile() {
        Response response = uploadPetImageService.uploadPetImage(
            existingPetId,
            null,
            "Test metadata"
        );
        
        ResponseHelper.validateStatusCode(response, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }
} 