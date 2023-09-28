package oah.project.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import oah.project.common.result.Result;
import oah.project.model.system.SysOperLog;
import oah.project.model.vo.SysOperLogQueryVo;
import oah.project.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysOperLogController
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.28 21:22
 * @Version 1.0
 */
@Api(value = "SysOperLog管理", tags = "SysOperLog管理")
@RestController
@RequestMapping(value="/admin/system/sysOperLog")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysOperLogController {

    @Autowired
    private OperLogService operLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
                        @ApiParam(name = "sysOperLogVo", value = "查询对象", required = true)SysOperLogQueryVo sysOperLogQueryVo) {
        IPage<SysOperLog> pageModel = operLogService.selectPage(page, limit, sysOperLogQueryVo);
        return Result.ok(pageModel);
    }


}
