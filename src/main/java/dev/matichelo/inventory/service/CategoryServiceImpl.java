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
import java.util.Optional;

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
                            .data(categories)
                                    .metadata(MetadataResponseDTO.message("Categories retrieved successfully", HttpStatus.OK.value()))
                                            .build();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            CategoryResponseDTO dto = CategoryResponseDTO.builder()
                            .data(List.of())
                                    .metadata(MetadataResponseDTO.message("An error occurred while retrieving categories: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()))
                                            .build();
            return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseDTO> searchById(Long id) {
      try {
//          Category category = categoryRepository.findById(id).orElseThrow( () -> new RuntimeException("Category not found with id" + id ));
          Optional<Category>  categoryOptional= categoryRepository.findById(id);
          if (categoryOptional.isPresent()) {
              CategoryResponseDTO dto = CategoryResponseDTO.builder()
                      .data(List.of(categoryOptional.get()))
                      .metadata(MetadataResponseDTO.message("Category retrieved successfully", HttpStatus.OK.value()))
                      .build();
              return new ResponseEntity<>(dto, HttpStatus.OK);
          } else {
              CategoryResponseDTO dto = CategoryResponseDTO.builder()
                      .data(List.of())
                      .metadata(MetadataResponseDTO.message("Category not found with id: " + id, HttpStatus.NOT_FOUND.value()))
                      .build();
                return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
          }
      } catch (Exception e) {
          CategoryResponseDTO dto = CategoryResponseDTO.builder()
                  .data(List.of())
                  .metadata(MetadataResponseDTO.message("An error occurred while retrieving the category: " +  e.getMessage(),
                          HttpStatus.INTERNAL_SERVER_ERROR.value()))
                  .build();
          return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseDTO> save(Category category) {

            Category categorySave = categoryRepository.save(category);
            if(categorySave != null){
                CategoryResponseDTO dto = CategoryResponseDTO.builder()
                        .data(List.of(categorySave))
                        .metadata(MetadataResponseDTO.message("Category saved successfully", HttpStatus.OK.value()))
                        .build();
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            } else{
                CategoryResponseDTO dto = CategoryResponseDTO.builder()
                        .data(List.of())
                        .metadata(MetadataResponseDTO.message("Category could not be saved", HttpStatus.BAD_REQUEST.value()))
                        .build();
                return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
            }

    }

    @Override
    public ResponseEntity<CategoryResponseDTO> update(Long id, Category category) {

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category categoryUpdate = categoryOptional.get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.setDescription(category.getDescription());
            categoryRepository.save(categoryUpdate);
            CategoryResponseDTO dto = CategoryResponseDTO.builder()
                    .data(List.of(categoryUpdate))
                    .metadata(MetadataResponseDTO.message("Category updated successfully", HttpStatus.OK.value()))
                    .build();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            CategoryResponseDTO dto = CategoryResponseDTO.builder()
                    .data(List.of())
                    .metadata(MetadataResponseDTO.message("Category not found with id: " + id, HttpStatus.NOT_FOUND.value()))
                    .build();
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseDTO> delete(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            categoryRepository.deleteById(id);
            CategoryResponseDTO dto = CategoryResponseDTO.builder()
                    .data(List.of())
                    .metadata(MetadataResponseDTO.message("Category deleted successfully", HttpStatus.OK.value()))
                    .build();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            CategoryResponseDTO dto = CategoryResponseDTO.builder()
                    .data(List.of())
                    .metadata(MetadataResponseDTO.message("Category not found with id: " + id, HttpStatus.NOT_FOUND.value()))
                    .build();
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }
    }


}