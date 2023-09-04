package org.dromara.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.system.domain.SysOss;
import org.dromara.system.domain.SysOssCategory;
import org.dromara.system.domain.bo.SysOssCategoryBo;
import org.dromara.system.domain.query.SysOssCategoryQuery;
import org.dromara.system.domain.vo.SysOssCategoryVo;
import org.dromara.system.mapper.SysOssCategoryMapper;
import org.dromara.system.service.ISysOssCategoryService;
import org.dromara.system.service.ISysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * OSS分类Service业务层处理
 *
 * @author hexm
 * @date 2023-08-14
 */
@Service
public class SysOssCategoryServiceImpl extends ServiceImpl<SysOssCategoryMapper, SysOssCategory> implements ISysOssCategoryService {

    public static final String ROOT_PATH = "/";
    @Autowired
    private ISysOssService ossService;

    /**
     * 查询OSS分类
     *
     * @param ossCategoryId 主键
     * @return SysOssCategoryVo
     */
    @Override
    public SysOssCategoryVo queryById(Long ossCategoryId) {
        return baseMapper.queryVoById(ossCategoryId);
    }

    /**
     * 查询OSS分类列表
     *
     * @param query 查询对象
     * @return SysOssCategoryVo
     */
    @Override
    public List<SysOssCategoryVo> queryList(SysOssCategoryQuery query) {
        return baseMapper.queryList(query);
    }

    /**
     * 根据新增业务对象插入OSS分类
     *
     * @param bo OSS分类新增业务对象
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByBo(SysOssCategoryBo bo) {
        SysOssCategory category = MapstructUtils.convert(bo, SysOssCategory.class);
        // 设置路径、层级
        if (bo.getParentId().equals(0L)) {
            category.setCategoryPath(ROOT_PATH + category.getCategoryName());
            category.setLevel(0);
            category.setParentId(0L);
        } else {
            SysOssCategory parent = getById(bo.getParentId());
            if (parent == null) {
                throw new ServiceException("父分类不存在！");
            }
            // 检查父类是否是当前分类或子分类下
            if ((parent.getCategoryPath() + ROOT_PATH).startsWith(category.getCategoryPath() + ROOT_PATH)) {
                throw new ServiceException("父分类不能为当前分类或子分类下");
            }
            category.setParentId(parent.getOssCategoryId());
            category.setCategoryPath(parent.getCategoryPath() + ROOT_PATH + category.getCategoryName());
            category.setLevel(parent.getLevel() + 1);
        }
        checkRepeat(category);
        return save(category);
    }

    /**
     * 根据编辑业务对象修改OSS分类
     *
     * @param bo OSS分类编辑业务对象
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByBo(SysOssCategoryBo bo) {
        SysOssCategory category = getById(bo.getOssCategoryId());
        if (!category.getParentId().equals(bo.getParentId()) || !category.getCategoryName().equals(bo.getCategoryName())) {
            int level = category.getLevel();
            String path = category.getCategoryPath();
            int levelDiff;
            if (bo.getParentId().equals(0L)) {
                levelDiff = category.getLevel();
                category.setCategoryPath(ROOT_PATH + category.getCategoryName());
                category.setLevel(0);
                category.setParentId(0L);
            } else {
                SysOssCategory parent = getById(bo.getParentId());
                if (parent == null) {
                    throw new ServiceException("父分类不存在！");
                }
                // 检查父类是否是当前分类或子分类下
                if ((parent.getCategoryPath() + ROOT_PATH).startsWith(category.getCategoryPath() + ROOT_PATH)) {
                    throw new ServiceException("父分类不能为当前分类或子分类下");
                }
                levelDiff = level - (parent.getLevel() + 1);
                category.setParentId(parent.getOssCategoryId());
                category.setCategoryPath(parent.getCategoryPath() + ROOT_PATH + category.getCategoryName());
                category.setLevel(parent.getLevel() + 1);
            }
            // 检查分类名称是否重复
            checkRepeat(category);
            // 获取子分类，更新子分类的路径
            List<SysOssCategory> children = lambdaQuery()
                .likeRight(SysOssCategory::getCategoryPath, path + ROOT_PATH)
                .select(SysOssCategory::getOssCategoryId, SysOssCategory::getCategoryPath, SysOssCategory::getLevel)
                .list();
            for (SysOssCategory child : children) {
                child.setLevel(child.getLevel() - levelDiff);
                child.setCategoryPath(child.getCategoryPath().replaceFirst(path, category.getCategoryPath()));
            }
            if (CollUtil.isNotEmpty(children)) {
                updateBatchById(children);
            }
        }
        // 检查不能设置父分类id为子分类id
        return updateById(category);
    }

    /**
     * 校验并批量删除OSS分类信息
     *
     * @param ids 主键集合
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        boolean exists = ossService.lambdaQuery().in(SysOss::getOssCategoryId, ids).exists();
        if (exists) {
            throw new ServiceException("无法删除非空分类");
        }
        exists = lambdaQuery().in(SysOssCategory::getParentId, ids).exists();
        if (exists) {
            throw new ServiceException("请先删除子分类");
        }
        return removeByIds(ids);
    }

    /**
     * 检查是否包含相同路径的分类
     *
     * @param category 分类对象
     */
    private void checkRepeat(SysOssCategory category) {
        boolean exists = lambdaQuery()
            .ne(category.getOssCategoryId() != null, SysOssCategory::getOssCategoryId, category.getOssCategoryId())
            .eq(SysOssCategory::getCategoryPath, category.getCategoryPath())
            .exists();
        if (exists) {
            throw new ServiceException("此位置已包含同名分类");
        }
    }
}