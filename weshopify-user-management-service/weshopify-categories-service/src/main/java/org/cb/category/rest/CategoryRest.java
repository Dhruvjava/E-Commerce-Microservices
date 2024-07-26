package org.cb.category.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.category.rq.CategoryRq;
import org.cb.category.service.ICategoryService;
import org.cb.commons.base.datars.BaseDataRs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@Slf4j
@Tag(name = "CATEGORY SERVICE", description = "This is Category Service")
@RequiredArgsConstructor
public class CategoryRest {

    private final ICategoryService categoryService;

    @PostMapping
    @Operation(summary = "CREATE CATEGORY",
                    description = "This functionality is used to create category")
    public ResponseEntity<BaseDataRs> createCategory(@RequestBody CategoryRq rq) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing RESTFull Services [POST : /api/category] -> "));
        return new ResponseEntity<>(categoryService.createCategory(rq), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "UPDATE CATEGORY",
                    description = "This functionality is used to update category")
    public ResponseEntity<BaseDataRs> updateCategory(@RequestBody CategoryRq rq) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing RESTFull Services [PUT : /api/category] -> "));
        return ResponseEntity.ok(categoryService.updateCategory(rq));
    }

    @GetMapping("/{id}")
    @Operation(summary = "FIND ONE CATEGORY",
                    description = "This functionality is used to find one category")
    public ResponseEntity<BaseDataRs> findCategory(@PathVariable Integer id) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing RESTFull Services [GET : /api/category/{id}] -> "));
        return ResponseEntity.ok(categoryService.findOneCategory(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "UPDATE CATEGORY",
                    description = "This functionality is used to update category")
    public ResponseEntity<BaseDataRs> deleteCategory(@PathVariable Integer id) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing RESTFull Services [DELETE : /api/category/{id}] -> "));
        return ResponseEntity.ok(categoryService.deleteOneCategory(id));
    }

    @GetMapping
    @Operation(summary = "UPDATE CATEGORY",
                    description = "This functionality is used to update category")
    public ResponseEntity<BaseDataRs> findAllCategory() {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing RESTFull Services [DELETE : /api/category/{id}] -> "));
        log.info("Retrieving All Categories ->");
        return ResponseEntity.ok(categoryService.findAllCategories());
    }


}
