package oah.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oah.project.model.system.SysRoleMenu;
import oah.project.model.system.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysUserRoleMapper
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.18 21:11
 * @Version 1.0
 */
@Repository
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
}
