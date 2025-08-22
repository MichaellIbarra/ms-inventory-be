package dev.matichelo.inventory.service;

import dev.matichelo.inventory.dto.response.CategoryResponseDTO;
import dev.matichelo.inventory.dto.response.MetadataResponseDTO;
import dev.matichelo.inventory.model.Category;
import dev.matichelo.inventory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseDTO> search() {
        try {
            // error intencional para probar el manejo de excepciones
//             if (true) throw new RuntimeException("Intentional error for testing");
            List<Category> categories = categoryRepository.findAll();
            CategoryResponseDTO dto = CategoryResponseDTO.builder()
                            .categories(categories)
                                    .metadata(MetadataResponseDTO.message("Categories retrieved successfully", HttpStatus.OK.value()))
                                            .build();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            CategoryResponseDTO dto = CategoryResponseDTO.builder()
                            .categories(List.of())
                                    .metadata(MetadataResponseDTO.message("An error occurred while retrieving categories: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()))
                                            .build();
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
