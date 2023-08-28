package oah.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oah.project.model.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysRoleMapper
 * @Description TODO
 * @Author _oah
 * @Date 2023.08.28 19:01
 * @Version 1.0
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
}
