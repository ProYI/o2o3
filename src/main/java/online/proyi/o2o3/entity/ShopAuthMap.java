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
 * 〈店铺授权〉
 */
@Data
@Table(name = "tb_shop_auth_map")
@TableName("tb_shop_auth_map")
@Entity
public class ShopAuthMap extends BaseEntiy {
    @Id
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED not null comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "主键ID", example = "1")
    private Integer id;

    @Column(name = "title", columnDefinition = "varchar(200) not null comment '职称名'")
    @Schema(description = "职称名", required = true)
    private String title;


    @Column(name = "title_flag", columnDefinition = "tinyInt(1) UNSIGNED not null comment '职称符号（可用于权限控制）'")
    @Schema(description = "授权状态", example = "0")
    private Integer titleFlag;

    @Column(name = "enable_status", columnDefinition = "tinyInt(1) UNSIGNED not null default '0' comment '授权状态 0 无效 1 有效'")
    @Schema(description = "授权状态", example = "0")
    private Integer enableStatus;

    //员工信息实体类
    @Transient
    private UserInfo employee;

    //店铺实体类
    @Transient
    private Shop shop;
}