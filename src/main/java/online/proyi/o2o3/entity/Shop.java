package online.proyi.o2o3.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 〈店铺实体〉
 */
@Data
@Table(name = "tb_area")
@TableName("tb_area")
@Entity
public class Shop extends BaseEntiy {
    @Id
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED not null comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "主键ID", example = "1")
    private Integer Id;

    @Column(name = "name", columnDefinition = "varchar(200) not null comment '商铺名称'")
    @Schema(description = "商铺名称", required = true)
    private String name;

    @Column(name = "desc", columnDefinition = "varchar(200) not null comment '商铺描述'")
    @Schema(description = "商铺描述", required = true)
    private String desc;

    @Column(name = "addr", columnDefinition = "varchar(200) not null comment '商铺地址'")
    @Schema(description = "商铺地址", required = true)
    private String addr;

    @Column(name = "phone", columnDefinition = "varchar(11) not null comment '商铺联系电话'")
    @Schema(description = "商铺联系电话", required = true)
    private String phone;

    @Column(name = "img", columnDefinition = "varchar(200) not null comment '商铺图片'")
    @Schema(description = "商铺图片", required = true)
    private String img;

    @Column(name = "priority", columnDefinition = "tinyInt(1) UNSIGNED not null default '0' comment '优先级'")
    @Schema(description = "优先级", example = "0")
    private Integer priority;

    @Column(name = "enable_status", columnDefinition = "tinyInt(1) not null default '0' comment '可用状态 -1不可用，0审核中，1可用'")
    @Schema(description = "可用状态", example = "-1不可用，0审核中，1可用")
    private Integer enableStatus;

    @Column(name = "advice", columnDefinition = "varchar(200) null comment '超级管理员建议'")
    @Schema(description = "超级管理员建议")
    private String advice;

    @Transient
    private Area area;

    @Transient
    private UserInfo owner;

    @Transient
    private ShopCategory shopCategory;
}