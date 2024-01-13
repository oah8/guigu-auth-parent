package oah.project.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import oah.project.model.system.SysPost;
import oah.project.model.vo.SysPostQueryVo;
import oah.project.system.mapper.SysPostMapper;
import oah.project.system.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysPostServiceImpl
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.12 15:35
 * @Version 1.0
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

    @Autowired
    private SysPostMapper sysPostMapper;

    // 岗位列表
    @Override
    public IPage<SysPost> selectPageByVo(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo) {
        return baseMapper.selectPage(pageParam, sysPostQueryVo);
    }

    // 更新岗位状态
    @Override
    public void updatePostStatus(String id, Integer status) {
        // 根据岗位id查询
        SysPost sysPost = baseMapper.selectById(id);
        // 设置修改状态
        sysPost.setStatus(status);
        // 调用方法更新
        baseMapper.updateById(sysPost);
    }
}
