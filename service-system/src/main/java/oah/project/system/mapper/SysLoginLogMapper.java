package oah.project.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oah.project.model.system.SysLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author _oah
 * @since 2023-09-17
 */
@Repository
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {


}
