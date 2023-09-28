package oah.project.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import oah.project.common.result.Result;
import oah.project.model.system.SysRole;
import oah.project.model.vo.AssginRoleVo;
import oah.project.model.vo.SysRoleQueryVo;
import oah.project.system.annotation.Log;
import oah.project.system.enums.BusinessType;
import oah.project.system.exception.GuiguException;
import oah.project.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysRoleController
 * @Description TODO
 * @Author _oah
 * @Date 2023.08.29 21:57
 * @Version 1.0
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    // http://localhost:8800/admin/system/sysRole/findAll

    @ApiOperation("获取用户的角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation("用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }


    // 批量删除
    // 多个id值[1, 2, 3]
    // json数组格式 --- java的list集合
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {
        boolean isSuccess = sysRoleService.removeByIds(ids);
        if(isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    // 修改最终修改
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation("最终修改")
    @PostMapping("/update")
    public Result updateRole(@RequestBody SysRole sysRole) {
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if(isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }


    // 修改-根据id查询
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("根据id查询")
    @GetMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    // 添加
    // @RequestBody 不能使用get提交方式
    // 传递json格式数据，把json格式数据封装到对象里面{...}
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result saveRole(@RequestBody SysRole sysRole) {
        boolean isSuccess = sysRoleService.save(sysRole);
        if(isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    // 条件查询
    // page 当前页 limit 每页记录数
    @ApiOperation("条件分页查询")
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @GetMapping("/{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysRoleQueryVo sysRoleQueryVo) {
        // 创建page对象
        Page<SysRole> pageParam = new Page<>(page, limit);
        // 调用service方法
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        // 返回
        return Result.ok(pageModel);

    }





    // 逻辑删除接口
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeRole(@PathVariable Long id) {
        // 调用方法
        boolean isSuccess = sysRoleService.removeById(id);
        if(isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    // 查询所有记录
    @ApiOperation("查询所有记录接口")
    @GetMapping("findAll")
    public Result findAllRole() {

//        try {
//            // TODO 模拟异常
//            int i = 9 / 0;
//        } catch (Exception e) {
//            // 手动抛出异常
//            throw new GuiguException(20001, "执行自定义异常处理");
//        }
        // 调用service
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }







}
