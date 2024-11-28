package org.cb.brands.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.brands.client.CategoryClient;
import org.cb.brands.constants.ErrorCodes;
import org.cb.brands.constants.MessageCodes;
import org.cb.brands.data.rs.BrandDataRSs;
import org.cb.brands.data.rs.BrandDataRs;
import org.cb.brands.entity.Brand;
import org.cb.brands.mapper.BrandMapper;
import org.cb.brands.repo.BrandRepo;
import org.cb.brands.rq.BrandRq;
import org.cb.brands.rs.BrandRs;
import org.cb.brands.service.BrandService;
import org.cb.commons.base.category.rs.data.CategoryDataRs;
import org.cb.commons.base.datars.BaseDataRs;
import org.cb.exception.BrandException;
import org.cb.exception.BrandNotFoundException;
import org.cb.util.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepo brandRepo;

    private final ModelMapper mapper;

    private final Messages messages;

    private final CategoryClient categoryClient;

    private final ObjectMapper objectMapper;

    @Override
    public BaseDataRs createBrand(BrandRq rq) {
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing createBrand(BrandRq) -> "));
        try {
            Brand brand = BrandMapper.mapToBrand(rq, mapper);
            Set<String> categoriesRef = Optional.ofNullable(rq.getCategories()).filter(Utils::isEmpty).stream().flatMap(
                Collection::stream).map(String::strip).map(this::processCategory).filter(Optional::isPresent).map(Optional::get).filter(Objects::nonNull).collect(Collectors.toSet());
            if (Utils.isNotEmpty(categoriesRef)){
                brand.setCategories(categoriesRef);
            }else {
                brand.setCategories(Collections.emptySet());
            }
            brand = Optional.ofNullable(brand).orElseThrow(() ->
                 new BrandException(ErrorCodes.EC_INVALID_INPUT, messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT)));
            brand = brandRepo.save(brand);
            BrandRs rs = BrandMapper.mapToBrandRs(brand, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_NO_RECORD_FOUND);
            return new BrandDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in createBrand(BrandRq) -> {}", e.getMessage());
            throw e;
        }
    }
    private Optional<String> processCategory(String cat){
        Optional.ofNullable(log).filter(Logger::isDebugEnabled).ifPresent(logger -> logger.debug("Executing processCategory(String) ->"));
        ResponseEntity<CategoryDataRs> catResponse = categoryClient.findByCategoryId(Integer.parseInt(cat));
        if (catResponse.getStatusCode().is2xxSuccessful() && catResponse.getBody() != null) {
            try {
                return objectMapper.writeValueAsString(catResponse.getBody().getCategory()).describeConstable();
            }catch (Exception e){
                return switch(e){
                    case NumberFormatException ex -> {
                        log.warn("Invalid Category id : {}", cat);
                        yield Optional.empty();
                    }
                    case JsonProcessingException ex -> {
                        log.warn("Failed to process category JSON for category ID: {} - {}", cat, ex.getMessage());
                        yield Optional.empty();
                    }
                    case Exception ex -> {
                        log.error("Unexpected error while processing category ID: {} - {}", cat, ex.getMessage());
                        yield Optional.empty();
                    }
                };
            }
        }
        return Optional.empty();
        }

    @Override
    public BaseDataRs updateBrand(BrandRq rq) {
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing updateBrand(BrandRq) -> "));
        try {
            String id = Optional.ofNullable(rq.getId()).orElseThrow(() -> {
                String errMsg = messages.getErrorProperty(ErrorCodes.EC_REQUIRED_BRAND_ID);
                throw new BrandException(ErrorCodes.EC_REQUIRED_BRAND_ID, errMsg);
            });
            Brand brand = brandRepo.findById(id).orElseThrow(() -> {
                String errMsg = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                return new BrandNotFoundException(ErrorCodes.EC_INVALID_INPUT, errMsg,
                                List.of(Objects.requireNonNull(Utils.populateErrorRSs(
                                                ErrorCodes.EC_BRAND_NOT_FOUND, messages))));
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
        Optional.of(log.isDebugEnabled())
                        .ifPresent(l -> log.debug("Executing deleteBrand(String) -> "));
        try {
            id = Optional.ofNullable(id).filter(Predicate.not(Utils::isEmpty)).orElseThrow(() -> {
                var errMsg = messages.getErrorProperty(ErrorCodes.EC_REQUIRED_BRAND_ID);
                return new BrandException(ErrorCodes.EC_REQUIRED_BRAND_ID, errMsg);
            });
            brandRepo.deleteById(id);
            var baseDataRs = findAllBrand();
            String message = messages.getMessageProperty(MessageCodes.MC_NO_RECORD_FOUND);
            baseDataRs.setMessage(message);
            return baseDataRs;
        } catch (Exception e) {
            log.error("Exception in deleteBrand(String) -> {}", e.getMessage());
            throw switch (e) {
                case BrandException be -> be;
                default -> new BrandException(ErrorCodes.EC_REQUIRED_BRAND_ID, e.getMessage());
            };
        }
    }

    @Override
    public BaseDataRs findById(String id) {
        Optional.of(log).filter(Logger::isDebugEnabled).ifPresent(logger -> logger.debug("Executing findById(String) -> "));
        try{
            id = Optional.ofNullable(id).filter(Predicate.not(Utils::isEmpty)).orElseThrow(() -> {
                var errMsg = messages.getErrorProperty(ErrorCodes.EC_REQUIRED_BRAND_ID);
                throw new BrandException(ErrorCodes.EC_REQUIRED_BRAND_ID, errMsg);
            });
            Brand brand = brandRepo.findById(id).orElseThrow(()->{
                String errMsg = messages.getErrorProperty(ErrorCodes.EC_BRAND_NOT_FOUND);
                throw new BrandNotFoundException(ErrorCodes.EC_BRAND_NOT_FOUND, errMsg);
            });
            BrandRs brandRs = BrandMapper.mapToBrandRs(brand, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_NO_RECORD_FOUND);
            return new BrandDataRs(message, brandRs);
        }catch (Exception e){
            log.error("Exception in findById(String) -> {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public BaseDataRs findByBrandName(String name) {
        Optional.of(log).filter(Logger::isDebugEnabled).ifPresent(logger -> logger.debug("Executing findByBrandName(String) -> "));
        try{
            name = Optional.ofNullable(name).filter(Predicate.not(Utils::isEmpty)).orElseThrow(() -> {
                var errMsg = messages.getErrorProperty(ErrorCodes.EC_REQUIRED_BRAND_ID);
                throw new BrandException(ErrorCodes.EC_REQUIRED_BRAND_ID, errMsg);
            });
            Brand brand = brandRepo.findByName(name).orElseThrow(()->{
                String errMsg = messages.getErrorProperty(ErrorCodes.EC_BRAND_NOT_FOUND);
                throw new BrandNotFoundException(ErrorCodes.EC_BRAND_NOT_FOUND, errMsg);
            });
            BrandRs brandRs = BrandMapper.mapToBrandRs(brand, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_NO_RECORD_FOUND);
            return new BrandDataRs(message, brandRs);
        }catch (Exception e){
            log.error("Exception in findByBrandName(String) -> {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public BaseDataRs findAllBrand() {
        Optional.of(log).filter(Logger::isDebugEnabled).ifPresent(logger -> logger.debug("Executing findAllBrand() -> "));
        try{
            List<Brand> brands = brandRepo.findAll();
            return Optional.of(brands).map(brand->{
                String message = messages.getMessageProperty(
                    MessageCodes.MC_RETRIEVED_SUCCESSFUL);
                List<BrandRs> brandRSs = brand.stream().map(b -> BrandMapper.mapToBrandRs(b, mapper)).toList();
                return new BrandDataRSs(message, brandRSs);
            }).orElse(new BrandDataRSs(messages.getMessageProperty(MessageCodes.MC_NO_RECORD_FOUND),
                Collections.emptyList()));
        }catch (Exception e){
            log.error("Exception in findAllBrand() -> {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public BaseDataRs findBrandsByCategory(String category) {
        return null;
    }
}
