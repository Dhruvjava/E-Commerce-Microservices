package org.cb.brands.mapper;

import lombok.extern.slf4j.Slf4j;
import org.cb.brands.entity.Brand;
import org.cb.brands.rq.BrandRq;
import org.cb.brands.rs.BrandRs;
import org.cb.util.LocalDateTimeUtils;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Slf4j
public class BrandMapper {

    private BrandMapper(){}

    public static Brand mapToBrand(BrandRq rq, ModelMapper mapper) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug("Executing mapToBrand(BrandRq) ->"));
        try {
            return mapper.map(rq, Brand.class);
        } catch (Exception e) {
            log.error("Exception in mapToBrand(BrandRq) -> {}", e.getMessage());
            return null;
        }
    }

    public static BrandRs mapToBrandRs(Brand brand, ModelMapper mapper) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug("Executing mapToBrandRs(Brand, ModelMapper)->"));
        try {
            BrandRs rs = mapper.map(brand, BrandRs.class);
            if (brand.getCreatedon() != null) {
                String createdOn = LocalDateTimeUtils.convertLdtToString(brand.getCreatedon(), LocalDateTimeUtils.dd_MMM_yyyy_HH_mm_ss);
                rs.setCreatedOn(createdOn);
            }
            if (brand.getUpdatedon() != null) {
                String updateOn = LocalDateTimeUtils.convertLdtToString(brand.getUpdatedon(), LocalDateTimeUtils.dd_MMM_yyyy_HH_mm_ss);
                rs.setUpdatedOn(updateOn);
            }
            return rs;
        } catch (Exception e) {
            log.error("Exception in mapToBrandRs(Brand brand, ModelMapper mapper) -> {}", e.getMessage());
            return null;
        }
    }
}
