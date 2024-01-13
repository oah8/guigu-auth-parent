package oah.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import oah.project.model.system.SysDept;

import java.util.List;

/**
 * @ClassName SysDeptService
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.11 23:22
 * @Version 1.0
 */
public interface SysDeptService extends IService<SysDept> {

    // 部门列表(树形)
    public List<SysDept> findDeptLists();

    // 删除部门
    public void removeDeptById(String id);

    // 更改部门状态
    public void updateStatus(String id, Integer status);



}
