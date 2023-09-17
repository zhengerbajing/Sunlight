package com.ruoyi.ceshi.mapper;

import java.util.List;
import com.ruoyi.ceshi.domain.DeptManager;

/**
 * 测试Mapper接口
 * 
 * @author ruoyi
 * @date 2023-09-14
 */
public interface DeptManagerMapper 
{
    /**
     * 查询测试
     * 
     * @param id 测试主键
     * @return 测试
     */
    public DeptManager selectDeptManagerById(Long id);

    /**
     * 查询测试列表
     * 
     * @param deptManager 测试
     * @return 测试集合
     */
    public List<DeptManager> selectDeptManagerList(DeptManager deptManager);

    /**
     * 新增测试
     * 
     * @param deptManager 测试
     * @return 结果
     */
    public int insertDeptManager(DeptManager deptManager);

    /**
     * 修改测试
     * 
     * @param deptManager 测试
     * @return 结果
     */
    public int updateDeptManager(DeptManager deptManager);

    /**
     * 删除测试
     * 
     * @param id 测试主键
     * @return 结果
     */
    public int deleteDeptManagerById(Long id);

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeptManagerByIds(Long[] ids);
}
