package org.cb.brands.client;

import org.cb.commons.base.category.rs.data.CategoryDataRs;
import org.cb.commons.base.datars.BaseDataRs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Category Client", value = "WESHOPIFY-CATEGORIES-SVC")
public interface CategoryClient {

  @GetMapping("/api/categories/{catId}")
  public ResponseEntity<CategoryDataRs> findByCategoryId(@PathVariable("catId") int categoryId);

}
