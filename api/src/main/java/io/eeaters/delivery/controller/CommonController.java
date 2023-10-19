package io.eeaters.delivery.controller;

import io.eeaters.delivery.entity.vo.common.resp.CascadeResp;
import io.eeaters.delivery.entity.vo.common.resp.ChannelResp;
import io.eeaters.delivery.enums.ChannelEnum;
import io.eeaters.delivery.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 通用信息
 */
@RestController
@RequestMapping("common")
public class CommonController {


    @Autowired
    private RegionService regionService;


    @GetMapping("channel/all")
    public List<ChannelResp> channelAll() {
       return Arrays.stream(ChannelEnum.values())
                .map(ChannelResp::of)
                .collect(Collectors.toList());
    }


    @GetMapping("region/all")
    public List<CascadeResp> regionAll() {
        return regionService.findAll();
    }


}
