package io.eeaters.delivery.service;

import io.eeaters.delivery.converter.base.PageResponseConverter;
import io.eeaters.delivery.converter.model.PlanChannelMappingConverter;
import io.eeaters.delivery.converter.model.PlanConverter;
import io.eeaters.delivery.converter.vo.plan.resp.PlanBaseInfoRespConverter;
import io.eeaters.delivery.converter.vo.plan.resp.PlanRespConverter;
import io.eeaters.delivery.entity.base.PageResponse;
import io.eeaters.delivery.entity.model.Plan;
import io.eeaters.delivery.entity.model.PlanChannelMapping;
import io.eeaters.delivery.entity.vo.plan.req.AddPlanReq;
import io.eeaters.delivery.entity.vo.plan.req.ListPlanReq;
import io.eeaters.delivery.entity.vo.plan.req.UpdatePlanReq;
import io.eeaters.delivery.entity.vo.plan.resp.PlanBaseInfoResp;
import io.eeaters.delivery.entity.vo.plan.resp.PlanResp;
import io.eeaters.delivery.mapper.PlanChannelMappingRepository;
import io.eeaters.delivery.mapper.PlanRepository;
import io.eeaters.delivery.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanService {

    @Autowired
    PlanRepository planRepository;

    @Autowired
    PlanChannelMappingRepository mappingRepository;

    @Transactional
    public Long add(AddPlanReq addPlanReq) {
        Plan existPlan = planRepository.findByPlanName(addPlanReq.getPlanName());
        Asserts.isNotNull(existPlan, "方案已存在");

        Plan plan = PlanConverter.convert(addPlanReq);
        plan = planRepository.save(plan);

        List<PlanChannelMapping> mappings = PlanChannelMappingConverter.convert(plan.getId(),
                addPlanReq.getOperateUser(),
                addPlanReq.getMappingList());
        mappingRepository.saveAll(mappings);
        return plan.getId();
    }

    @Transactional
    public void update(UpdatePlanReq req) {
        Plan existPlan = planRepository.findByPlanName(req.getPlanName());

        if (existPlan != null) {
            Asserts.isTrue(!Objects.equals(existPlan.getId(), req.getId()), "方案已存在");
        }
        mappingRepository.deleteAllByPlanId(req.getId());

        Plan plan = PlanConverter.convert(req);
        planRepository.save(plan);

        List<PlanChannelMapping> mappings = PlanChannelMappingConverter.convert(plan.getId(),
                req.getOperateUser(),
                req.getMappingList());
        mappingRepository.saveAll(mappings);
    }

    public PageResponse<PlanResp> page(ListPlanReq req) {
        Page<Plan> plans = planRepository.pageByPlanName(req.getPlanName(), req.getPageQuery().toPageRequest());
        if (CollectionUtils.isEmpty(plans.getContent())) {
            return PageResponseConverter.emptyPage(plans);
        }
        List<Long> planIds = plans.getContent().stream().map(Plan::getId).toList();
        Map<Long, List<PlanChannelMapping>> planIdToChannelMap = mappingRepository.findAllByPlanIdIn(planIds)
                .stream()
                .collect(Collectors.groupingBy(PlanChannelMapping::getPlanId));
        return PageResponseConverter.convert(plans, plan -> PlanRespConverter.convert(plan, planIdToChannelMap.get(plan.getId())));

    }

    public List<PlanBaseInfoResp> listAll() {
        List<Plan> planList = planRepository.findAll();
        return PlanBaseInfoRespConverter.convert(planList);
    }

}
