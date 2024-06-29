package org.cb.category.repo;

import org.cb.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepo extends JpaRepository<Category, Integer> {

    public List<Category> findByParentCategoryAndEnabledIsTrueOrderByNameAsc(int parentId);

}
