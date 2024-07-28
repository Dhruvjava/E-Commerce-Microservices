package org.cb.brands.repo;

import org.cb.brands.entity.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandRepo extends MongoRepository<Brand, String> {
}
