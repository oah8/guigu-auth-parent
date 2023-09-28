package oah.project.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import oah.project.model.system.SysUser;
import oah.project.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author _oah
 * @since 2023-09-17
 */
public interface SysUserService extends IService<SysUser> {

    // 用户列表方法
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    // 更改用户状态
    void updateStatus(String id, Integer status);

    // 根据用户名称进行查询
    SysUser getUserInfoByUserName(String username);

    // 根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
    Map<String, Object> getUserInfo(String username);
}
