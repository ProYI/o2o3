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

@Data
@Table(name = "tb_area")
@TableName("tb_area")
@Entity
@Schema(name = "区域", description = "区域实体定义")
public class Area extends BaseEntiy {

    @Id
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED not null comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "主键ID", example = "1")
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(200) not null comment '区域名称'")
    @Schema(description = "地区名称", required = true)
    private String name;

    @Column(name = "priority", columnDefinition = "tinyInt(1) UNSIGNED not null default '0' comment '优先级'")
    @Schema(description = "优先级", example = "0")
    private Integer priority;

}
