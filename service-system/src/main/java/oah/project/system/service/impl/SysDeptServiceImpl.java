package oah.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import oah.project.model.system.SysDept;
import oah.project.system.exception.GuiguException;
import oah.project.system.mapper.SysDeptMapper;
import oah.project.system.service.SysDeptService;
import oah.project.system.utils.DeptHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysDeptServiceImpl
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.11 23:23
 * @Version 1.0
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {


    @Autowired
    private SysDeptMapper sysDeptMapper;

    // 部门列表
    @Override
    public List<SysDept> findDeptLists() {
        // 获取所有部门
        List<SysDept> sysDeptList = baseMapper.selectList(null);
        // 将所有部门数据转换要求的数据格式
        List<SysDept> resultList = DeptHelper.buildTree(sysDeptList);

        return resultList;
    }

    // 删除部门
    @Override
    public void removeDeptById(String id) {
        // 查询当前删除菜单下面是否有子菜单
        // 根据id = parentId
        QueryWrapper<SysDept> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);

        if(count > 0) { // 存在子菜单
            throw new GuiguException(201, "请删除子菜单");
        }
        // 调用删除
        baseMapper.deleteById(id);
    }


    // 更新部门状态
    @Override
    public void updateStatus(String id, Integer status) {
        SysDept sysDept = baseMapper.selectById(id);
        sysDept.setStatus(status);
        baseMapper.updateById(sysDept);
    }
}
