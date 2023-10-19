package io.eeaters.delivery.converter.vo.common.resp;

import io.eeaters.delivery.entity.model.Region;
import io.eeaters.delivery.entity.vo.common.resp.CascadeResp;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class CascadeRespConverter {

    public static List<CascadeResp> converter(List<Region> regions, Integer root) {
        List<CascadeResp> result = new ArrayList<>();
        Map<Integer, List<Region>> treeMap = regions.stream().collect(Collectors.groupingBy(Region::getParentId));
        //第一级
        List<Region> list = treeMap.get(root);
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        for (Region region : list) {
            result.add(fillCascade(region, treeMap));
        }
        return result;

    }

    private static CascadeResp fillCascade(Region region, Map<Integer, List<Region>> treeMap) {
        CascadeResp resp = converter(region);
        if (treeMap.containsKey(region.getId())) {
            List<Region> children = treeMap.get(region.getId());
            for (Region child : children) {
                resp.getChildren().add(fillCascade(child, treeMap));
            }
        }
        return resp;

    }

    private static CascadeResp converter(Region region) {
        CascadeResp cascadeResp = new CascadeResp();
        cascadeResp.setLabel(region.getName());
        cascadeResp.setValue(String.valueOf(region.getId()));
        cascadeResp.setChildren(new ArrayList<>());
        return cascadeResp;
    }



}
