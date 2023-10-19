package io.eeaters.delivery.entity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "t_plan_channel_mapping")
public class PlanChannelMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_id")
    private Long planId;

    private String channel;

    private Integer sort;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time", insertable = false, updatable = false)
    private LocalDateTime createTime;
}
