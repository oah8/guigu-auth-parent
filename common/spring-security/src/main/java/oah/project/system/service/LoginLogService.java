package oah.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import oah.project.model.system.SysLoginLog;
import oah.project.model.vo.SysLoginLogQueryVo;

/**
 * @ClassName LoginLogService
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.28 19:42
 * @Version 1.0
 */
public interface LoginLogService {

    // 登录日志
    public void recordLoginLog(String username, Integer status,
                               String ipaddr, String message);

    // 条件分页查询登录日志
    IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo);
}
