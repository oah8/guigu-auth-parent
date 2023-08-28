package oah.project.system.test;

import oah.project.model.system.SysRole;
import oah.project.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void testAdd() {
        List<SysRole> list = sysRoleMapper.selectList(null);
        for(SysRole sysRole : list) {
            System.out.println(sysRole);
        }

    }
}
