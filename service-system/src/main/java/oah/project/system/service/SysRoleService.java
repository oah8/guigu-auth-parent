package oah.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import oah.project.model.system.SysRole;
import oah.project.model.vo.AssginRoleVo;
import oah.project.model.vo.SysRoleQueryVo;

import java.util.Map;

/**
 * @ClassName SysRoleService
 * @Description TODO
 * @Author _oah
 * @Date 2023.08.29 21:30
 * @Version 1.0
 */
public interface SysRoleService extends IService<SysRole> {
    // 条件分页查询
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    // 获取用户角色数据
    Map<String, Object> getRolesByUserId(String userId);

    // 用户分配角色
    void doAssign(AssginRoleVo assginRoleVo);
}
