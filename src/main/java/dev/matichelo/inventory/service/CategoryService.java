package dev.matichelo.inventory.service;

import dev.matichelo.inventory.dto.response.CategoryResponseDTO;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<CategoryResponseDTO> search();

}
