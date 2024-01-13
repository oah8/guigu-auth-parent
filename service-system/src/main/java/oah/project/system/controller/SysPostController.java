package oah.project.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import oah.project.common.result.Result;
import oah.project.model.system.SysPost;
import oah.project.model.vo.SysPostQueryVo;
import oah.project.system.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysPostController
 * @Description TODO
 * @Author _oah
 * @Date 2024.01.12 15:46
 * @Version 1.0
 */
@Api(tags = "岗位管理接口")
@RestController
@RequestMapping("/admin/system/sysPost")
public class SysPostController {


    @Autowired
    private SysPostService sysPostService;

    @ApiOperation("更改岗位状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status) {
        sysPostService.updatePostStatus(id, status);
        return Result.ok();
    }


    @ApiOperation("岗位列表")
    @GetMapping("/{page}/{limit}")
    public Result postList(@PathVariable Long page,
                           @PathVariable Long limit,
                           SysPostQueryVo sysPostQueryVo) {
        // 创建page对象
        Page<SysPost> pageParam = new Page<>(page, limit);
        // 调用service方法
        IPage<SysPost> pageModel = sysPostService.selectPageByVo(pageParam, sysPostQueryVo);
        return Result.ok(pageModel);
    }

    // 添加
    @ApiOperation("添加岗位")
    @PostMapping("/save")
    public Result save(@RequestBody SysPost sysPost) {
        boolean flag = sysPostService.save(sysPost);
        if(flag) {
            return Result.ok();
        } else {
            return Result.ok();
        }
    }

    // 修改岗位
    @ApiOperation("修改岗位")
    @PostMapping("/update")
    public Result update(@RequestBody SysPost sysPost) {
        boolean flag = sysPostService.updateById(sysPost);
        if(flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

    // 删除岗位
    @ApiOperation("删除岗位")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        boolean flag = sysPostService.removeById(id);
        if(flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    // 根据id查询岗位
    @ApiOperation("根据id查询")
    @GetMapping("/getPost/{id}")
    public Result getPost(@PathVariable String id) {
        SysPost sysPost = sysPostService.getById(id);
        return Result.ok(sysPost);
    }



}
