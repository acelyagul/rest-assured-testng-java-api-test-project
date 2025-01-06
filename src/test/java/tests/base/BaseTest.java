package tests.base;

import models.request.PetRequest;
import models.response.PetResponse;
import java.util.stream.Collectors;

public class BaseTest {

    protected PetResponse mapRequestToResponse(PetRequest request) {
        return PetResponse.builder()
                .name(request.getName())
                .status(request.getStatus())
                .category(mapCategory(request.getCategory()))
                .tags(request.getTags() != null ? 
                        request.getTags().stream()
                                .map(this::mapTag)
                                .collect(Collectors.toList()) : 
                        null)
                .photoUrls(request.getPhotoUrls())
                .build();
    }

    protected PetResponse.Category mapCategory(PetRequest.Category category) {
        if (category == null) return null;
        return PetResponse.Category.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    protected PetResponse.Tag mapTag(PetRequest.Tag tag) {
        return PetResponse.Tag.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
} 