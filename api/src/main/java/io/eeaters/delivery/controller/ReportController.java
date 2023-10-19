package io.eeaters.delivery.controller;

import io.eeaters.delivery.entity.vo.report.resp.DashboardResp;
import io.eeaters.delivery.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    ReportService reportService;
    @GetMapping("/dashboard")
    public DashboardResp dashboard(@RequestParam Integer period) {
        return reportService.mockDashboard();
    }

}
