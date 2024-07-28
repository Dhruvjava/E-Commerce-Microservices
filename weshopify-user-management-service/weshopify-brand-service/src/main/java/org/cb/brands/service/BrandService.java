package org.cb.brands.service;

import org.cb.brands.rq.BrandRq;
import org.cb.commons.base.datars.BaseDataRs;

public interface BrandService {

    public BaseDataRs createBrand(BrandRq rq);

    public BaseDataRs updateBrand(BrandRq rq);

    public BaseDataRs deleteBrand(String id);

    public BaseDataRs findById(String id);

    public BaseDataRs findByBrandName(String name);

    public BaseDataRs findAllBrand();

    public BaseDataRs findBrandsByCategory(String category);

}
