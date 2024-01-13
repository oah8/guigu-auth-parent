package oah.project.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import oah.project.common.result.Result;
import oah.project.model.system.SysDept;
import oah.project.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysDeptController
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.11 23:43
 * @Version 1.0
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/admin/system/sysDept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    // 部门列表
    @ApiOperation("部门列表")
    @GetMapping("/findDeptLists")
    public Result findDeptLists() {
        List<SysDept> deptLists = sysDeptService.findDeptLists();
        return Result.ok(deptLists);
    }

    // 更改部门状态
    @ApiOperation("更改部门状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status) {
        sysDeptService.updateStatus(id, status);
        return Result.ok();
    }

    // 添加部门
    @ApiOperation("添加部门")
    @PostMapping("/save")
    public Result save(@RequestBody SysDept sysDept) {
        boolean is_Success = sysDeptService.save(sysDept);
        if(is_Success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    // 删除部门
    @ApiOperation("删除部门")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        sysDeptService.removeDeptById(id);
        return Result.ok();
    }

    // 修改
    @ApiOperation("修改部门")
    @PostMapping("/update")
    public Result update(@RequestBody SysDept sysDept) {
        sysDeptService.updateById(sysDept);
        return Result.ok();
    }

    // 根据id查询
    @ApiOperation("根据id查询部门")
    @GetMapping("/findDept/{id}")
    public Result findDept(@PathVariable String id) {
        SysDept sysDept = sysDeptService.getById(id);
        return Result.ok(sysDept);
    }


}
