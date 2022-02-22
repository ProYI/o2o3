package online.proyi.o2o3.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntiy {

    @Column(name = "create_time", columnDefinition = "datetime not null default CURRENT_TIMESTAMP comment '创建时间'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        if (createTime == null) {
            createTime = now;
        }
        if (updateTime == null) {
            updateTime = now;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updateTime = new Date();
    }

    @PreRemove
    public void preRemove() {
        updateTime = new Date();
    }

    /**
     * 逻辑删除标志
     * 0 未删除
     * 1 已删除
     */
    @Column(name="del_flag", columnDefinition = " tinyint(1) UNSIGNED not null DEFAULT '0' comment '逻辑删除标志'")
    protected Integer delFlag = 0;

    @Version
    @Column(name = "version", columnDefinition = "int(11) not null comment '版本锁'")
    protected Integer version;
}
