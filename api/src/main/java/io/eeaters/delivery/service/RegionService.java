package io.eeaters.delivery.service;

import io.eeaters.delivery.converter.vo.common.resp.CascadeRespConverter;
import io.eeaters.delivery.entity.model.Region;
import io.eeaters.delivery.entity.vo.common.resp.CascadeResp;
import io.eeaters.delivery.mapper.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<CascadeResp> findAll() {
        List<Region> regionList = regionRepository.findAll();
        return CascadeRespConverter.converter(regionList, 0);
    }
}
