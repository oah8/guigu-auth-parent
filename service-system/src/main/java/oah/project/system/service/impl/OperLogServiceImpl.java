package oah.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import oah.project.common.result.Result;
import oah.project.model.system.SysOperLog;
import oah.project.model.vo.SysOperLogQueryVo;
import oah.project.system.mapper.OperLogMapper;
import oah.project.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OperLogServiceImpl
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.28 20:36
 * @Version 1.0
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        operLogMapper.insert(sysOperLog);
    }

    // 操作日志分页查询
    @Override
    public IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo) {
        Page<SysOperLog> pageParam = new Page<>(page, limit);

        // 获取条件值
        String title = sysOperLogQueryVo.getTitle();
        String operName = sysOperLogQueryVo.getOperName();
        String createTimeBegin = sysOperLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysOperLogQueryVo.getCreateTimeEnd();

        // 封装参数
        QueryWrapper<SysOperLog> wrapper = new QueryWrapper<>();
        if(!StringUtils.isNullOrEmpty(title)) {
            wrapper.like("title", title);
        }
        if(!StringUtils.isNullOrEmpty(operName)) {
            wrapper.like("oper_name", operName);
        }
        if(!StringUtils.isNullOrEmpty(createTimeBegin)) {
            wrapper.ge("create_time", createTimeBegin);
        }
        if(!StringUtils.isNullOrEmpty(createTimeEnd)) {
            wrapper.le("create_time", createTimeEnd);
        }

        // 调用mapper方法实现分页条件查询
        IPage<SysOperLog> sysOperLogPage = operLogMapper.selectPage(pageParam, wrapper);
        return sysOperLogPage;
    }
}
