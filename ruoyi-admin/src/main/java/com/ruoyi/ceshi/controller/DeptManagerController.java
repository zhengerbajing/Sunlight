package com.ruoyi.ceshi.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.ceshi.domain.Node;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.ceshi.domain.DeptManager;
import com.ruoyi.ceshi.service.IDeptManagerService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 测试Controller
 * 
 * @author ruoyi
 * @date 2023-09-14
 */
@RestController
@RequestMapping("/ceshi/manager")
public class DeptManagerController extends BaseController
{
    @Autowired
    private IDeptManagerService deptManagerService;

    //
    @PreAuthorize("@ss.hasPermi('ceshi:manager:list')")
    @GetMapping("/node")
    public AjaxResult node(DeptManager deptManager)
    {
        List<Node> node = deptManagerService.selectDeptManagerNode(deptManager.getUrl());
        return success(node);
    }

    @PreAuthorize("@ss.hasPermi('ceshi:manager:list')")
    @GetMapping("/file")
    public AjaxResult file(DeptManager deptManager)
    {
        return success(deptManagerService.selectDeptManagerFile(deptManager));
    }
    //

    /**
     * 查询测试列表
     */
    @PreAuthorize("@ss.hasPermi('ceshi:manager:list')")
    @GetMapping("/list")
    public AjaxResult list(DeptManager deptManager)
    {
        List<DeptManager> list = deptManagerService.selectDeptManagerList(deptManager);
        return success(list);
    }

    /**
     * 导出测试列表
     */
    @PreAuthorize("@ss.hasPermi('ceshi:manager:export')")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeptManager deptManager)
    {
        List<DeptManager> list = deptManagerService.selectDeptManagerList(deptManager);
        ExcelUtil<DeptManager> util = new ExcelUtil<DeptManager>(DeptManager.class);
        util.exportExcel(response, list, "测试数据");
    }

    /**
     * 获取测试详细信息
     */
    @PreAuthorize("@ss.hasPermi('ceshi:manager:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(deptManagerService.selectDeptManagerById(id));
    }

    /**
     * 新增测试
     */
    @PreAuthorize("@ss.hasPermi('ceshi:manager:add')")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeptManager deptManager)
    {
        return toAjax(deptManagerService.insertDeptManager(deptManager));
    }

    /**
     * 修改测试
     */
    @PreAuthorize("@ss.hasPermi('ceshi:manager:edit')")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeptManager deptManager)
    {
        return toAjax(deptManagerService.updateDeptManager(deptManager));
    }

    /**
     * 删除测试
     */
    @PreAuthorize("@ss.hasPermi('ceshi:manager:remove')")
    @Log(title = "测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(deptManagerService.deleteDeptManagerByIds(ids));
    }
}
