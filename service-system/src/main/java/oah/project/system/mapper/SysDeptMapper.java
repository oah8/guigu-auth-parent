package oah.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oah.project.model.system.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysDeptMapper
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.11 22:32
 * @Version 1.0
 */
@Repository
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
}
