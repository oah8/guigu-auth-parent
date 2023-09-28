package oah.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import oah.project.model.system.SysRole;
import oah.project.model.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    // 条件分页查询
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, @Param("vo") SysRoleQueryVo sysRoleQueryVo);
}
