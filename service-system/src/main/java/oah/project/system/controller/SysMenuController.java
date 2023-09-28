package oah.project.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import oah.project.common.result.Result;
import oah.project.model.system.SysMenu;
import oah.project.model.vo.AssginMenuVo;
import oah.project.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author _oah
 * @since 2023-09-18
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


    @ApiOperation("给角色分配菜单权限")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }


    // 更改菜单状态
    @ApiOperation("更改菜单状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status) {
        sysMenuService.updateStatus(id, status);
        return Result.ok();
    }


    // 根据角色分配菜单
    @ApiOperation("根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<SysMenu> list = sysMenuService.findMenuByRoleId(roleId);
        return Result.ok(list);
    }


    // 菜单列表（树形）
    @ApiOperation("菜单列表")
    @GetMapping("/findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    // 添加菜单
    @ApiOperation("添加菜单")
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        boolean is_Success = sysMenuService.save(sysMenu);
        if(is_Success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    // 根据id查询
    @ApiOperation("根据id查询菜单")
    @GetMapping("/findNode/{id}")
    public Result findNode(@PathVariable String id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return Result.ok(sysMenu);
    }

    // 修改
    @ApiOperation("修改菜单")
    @PostMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Result.ok();
    }

    // 删除菜单
    @ApiOperation("删除菜单")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        sysMenuService.removeMenuById(id);
        return Result.ok();
    }

}

