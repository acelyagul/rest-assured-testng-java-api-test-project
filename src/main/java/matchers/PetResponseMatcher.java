package matchers;

import io.restassured.response.Response;
import models.response.PetResponse;
import org.testng.Assert;
import java.util.List;

public class PetResponseMatcher {
    public static void validatePetResponse(Response response, PetResponse expected) {
        PetResponse actual = response.as(PetResponse.class);
        
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.getStatus(), expected.getStatus());
        
        if (expected.getCategory() != null && actual.getCategory() != null) {
            Assert.assertEquals(actual.getCategory().getName(), 
                expected.getCategory().getName());
        }
    }

    public static void validatePetsListResponse(Response response) {
        List<PetResponse> pets = response.jsonPath().getList("", PetResponse.class);
        Assert.assertFalse(pets.isEmpty());
        pets.forEach(pet -> Assert.assertNotNull(pet.getId()));
    }

    public static void validateInvalidPetResponse(Response response) {
        PetResponse pet = response.as(PetResponse.class);
        Assert.assertNull(pet.getName());
        Assert.assertEquals(pet.getId().longValue(), Long.MAX_VALUE);
    }
} 