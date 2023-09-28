package oah.project.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import oah.project.common.result.Result;
import oah.project.common.utils.MD5;
import oah.project.model.system.SysUser;
import oah.project.model.vo.SysUserQueryVo;
import oah.project.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author _oah
 * @since 2023-09-17
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;


    @ApiOperation("更改用户状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                              @PathVariable Integer status) {
        sysUserService.updateStatus(id, status);
        return Result.ok();
    }


    @ApiOperation("用户列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       SysUserQueryVo sysUserQueryVo) {
        // 创建page对象
        Page<SysUser> pageParam = new Page<>(page, limit);
        // 调用service方法
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, sysUserQueryVo);

        return Result.ok(pageModel);
    }

    @ApiOperation("添加用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser sysUser) {
        // 把输入的密码进行加密MD5
        String encrypt = MD5.encrypt(sysUser.getPassword());
        sysUser.setPassword(encrypt);

        boolean is_Success = sysUserService.save(sysUser);
        if(is_Success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id查询")
    @GetMapping("/getUser/{id}")
    public Result getUser(@PathVariable String id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }


    @ApiOperation("修改用户")
    @PostMapping("/update")
    public Result update(@RequestBody SysUser sysUser) {
        boolean is_Success = sysUserService.updateById(sysUser);
        if(is_Success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        boolean is_Success = sysUserService.removeById(id);
        if(is_Success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }















}
























