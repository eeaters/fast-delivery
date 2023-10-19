package io.eeaters.delivery.mapper;

import io.eeaters.delivery.entity.model.Plan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    default Page<Plan> pageByPlanName(String planName, PageRequest page){
        Plan plan = new Plan();
        if (StringUtils.isNotBlank(planName)) {
            plan.setPlanName(planName);
        }
        Example<Plan> example = Example.of(plan);
        return findAll(example, page);
    }

    Plan findByPlanName(String planName);

}
