package oah.project.system.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import oah.project.model.system.SysRole;
import oah.project.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName SysRoleServiceTest
 * @Description TODO
 * @Author _oah
 * @Date 2023.08.29 21:36
 * @Version 1.0
 */
@SpringBootTest
public class SysRoleServiceTest {

    // 注入service
    @Autowired
    private SysRoleService sysRoleService;

    // 查询所有
    @Test
    public void findAll() {
        // service方法实现
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }


    // 添加
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色atgui");
        sysRole.setRoleCode("testMaaga");
        sysRole.setDescription("测试角色agfa");
        sysRoleService.save(sysRole);
    }

    // 修改
    @Test
    public void update() {
        SysRole sysRole = sysRoleService.getById(8);
        sysRole.setDescription("test");
        sysRoleService.updateById(sysRole);
    }

    // 删除
    @Test
    public void remove() {
        sysRoleService.removeById(9);
    }

    // 条件查询
    @Test
    public void select() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_code", "SYSTEM");
        List<SysRole> list = sysRoleService.list(wrapper);
        System.out.println(list);
    }





}
