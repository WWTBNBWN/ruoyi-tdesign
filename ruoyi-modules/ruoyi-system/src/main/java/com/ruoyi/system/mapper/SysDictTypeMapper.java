package com.ruoyi.system.mapper;

import com.ruoyi.common.mybatis.core.mapper.BaseMapperPlus;
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.domain.bo.SysDictTypeBo;
import com.ruoyi.system.domain.vo.SysDictTypeVo;

import java.util.List;

/**
 * 字典表 数据层
 *
 * @author Lion Li
 */
public interface SysDictTypeMapper extends BaseMapperPlus<SysDictType, SysDictTypeVo> {

    /**
     * 查询字典类型列表
     *
     * @param dictType
     * @return {@link SysDictType}
     */
    List<SysDictTypeVo> queryList(SysDictTypeBo dictType);
}
