package oah.project.system.service.impl;

import oah.project.model.system.SysUser;
import oah.project.system.custom.CustomUser;
import oah.project.system.service.SysMenuService;
import oah.project.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.28 15:40
 * @Version 1.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserInfoByUserName(username);
        if(sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if(sysUser.getStatus().intValue() == 0) {
            throw new RuntimeException("用户被禁用了");
        }
        // 根据userid查询操作权限数据
        List<String> userPermsList = sysMenuService.getUserButtonList(sysUser.getId());

        // 转换成security要求格式数据
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(String perm : userPermsList) {
            authorities.add(new SimpleGrantedAuthority(perm.trim()));
        }

        return new CustomUser(sysUser, authorities);
    }
}
