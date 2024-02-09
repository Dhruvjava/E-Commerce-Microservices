package org.cb.category.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.datars.BaseDataRs;
import org.cb.base.rs.ErrorRs;
import org.cb.category.constants.ErrorCodes;
import org.cb.category.helper.CategoryHelper;
import org.cb.category.repo.ICategoryRepo;
import org.cb.category.rq.CategoryRq;
import org.cb.category.service.ICategoryService;
import org.cb.exception.InvalidCategoryException;
import org.cb.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
            List<ErrorRs> errors = CategoryHelper.validateCreatCategory(rq, messages);
            Optional.ofNullable(errors).filter(Utils::isNotEmpty).ifPresent(err -> {
                String errorMessage = messages.getErrorProperties(ErrorCodes.EC_INVALID_INPUT);
                log.error(errorMessage);
                throw new InvalidCategoryException(ErrorCodes.EC_INVALID_INPUT, errorMessage, err);
            });
        } catch (Exception e) {
            log.error("Exception in createCategory(CategoryRq) -> {}", e);
            throw e;
        }
        return null;
    }

    @Override
    public BaseDataRs updateCategory(CategoryRq rq) {
        return null;
    }

    @Override
    public BaseDataRs findOneCategory(Integer id) {
        return null;
    }

    @Override
    public BaseDataRs deleteOneCategory(Integer id) {
        return null;
    }

    @Override
    public BaseDataRs findAllCategories() {
        return null;
    }

    @Override
    public BaseDataRs searchCategories(String searchText) {
        return null;
    }

    @Override
    public BaseDataRs findAllChildCategories(Integer parentCatId) {
        return null;
    }
}
