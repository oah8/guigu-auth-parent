package oah.project.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import oah.project.common.result.Result;
import oah.project.model.system.SysLoginLog;
import oah.project.model.vo.SysLoginLogQueryVo;
import oah.project.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysLoginController
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.28 20:53
 * @Version 1.0
 */
@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysLoginController {


    @Autowired
    private LoginLogService loginLogService;

    // 条件分页查询登录日志
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@PathVariable long page,
                        @PathVariable long limit,
                        SysLoginLogQueryVo sysLoginLogQueryVo) {
        IPage<SysLoginLog> pageModel = loginLogService.selectPage(page, limit, sysLoginLogQueryVo);
        return Result.ok(pageModel);
    }


}
