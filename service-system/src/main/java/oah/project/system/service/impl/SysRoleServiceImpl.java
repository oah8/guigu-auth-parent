package oah.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import oah.project.model.system.SysRole;
import oah.project.model.system.SysUserRole;
import oah.project.model.vo.AssginRoleVo;
import oah.project.model.vo.SysRoleQueryVo;
import oah.project.system.mapper.SysRoleMapper;
import oah.project.system.mapper.SysUserRoleMapper;
import oah.project.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysRoleServiceImpl
 * @Description TODO
 * @Author _oah
 * @Date 2023.08.29 21:31
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }

    // 获取用户角色数据
    @Override
    public Map<String, Object> getRolesByUserId(String userId) {
        // 获取所有角色
        List<SysRole> roles = baseMapper.selectList(null);

        // 根据用户id查询，已经分配角色信息
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<SysUserRole> userRolesList = sysUserRoleMapper.selectList(wrapper);

        // 从userRoles集合获取所有角色id
        List<String> userRoleIds = new ArrayList<>();
        for(SysUserRole userRole : userRolesList) {
            String roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }

        // 封装到map集合
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles", roles); // 所有角色
        returnMap.put("userRoleIds", userRoleIds); // 用户分配角色id集合
        return returnMap;

    }

    // 用户分配角色
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        // 根据用户id删除之前分配角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);

        // 获取所有角色id，添加角色用户关系表
        // 角色id列表
        List<String> roleIdList = assginRoleVo.getRoleIdList();

        for(String roleId : roleIdList) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assginRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        }

    }
}
