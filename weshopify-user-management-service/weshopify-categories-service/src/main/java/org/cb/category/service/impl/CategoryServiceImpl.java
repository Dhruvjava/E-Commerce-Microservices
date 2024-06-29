package org.cb.category.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.datars.BaseDataRs;
import org.cb.base.rs.ErrorRs;
import org.cb.category.constants.ErrorCodes;
import org.cb.category.constants.MessageCodes;
import org.cb.category.datars.CategoryDataRSs;
import org.cb.category.datars.CategoryDataRs;
import org.cb.category.entity.Category;
import org.cb.category.helper.CategoryHelper;
import org.cb.category.mapper.CategoryMapper;
import org.cb.category.repo.ICategoryRepo;
import org.cb.category.rq.CategoryRq;
import org.cb.category.rs.CategoryRs;
import org.cb.category.service.ICategoryService;
import org.cb.exception.CategoryNotFoundException;
import org.cb.exception.InvalidCategoryException;
import org.cb.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepo categoryRepo;

    private final Messages messages;

    private final ModelMapper mapper;

    @Override
    public BaseDataRs createCategory(CategoryRq rq) {
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing createCategory(CategoryRq) -> "));
        try {
            List<ErrorRs> errors = CategoryHelper.validateCreateCategory(rq, messages);
            Optional.ofNullable(errors).filter(Utils::isNotEmpty).ifPresent(err -> {
                String errorMessage = messages.getErrorProperties(ErrorCodes.EC_INVALID_INPUT);
                log.error(errorMessage);
                throw new InvalidCategoryException(ErrorCodes.EC_INVALID_INPUT, errorMessage, err);
            });
            Category category = CategoryMapper.mapToCategory(rq, mapper);
            categoryRepo.save(category);
            CategoryRs rs = CategoryMapper.mapToCategoryRs(category, mapper);
            String message = messages.getMessageProperties(MessageCodes.MC_CREATED_SUCCESSFULL);
            return new CategoryDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in createCategory(CategoryRq) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs updateCategory(CategoryRq rq) {
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing createCategory(CategoryRq) -> "));
        try {
            List<ErrorRs> errors = CategoryHelper.validateUpdateCategory(rq, messages);
            Optional.ofNullable(errors).filter(Utils::isNotEmpty).ifPresent(err -> {
                String errorMessage = messages.getErrorProperties(ErrorCodes.EC_INVALID_INPUT);
                log.error(errorMessage);
                throw new InvalidCategoryException(ErrorCodes.EC_INVALID_INPUT, errorMessage, err);
            });
            Category category = categoryRepo.findById(rq.getId()).orElseThrow(() -> {
                String errorMessage =
                                messages.getMessageProperties(MessageCodes.MC_NO_RECORD_FOUND);
                return new CategoryNotFoundException(MessageCodes.MC_NO_RECORD_FOUND, errorMessage);
            });
            if (Utils.isNotEmpty(rq.getName()) && !category.getName().equals(rq.getName())) {
                category.setName(rq.getName());
            }
            if (Utils.isNotEmpty(rq.getAlias()) && !category.getAlias().equals(rq.getAlias())) {
                category.setAlias(rq.getAlias());
            }
            if (Utils.isNotEmpty(rq.getImage()) && !category.getImage().equals(rq.getImage())) {
                category.setImage(rq.getImage());
            }
            if (rq.getParentCategory() != null && category.getParentCategory() != rq.getParentCategory()) {
                category.setParentCategory(rq.getParentCategory());
            }
            category.setUpdatedBy("ADMIN");
            category.setUpdatedOn(LocalDateTime.now());
            categoryRepo.save(category);
            CategoryRs rs = CategoryMapper.mapToCategoryRs(category, mapper);
            String message = messages.getMessageProperties(MessageCodes.MC_UPDATED_SUCCESSFULL);
            return new CategoryDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in createCategory(CategoryRq) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findOneCategory(Integer id) {
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing findOneCategory(Integer)"));
        try {
            Optional.of(id).filter(i -> i == 0).ifPresent(err -> {
                String errorMessage = messages.getErrorProperties(ErrorCodes.EC_INVALID_INPUT);
                throw new InvalidCategoryException(ErrorCodes.EC_INVALID_INPUT, errorMessage,
                                List.of(Objects.requireNonNull(Utils.populateErrorRSs(
                                                ErrorCodes.EC_REQUIRED_CATEGORY_ID, messages))));
            });
            Category category = categoryRepo.findById(id).orElse(null);
            return Optional.ofNullable(category).map(cat -> {
                String message = messages.getMessageProperties(
                                MessageCodes.MC_RETRIEVED_SUCCESSFULL);
                CategoryRs rs = CategoryMapper.mapToCategoryRs(cat, mapper);
                return new CategoryDataRs(message, rs);
            }).orElse(new CategoryDataRs(
                            messages.getMessageProperties(MessageCodes.MC_NO_RECORD_FOUND), null));
        } catch (Exception e) {
            log.error("Exception in findOneCategory(Integer) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs deleteOneCategory(Integer id) {
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing deleteOneCategory(Integer)"));
        try {
            Optional.of(id).filter(i -> i == 0).ifPresent(err -> {
                String errorMessage = messages.getErrorProperties(ErrorCodes.EC_INVALID_INPUT);
                throw new InvalidCategoryException(ErrorCodes.EC_INVALID_INPUT, errorMessage,
                                List.of(Objects.requireNonNull(Utils.populateErrorRSs(
                                                ErrorCodes.EC_REQUIRED_CATEGORY_ID, messages))));
            });
            categoryRepo.deleteById(id);
            CategoryDataRSs dataRSs = (CategoryDataRSs) findAllCategories();
            return Optional.ofNullable(dataRSs.getCategories()).filter(Utils::isNotEmpty)
                            .map(cats -> {
                                String message = messages.getMessageProperties(
                                                MessageCodes.MC_DELETED_SUCCESSFULL);
                                return new CategoryDataRSs(message, cats);
                            }).orElse(new CategoryDataRSs(messages.getMessageProperties(
                                            MessageCodes.MC_DELETED_SUCCESSFULL),
                                            Collections.emptyList()));
        } catch (Exception e) {
            log.error("Exception in deleteOneCategory(Integer) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findAllCategories() {
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing findAllCategories() -> "));
        try {
            List<Category> categories = categoryRepo.findAll();
            return Optional.of(categories).map(cats -> {
                String message = messages.getMessageProperties(
                                MessageCodes.MC_RETRIEVED_SUCCESSFULL);
                List<CategoryRs> rs = cats.stream()
                                .map(cat -> CategoryMapper.mapToCategoryRs(cat, mapper)).toList();
                return new CategoryDataRSs(message, rs);
            }).orElse(new CategoryDataRSs(
                            messages.getMessageProperties(MessageCodes.MC_NO_RECORD_FOUND),
                            Collections.emptyList()));
        } catch (Exception e) {
            log.error("Exception in findAllCategories() -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs searchCategories(String searchText) {
        return null;
    }

    @Override
    public BaseDataRs findAllChildCategories(Integer parentCatId) {
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing findAllChildCategories(Integer) -> "));
        try {
            Optional.of(parentCatId).filter(parent -> parent <= 0).ifPresent(err -> {
                String errorMessage = messages.getErrorProperties(ErrorCodes.EC_INVALID_INPUT);
                throw new InvalidCategoryException(ErrorCodes.EC_INVALID_INPUT, errorMessage,
                                List.of(Objects.requireNonNull(Utils.populateErrorRSs(
                                                ErrorCodes.EC_REQUIRED_CATEGORY_PARENT_ID,
                                                messages))));
            });
            List<Category> childCategories =
                            categoryRepo.findByParentCategoryAndEnabledIsTrueOrderByNameAsc(
                                            parentCatId);
            return Optional.of(childCategories).map(child -> {
                String message = messages.getMessageProperties(
                                MessageCodes.MC_RETRIEVED_SUCCESSFULL);
                List<CategoryRs> rs = child.stream()
                                .map(cat -> CategoryMapper.mapToCategoryRs(cat, mapper)).toList();
                return new CategoryDataRSs(message, rs);
            }).orElse(new CategoryDataRSs(
                            messages.getMessageProperties(MessageCodes.MC_NO_RECORD_FOUND)));
        } catch (Exception e) {
            log.error("Exception in findAllChildCategories(Integer) -> {0}", e);
            throw e;
        }
    }
}
