package dev.matichelo.inventory.controller;

import dev.matichelo.inventory.dto.response.CategoryResponseDTO;
import dev.matichelo.inventory.model.Category;
import dev.matichelo.inventory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRest {

    private final CategoryService categoryService;


    /***
     * Método para buscar todas las categorías
     * @return
     */
    @GetMapping
    public ResponseEntity<CategoryResponseDTO> searchCategories(){
        // conversión si solo retornara la lista de categorías
        // List<Category> categories = categoryService.search();
        // return ResponseEntity.ok().body(new CategoryResponseDTO(categories));
        return  categoryService.search();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> searchCategoryById(@PathVariable Long id){
        return categoryService.searchById(id);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody Category category){
        return categoryService.save(category);
    }
}
