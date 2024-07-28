package org.cb.brands.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.brands.constants.ErrorCodes;
import org.cb.brands.constants.MessageCodes;
import org.cb.brands.data.rs.BrandDataRs;
import org.cb.brands.entity.Brand;
import org.cb.brands.mapper.BrandMapper;
import org.cb.brands.repo.BrandRepo;
import org.cb.brands.rq.BrandRq;
import org.cb.brands.rs.BrandRs;
import org.cb.commons.base.datars.BaseDataRs;
import org.cb.exception.BrandException;
import org.cb.exception.BrandNotFoundException;
import org.cb.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepo brandRepo;

    private final ModelMapper mapper;

    private final Messages messages;
    private final View error;

    @Override
    public BaseDataRs createBrand(BrandRq rq) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug("Executing createBrand(BrandRq) -> "));
        try {
            Brand brand = BrandMapper.mapToBrand(rq, mapper);
            Optional.ofNullable(brand).orElseThrow(() -> {
                String errMsg = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                return new BrandException(ErrorCodes.EC_INVALID_INPUT, errMsg);
            });
            brand = brandRepo.save(brand);
            BrandRs rs = BrandMapper.mapToBrandRs(brand, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_CREATED_SUCCESSFUL);
            return new BrandDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in createBrand(BrandRq) -> {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public BaseDataRs updateBrand(BrandRq rq) {
        Optional.of(log.isDebugEnabled()).ifPresent(l -> log.debug("Executing updateBrand(BrandRq) -> "));
        try {
            Optional.ofNullable(rq.getId()).orElseThrow(() -> {
                String errMsg = messages.getErrorProperty(ErrorCodes.EC_REQUIRED_BRAND_ID);
                return new BrandException(ErrorCodes.EC_REQUIRED_BRAND_ID, errMsg);
            });
            Brand brand = brandRepo.findById(rq.getId()).orElseThrow(() -> {
                String errMsg = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                return new BrandNotFoundException(ErrorCodes.EC_INVALID_INPUT, errMsg, List.of(Objects.requireNonNull(Utils.populateErrorRSs(ErrorCodes.EC_BRAND_NOT_FOUND, messages))));
            });
            if (Utils.isNotEmpty(rq.getName())) {
                brand.setName(rq.getName());
            }
            if (Utils.isNotEmpty(rq.getCategories())) {
                brand.setCategories(rq.getCategories());
            }
            brand = brandRepo.save(brand);
            String message = messages.getMessageProperty(MessageCodes.MC_UPDATED_SUCCESSFUL);
            BrandRs rs = BrandMapper.mapToBrandRs(brand, mapper);
            return new BrandDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in updateBrand(BrandRq) -> {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public BaseDataRs deleteBrand(String id) {
        return null;
    }

    @Override
    public BaseDataRs findById(String id) {
        return null;
    }

    @Override
    public BaseDataRs findByBrandName(String name) {
        return null;
    }

    @Override
    public BaseDataRs findAllBrand() {
        return null;
    }

    @Override
    public BaseDataRs findBrandsByCategory(String category) {
        return null;
    }
}
