package org.cb.category.helper;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.rs.ErrorRs;
import org.cb.category.constants.ErrorCodes;
import org.cb.category.rq.CategoryRq;
import org.cb.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CategoryHelper {

    private CategoryHelper() {
    }

    public static List<ErrorRs> validateCreatCategory(CategoryRq rq, Messages messages) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug(
                        "Executing validateCreateCategory(CategoryRq) -> "));
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (Utils.isEmpty(rq.getName())) {
                log.error(ErrorCodes.EC_REQUIRED_CATEGORY_NAME);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_CATEGORY_NAME, messages));
            }
            if (Utils.isEmpty(rq.getAlias())) {
                log.error(ErrorCodes.EC_REQUIRED_CATEGORY_ALIAS);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_CATEGORY_ALIAS, messages));
            }
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
}
