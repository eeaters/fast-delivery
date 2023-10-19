package io.eeaters.delivery.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "t_region")
public class Region {

    @Id
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    private String name;
}
