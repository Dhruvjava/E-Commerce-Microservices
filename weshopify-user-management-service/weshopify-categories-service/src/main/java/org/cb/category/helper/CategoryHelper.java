package org.cb.category.helper;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.category.constants.ErrorCodes;
import org.cb.category.rq.CategoryRq;
import org.cb.commons.base.rs.ErrorRs;
import org.cb.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CategoryHelper {

    private CategoryHelper() {
    }

    public static List<ErrorRs> validateCreateCategory(CategoryRq rq, Messages messages) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing validateCreateCategory(CategoryRq) -> "));
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (Utils.isEmpty(rq.getImage())) {
                log.error(ErrorCodes.EC_REQUIRED_CATEGORY_IMAGE);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_CATEGORY_IMAGE, messages));
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateCreatCategory(CategoryRq) -> {0}", e);
            throw e;
        }
    }

    public static List<ErrorRs> validateUpdateCategory(CategoryRq rq, Messages messages) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing validateUpdateCategory(CategoryRq) -> "));
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getId() == null && rq.getId() <= 0) {
                log.error(ErrorCodes.EC_REQUIRED_CATEGORY_ID);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_CATEGORY_ID, messages));
            }
            List<ErrorRs> errorRs = validateCreateCategory(rq, messages);
            if (Utils.isNotEmpty(errorRs)) {
                errors.addAll(errorRs);
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateUpdateCategory(CategoryRq) -> {0}", e);
            throw e;
        }
    }
}
