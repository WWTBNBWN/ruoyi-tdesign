package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 应用管理对象 sys_app
 *
 * @author yixiacoco
 * @date 2023-05-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_app")
public class SysApp extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用id
     */
    @TableId(value = "appid")
    private Long appid;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 应用key
     */
    private String appKey;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 创建部门
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createDept;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

}
