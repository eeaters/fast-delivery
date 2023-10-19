package io.eeaters.delivery.mapper;

import io.eeaters.delivery.entity.model.PlanChannelMapping;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanChannelMappingRepository extends JpaRepository<PlanChannelMapping, Long> {
    List<PlanChannelMapping> findAllByPlanIdIn(List<Long> planIds);

    void deleteAllByPlanId(Long planId);
}
