package oah.project.system.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import oah.project.model.system.SysRole;
import oah.project.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SysRoleMapperTest
 * @Description TODO
 * @Author _oah
 * @Date 2023.08.28 19:05
 * @Version 1.0
 */
@SpringBootTest
public class SysRoleMapperTest {
    // springboot 扫描规则：扫描当前包及其子包内容
    @Autowired
    private SysRoleMapper sysRoleMapper;


    // 7条件删除
    @Test
    public void testDelete() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", "用户管理员");
        sysRoleMapper.delete(wrapper);

    }


    // 6条件查询
    @Test
    public void testSelect() {
        // 创建条件构造器对象
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        // 设置条件
//        wrapper.eq("role_name", "用户管理员");
        wrapper.like("role_name", "管理员");
        // 调用方法查询
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        System.out.println(list);
    }


    // 批量删除
    @Test
    public void testBatchDelete() {
        sysRoleMapper.deleteBatchIds(Arrays.asList(8, 9));
    }



    // 4id删除
    @Test
    public void deleteById() {
        int rows = sysRoleMapper.deleteById(9);
    }


    // 3修改操作
    @Test
    public void update() {
        // 根据id查询
        SysRole sysRole = sysRoleMapper.selectById(9);

        // 设置修改值
        sysRole.setDescription("系统管理员尚硅谷");

        // 调用方法实现修改
        sysRoleMapper.updateById(sysRole);
    }


    // 2添加操作
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色1");
        sysRole.setRoleCode("testManager1");
        sysRole.setDescription("测试角色1");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println(rows);
    }


    // 1查询表所有记录
    @Test
    public void findAll() {
        List<SysRole> list = sysRoleMapper.selectList(null);
        for(SysRole sysRole : list) {
            System.out.println(sysRole);
        }

    }
}
