package online.proyi.o2o3.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 〈用户entity〉
 */
@Data
@Table(name = "tb_user_info")
@TableName("tb_user_info")
@Entity
public class UserInfo extends BaseEntiy {

    @javax.persistence.Id
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED not null comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "主键ID", example = "1")
    private Long Id;

    @Column(name = "name", columnDefinition = "varchar(200) not null comment '用户姓名'")
    @Schema(description = "用户姓名", required = true)
    private String name;

    @Column(name = "profile_img", columnDefinition = "varchar(200) not null comment '个人头像'")
    @Schema(description = "个人头像", required = true)
    private String profileImg;

    @Column(name = "email", columnDefinition = "varchar(200) not null comment '电子邮箱'")
    @Schema(description = "电子邮箱", required = true)
    private String email;

    @Column(name = "email", columnDefinition = "varchar(10) not null comment '性别'")
    @Schema(description = "性别", required = true)
    private String gender;

    @Column(name = "enable_status", columnDefinition = "tinyInt(1) not null default '0' comment '可用状态 -1不可用，0审核中，1可用'")
    @Schema(description = "可用状态", example = "-1不可用，0审核中，1可用")
    private Integer enableStatus;

    @Column(name = "user_type", columnDefinition = "tinyInt(1) not null comment '用户类型 1.顾客 2.店家 3.超级管理员'")
    @Schema(description = "用户类型", example = "1.顾客 2.店家 3.超级管理员")
    private Integer userType;
}