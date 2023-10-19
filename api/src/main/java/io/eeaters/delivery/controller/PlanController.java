package io.eeaters.delivery.controller;

import io.eeaters.delivery.entity.base.PageResponse;
import io.eeaters.delivery.entity.vo.plan.req.AddPlanReq;
import io.eeaters.delivery.entity.vo.plan.req.ListPlanReq;
import io.eeaters.delivery.entity.vo.plan.req.UpdatePlanReq;
import io.eeaters.delivery.entity.vo.plan.resp.PlanBaseInfoResp;
import io.eeaters.delivery.entity.vo.plan.resp.PlanResp;
import io.eeaters.delivery.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @PostMapping("add")
    public Long add(@RequestBody @Valid AddPlanReq addPlanReq) {
        return planService.add(addPlanReq);
    }

    @PostMapping("update")
    public Long update(@RequestBody @Valid UpdatePlanReq req) {
        planService.update(req);
        return req.getId();
    }

    @PostMapping("page")
    public PageResponse<PlanResp> page(@RequestBody @Valid ListPlanReq listPlanReq) {
        return planService.page(listPlanReq);
    }

    @GetMapping("listAll")
    public List<PlanBaseInfoResp> listAll() {
        return planService.listAll();
    }
}
