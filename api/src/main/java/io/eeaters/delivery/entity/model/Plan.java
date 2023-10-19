package io.eeaters.delivery.entity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "t_plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "`desc`")
    private String desc;

    @Column(name = "time_period")
    private Integer timePeriod;

    @Column(name = "type")
    private Integer type;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time", insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time",insertable = false,updatable = false)
    private LocalDateTime updateTime;


}
