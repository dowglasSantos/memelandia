package br.com.ebac.memelandia.feign;

import br.com.ebac.memelandia.dto.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "category", url = "http://localhost:8082/category")
public interface CategoryFeign {
    @PostMapping()
    void createCategory(@RequestBody CategoryDTO category);

    @PutMapping("/{categoryID}")
    void updateCategory(@RequestBody CategoryDTO category, @PathVariable(name = "categoryID") Long categoryID);

    @DeleteMapping("/{categoryID}")
    void deleteCategory(@PathVariable(name = "categoryID") Long categoryID);

    @GetMapping()
    List<CategoryDTO> listCategory();
}
