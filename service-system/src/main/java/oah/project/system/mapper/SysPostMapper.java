package oah.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import oah.project.model.system.SysPost;
import oah.project.model.vo.SysPostQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysPostMapper
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.12 11:21
 * @Version 1.0
 */
@Repository
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {

    // 岗位列表分页查询
    IPage<SysPost> selectPage(Page<SysPost> pageParam, @Param("vo")SysPostQueryVo sysPostQueryVo);

}
