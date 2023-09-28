package oah.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import oah.project.model.system.SysOperLog;
import oah.project.model.vo.SysOperLogQueryVo;

/**
 * @ClassName OperLogService
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.28 20:34
 * @Version 1.0
 */
public interface OperLogService {

    public void saveSysLog(SysOperLog sysOperLog);

    // 操作日志分页查询
    IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);

}
