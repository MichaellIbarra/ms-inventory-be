package dev.matichelo.inventory.service;

import dev.matichelo.inventory.dto.response.CategoryResponseDTO;
import dev.matichelo.inventory.model.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<CategoryResponseDTO> search();
    ResponseEntity<CategoryResponseDTO> searchById(Long id);
    ResponseEntity<CategoryResponseDTO> save(Category category);

}
