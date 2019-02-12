package vip.proyi.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author pengchen
 * @since 2019-01-15
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("tb_user")
public class User extends Model<User> {

    private static final long serialVersionUID = -2852516089673729027L;
    /**
     * 用户主键
     */
    @TableId("user_no")
    private String userNo;
    /**
     * 是电话号码，也是账号（登录用）
     */
    private String mobile;
    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    @TableField("pass_word")
    private String passWord;
    /**
     * 单位
     */
    private String unit;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态值（1：启用，2：禁用，3：删除）
     */
    private Integer status;
    /**
     * 职位
     */
    private String job;

    @Override
    protected Serializable pkVal() {
        return this.userNo;
    }

}
