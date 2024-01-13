package oah.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import oah.project.model.system.SysDept;
import oah.project.model.system.SysPost;
import oah.project.model.vo.SysPostQueryVo;

/**
 * @ClassName SysPostService
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.12 11:19
 * @Version 1.0
 */
public interface SysPostService extends IService<SysPost> {
    // 岗位列表分页查询
    public IPage<SysPost> selectPageByVo(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo);

    // 更改岗位状态
    public void updatePostStatus(String id, Integer status);

    // 根据

}
