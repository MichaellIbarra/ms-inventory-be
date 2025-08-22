package dev.matichelo.inventory.dto.response;

import dev.matichelo.inventory.model.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoryResponseDTO {
    private MetadataResponseDTO metadata;
    private List<Category> categories;
}
