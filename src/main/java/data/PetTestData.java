package data;

import com.github.javafaker.Faker;
import models.request.PetRequest;
import java.util.Collections;

public class PetTestData {
    private static final Faker faker = new Faker();

    public static PetRequest createDefaultPet() {
        return PetRequest.builder()
                .id(String.valueOf(faker.number().randomNumber()))
                .name(faker.dog().name())
                .status("available")
                .category(createCategory())
                .tags(Collections.singletonList(createTag()))
                .photoUrls(Collections.singletonList(faker.internet().url()))
                .build();
    }

    private static PetRequest.Category createCategory() {
        return PetRequest.Category.builder()
                .id(faker.number().randomNumber())
                .name(faker.animal().name())
                .build();
    }

    private static PetRequest.Tag createTag() {
        return PetRequest.Tag.builder()
                .id(faker.number().randomNumber())
                .name(faker.lorem().word())
                .build();
    }

    public static PetRequest createInvalidPet() {
        return PetRequest.builder()
                .id("121212123849843843980")
                .build();
    }

    public static PetRequest createNonExistentPet() {
        return PetRequest.builder()
                .id(String.valueOf(999999L))
                .name("Non-existent Pet")
                .status("available")
                .build();
    }
}