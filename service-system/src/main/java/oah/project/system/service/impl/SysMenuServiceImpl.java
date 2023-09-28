package oah.project.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import oah.project.model.system.SysMenu;
import oah.project.model.system.SysRoleMenu;
import oah.project.model.vo.AssginMenuVo;
import oah.project.model.vo.RouterVo;
import oah.project.system.exception.GuiguException;
import oah.project.system.mapper.SysMenuMapper;
import oah.project.system.mapper.SysRoleMenuMapper;
import oah.project.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import oah.project.system.utils.MenuHelper;
import oah.project.system.utils.RouterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author _oah
 * @since 2023-09-18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    // 菜单列表（树形）
    @Override
    public List<SysMenu> findNodes() {
        // 获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);

        // 将所有菜单数据转换要求的数据格式
        List<SysMenu> resultList = MenuHelper.buildTree(sysMenuList);

        return resultList;
    }

    // 删除菜单
    @Override
    public void removeMenuById(String id) {
        // 查询当前删除菜单下面是否有子菜单
        // 根据id = parentId
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) { // 有子菜单
            throw new GuiguException(201, "请先删除子菜单");
        }
        // 调用删除
        baseMapper.deleteById(id);
    }

    // 根据角色分配菜单
    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        // 获取所有菜单 status = 1
        QueryWrapper<SysMenu> wrapperMenu = new QueryWrapper<>();
        wrapperMenu.eq("status", 1);
        List<SysMenu> menuList = baseMapper.selectList(wrapperMenu);

        // 根据角色id查询，角色分配过的菜单列表
        QueryWrapper<SysRoleMenu> wrapperRoleMenu = new QueryWrapper<>();
        wrapperRoleMenu.eq("role_id", roleId);
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(wrapperRoleMenu);
        // 从第二步查询列表中，获取角色分配所有菜单id
        List<String> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : roleMenus) {
            String menuId = sysRoleMenu.getMenuId();
            roleMenuIds.add(menuId);
        }

        // 数据处理：isSelect 如果菜单被选中true，否则false
        // 拿着分配菜单id 和 所有菜单比对，有相同的，让isSelect值为true
        for (SysMenu sysMenu : menuList) {
            if(roleMenuIds.contains(sysMenu.getId())) {
                sysMenu.setSelect(true);
            } else {
                sysMenu.setSelect(false);
            }
        }
        // 转换成树形结构为了最终显示 MenuHelper方法实现
        List<SysMenu> sysMenus = MenuHelper.buildTree(menuList);

        return sysMenus;
    }

    // 更改菜单状态
    @Override
    public void updateStatus(String id, Integer status) {
        SysMenu sysMenu = baseMapper.selectById(id);
        sysMenu.setStatus(status);
        baseMapper.updateById(sysMenu);
    }

    // 给角色分配菜单权限

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        // 根据角色id删除菜单权限
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);
        // 遍历菜单id列表
        List<String> menuIdList = assginMenuVo.getMenuIdList();

        for (String menuId : menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }


    // 根据userid查询菜单权限值
    @Override
    public List<RouterVo> getUserMenuList(String userId) {
        // admin是超级管理员，操作所有内容
        List<SysMenu> sysMenuList = null;

        // 判断userId值是1，代表超级管理员，查询所有权限数据
        if("1".equals(userId)) {
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("status", 1);
            wrapper.orderByAsc("sort_value");
            sysMenuList = baseMapper.selectList(wrapper);
        } else {
            // 判断userId值不是1，其他类型用户，查询这个用户权限
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }

        // 构建树形结构
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);

        // 转换成前端路由要求格式数据
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenuTreeList);

        return routerVoList;
    }

    // 根据userid查询按钮权限值
    @Override
    public List<String> getUserButtonList(String userId) {
        List<SysMenu> sysMenuList = null;
        // 判断是否管理员
        if("1".equals(userId)) {
            sysMenuList = baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }
        // sysMenuList遍历
        List<String> permissionList = new ArrayList<>();
        for(SysMenu sysMenu : sysMenuList) {
            // type = 2
            if(sysMenu.getType() == 2) {
                String perms = sysMenu.getPerms();
                permissionList.add(perms);
            }
        }
        return permissionList;
    }
}
