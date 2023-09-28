package oah.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import oah.project.model.system.SysLoginLog;
import oah.project.model.vo.SysLoginLogQueryVo;
import oah.project.system.mapper.SysLoginLogMapper;
import oah.project.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginLogServiceImpl
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.28 19:46
 * @Version 1.0
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setStatus(status);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);
        sysLoginLogMapper.insert(sysLoginLog);
    }


    // 条件分页查询登录日志
    @Override
    public IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo) {
        // 创建page对象
        Page<SysLoginLog> pageParam = new Page(page, limit);
        // 获取条件值
        String username = sysLoginLogQueryVo.getUsername();
        String createTimeBegin = sysLoginLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysLoginLogQueryVo.getCreateTimeEnd();
        // 封装条件
        QueryWrapper<SysLoginLog> wrapper = new QueryWrapper<>();
        if(!StringUtils.isNullOrEmpty(username)) {
            wrapper.like("username", username);
        }
        if(!StringUtils.isNullOrEmpty(createTimeBegin)) {
            wrapper.ge("create_time", createTimeBegin);
        }
        if(!StringUtils.isNullOrEmpty(createTimeEnd)) {
            wrapper.le("create_time", createTimeEnd);
        }
        // 调用mapper方法
        IPage<SysLoginLog> pageModel = sysLoginLogMapper.selectPage(pageParam, wrapper);

        return pageModel;
    }
}
