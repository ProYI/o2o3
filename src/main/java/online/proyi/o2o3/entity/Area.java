package online.proyi.o2o3.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_area")
@Entity
public class Area extends BaseEntiy {

    @Id
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED not null comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(200) not null comment '区域名称'")
    private String name;

    @Column(name = "priority", columnDefinition = "tinyInt(1) UNSIGNED not null default '0' comment '优先级'")
    private Integer priority;

}
