package io.eeaters.delivery.mapper;

import io.eeaters.delivery.entity.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    
}
