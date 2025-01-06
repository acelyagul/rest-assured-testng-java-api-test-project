package services.pet;

import io.restassured.response.Response;
import utils.BaseHelper;
import java.io.File;
import static io.restassured.RestAssured.given;

public class UploadPetImageService extends BaseHelper {
    
    public Response uploadPetImage(Long petId, File imageFile, String additionalMetadata) {
        var request = given()
                .spec(getBaseSpec());

        if (imageFile != null) {
            request.contentType("multipart/form-data")
                  .multiPart("file", imageFile)
                  .formParam("additionalMetadata", additionalMetadata);
        }
        else {
            request.contentType("application/x-www-form-urlencoded")
                  .formParam("additionalMetadata", additionalMetadata);
        }

        return request
                .when()
                .post(buildPath(PET_PATH, String.valueOf(petId), "uploadImage"));
    }
} 