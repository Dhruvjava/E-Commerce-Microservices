package org.cb.category.service;

import org.cb.category.rq.CategoryRq;
import org.cb.commons.base.datars.BaseDataRs;

public interface ICategoryService {

    public BaseDataRs createCategory(CategoryRq rq);

    public BaseDataRs updateCategory(CategoryRq rq);

    public BaseDataRs findOneCategory(Integer id);

    public BaseDataRs deleteOneCategory(Integer id);

    public BaseDataRs findAllCategories();

    public BaseDataRs searchCategories(String searchText);

    public BaseDataRs findAllChildCategories(Integer parentCatId);

}
