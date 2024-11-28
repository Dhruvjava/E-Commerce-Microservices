package org.cb.category.mapper;

import lombok.extern.slf4j.Slf4j;
import org.cb.category.entity.Category;
import org.cb.category.rq.CategoryRq;
import org.cb.commons.base.category.rs.CategoryRs;
import org.cb.utils.LocalDateTimeUtils;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class CategoryMapper {

    public static Category mapToCategory(CategoryRq rq, ModelMapper mapper) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing mapToCategory(CategoryRq, ModelMapper) ->"));
        try {
            Category category = mapper.map(rq, Category.class);
            category.setCreatedBy("ADMIN");
            category.setUpdatedBy("ADMIN");
            category.setCreatedOn(LocalDateTime.now());
            category.setUpdatedOn(LocalDateTime.now());
            return category;
        } catch (Exception e) {
            log.error("Exception in mapToCategory(CategoryRq , ModelMapper) ->");
            throw e;
        }
    }

    public static Category mapToUpdateCategory(CategoryRq rq, ModelMapper mapper) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing mapToCategory(CategoryRq, ModelMapper) ->"));
        try {
            Category category = mapper.map(rq, Category.class);
            category.setUpdatedBy("ADMIN");
            category.setUpdatedOn(LocalDateTime.now());
            return category;
        } catch (Exception e) {
            log.error("Exception in mapToCategory(CategoryRq , ModelMapper) ->");
            throw e;
        }
    }

    public static CategoryRs mapToCategoryRs(Category entity, ModelMapper mapper) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing mapToCategory(CategoryRq, ModelMapper) ->"));
        try {
            CategoryRs category = mapper.map(entity, CategoryRs.class);
            category.setCreatedOn(LocalDateTimeUtils.convertLdtToString(entity.getCreatedOn(),
                            LocalDateTimeUtils.dd_MMM_yyyy_HH_00));
            category.setUpdatedOn(LocalDateTimeUtils.convertLdtToString(entity.getUpdatedOn(),
                            LocalDateTimeUtils.dd_MMM_yyyy_HH_00));
            return category;
        } catch (Exception e) {
            log.error("Exception in mapToCategory(CategoryRq , ModelMapper) ->");
            throw e;
        }
    }
}
