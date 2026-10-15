package br.com.ebac.memelandia.feign;

import br.com.ebac.memelandia.dto.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "category", url = "${meme.service.url}")
public interface CategoryFeign {
    @PostMapping("/category")
    void createCategory(@RequestBody CategoryDTO category);

    @PutMapping("/category/{categoryID}")
    void updateCategory(@RequestBody CategoryDTO category, @PathVariable(name = "categoryID") Long categoryID);

    @DeleteMapping("/category/{categoryID}")
    void deleteCategory(@PathVariable(name = "categoryID") Long categoryID);

    @GetMapping("/category")
    List<CategoryDTO> listCategory();
}
