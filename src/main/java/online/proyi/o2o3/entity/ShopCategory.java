package online.proyi.o2o3.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 〈店铺类别〉
 */
@Data
@Table(name = "tb_shop_category")
@TableName("tb_shop_category")
@Entity
public class ShopCategory extends BaseEntiy {
    @javax.persistence.Id
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED not null comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "主键ID", example = "1")
    private Integer Id;

    @Column(name = "name", columnDefinition = "varchar(200) not null comment '商铺分类名'")
    @Schema(description = "商铺分类名", required = true)
    private String name;

    @Column(name = "desc", columnDefinition = "varchar(200) default '' comment '商铺分类描述'")
    @Schema(description = "商铺分类描述")
    private String desc;

    @Column(name = "img", columnDefinition = "varchar(200) default null comment '商铺分类图片'")
    @Schema(description = "商铺分类图片")
    private String img;

    @Column(name = "priority", columnDefinition = "tinyInt(1) UNSIGNED not null default '0' comment '优先级'")
    @Schema(description = "优先级", example = "0")
    private Integer priority;

    @Transient
    private ShopCategory parent;
}