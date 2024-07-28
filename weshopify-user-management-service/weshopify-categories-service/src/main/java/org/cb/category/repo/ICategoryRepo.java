package org.cb.category.repo;

import org.cb.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepo extends JpaRepository<Category, Integer> {

    public List<Category> findByParentCategoryAndEnabledIsTrueOrderByNameAsc(int parentId);

    public Optional<Category> findByIdAndEnabledIsTrue(Integer id);
}
