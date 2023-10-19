package io.eeaters.delivery.entity.vo.common.resp;

import lombok.Data;

import java.util.List;

/**
 * 级联结果集通用返回
 */
@Data
public class CascadeResp {

    private String label;

    private String value;

    private List<CascadeResp> children;
}
