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

    /***
     * Método para buscar una categoría por su ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> searchCategoryById(@PathVariable Long id){
        return categoryService.searchById(id);
    }

    /***
     * Método para crear una nueva categoría
     * @param category
     * @return
     */
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    /***
     * Método para actualizar una categoría existente
     * @param id
     * @param category
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id , @RequestBody Category category){
        return categoryService.update(id, category);
    }
}
