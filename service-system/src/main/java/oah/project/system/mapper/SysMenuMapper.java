package oah.project.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oah.project.model.system.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author _oah
 * @since 2023-09-18
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    // 根据userid查询菜单权限数据
    List<SysMenu> findMenuListUserId(@Param("userId") String userId);
}
