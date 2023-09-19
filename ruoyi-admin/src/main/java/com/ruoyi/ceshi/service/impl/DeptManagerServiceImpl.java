package com.ruoyi.ceshi.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.ceshi.domain.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ceshi.mapper.DeptManagerMapper;
import com.ruoyi.ceshi.domain.DeptManager;
import com.ruoyi.ceshi.service.IDeptManagerService;

/**
 * 测试Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-14
 */
@Service
public class DeptManagerServiceImpl implements IDeptManagerService 
{
    @Autowired
    private DeptManagerMapper deptManagerMapper;

    /**
     * 查询测试
     * 
     * @param id 测试主键
     * @return 测试
     */
    @Override
    public DeptManager selectDeptManagerById(Long id)
    {
        return deptManagerMapper.selectDeptManagerById(id);
    }

    /**
     * 查询测试列表
     * 
     * @param deptManager 测试
     * @return 测试
     */
    @Override
    public List<DeptManager> selectDeptManagerList(DeptManager deptManager)
    {
        return deptManagerMapper.selectDeptManagerList(deptManager);
    }

    //
    public  List<Node> buildTree(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            ArrayList<Node> list = new ArrayList();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    Node node = new Node();
                    if(files[i].isDirectory()){
                        node.setIsDirectory(1);
                    } else {
                        node.setIsDirectory(0);
                    }
                    node.setParentUrl(files[i].getParent());
                    node.setName(files[i].getName());
                    node.setUrl(files[i].getAbsolutePath());
                    list.add(node);
                }
            } else{
                return null;
            }
            return list;
        }
        return null;
    }

    @Override
    public List<Node> selectDeptManagerNode(String url)
    {
        File rootFolder = new File(url);
        List<Node> list = buildTree(rootFolder);
        return list;
    }

    //@Override
    public boolean selectDeptManagerFile(DeptManager deptManager)
    {
        InputStream in = null;
        OutputStream out = null;
        try {
            System.out.println(deptManager.getUrl());
            File inFile = new File(deptManager.getUrl());
            System.out.println(inFile.canRead());
            File outFile = new File("C:/ruoyi/uploadPath/upload/file/" + deptManager.getFileName());
            in = new FileInputStream(inFile);
            out = new FileOutputStream(outFile);
            byte[] b = new byte[1024];
            int num;
            while((num = in.read(b,0,1024)) != -1){
                out.write(b,0,num);
            }
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                   e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    //

    /**
     * 新增测试
     * 
     * @param deptManager 测试
     * @return 结果
     */
    @Override
    public int insertDeptManager(DeptManager deptManager)
    {
        return deptManagerMapper.insertDeptManager(deptManager);
    }

    /**
     * 修改测试
     * 
     * @param deptManager 测试
     * @return 结果
     */
    @Override
    public int updateDeptManager(DeptManager deptManager)
    {
        return deptManagerMapper.updateDeptManager(deptManager);
    }

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的测试主键
     * @return 结果
     */
    @Override
    public int deleteDeptManagerByIds(Long[] ids)
    {
        return deptManagerMapper.deleteDeptManagerByIds(ids);
    }

    /**
     * 删除测试信息
     * 
     * @param id 测试主键
     * @return 结果
     */
    @Override
    public int deleteDeptManagerById(Long id)
    {
        return deptManagerMapper.deleteDeptManagerById(id);
    }
}
