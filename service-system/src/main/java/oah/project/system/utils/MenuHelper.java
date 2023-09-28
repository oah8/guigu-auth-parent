package oah.project.system.utils;

import oah.project.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MenuHelper
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.19 20:43
 * @Version 1.0
 */
public class MenuHelper {


    // 构建树形结构
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        // 创建集合封装最终数据
        ArrayList<SysMenu> trees = new ArrayList<>();

        // 遍历所有菜单集合
        for(SysMenu sysMenu : sysMenuList) {
            // 找到递归的入口， parentId = 0
            if(sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    // 从根节点进行递归查询，查询子节点
    // 判断 id = parentId 是否相同，如果相同是子节点，进行数据封装
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        // 数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());

        // 遍历递归查找
        for(SysMenu it : treeNodes) {
            // 获取当前菜单id
//            String id = sysMenu.getId();
//            long cid = Long.parseLong(id);
//            // 获取所有菜单parentId
//            Long parentId = it.getParentId();
            // 比对
            if(Long.parseLong(sysMenu.getId()) == it.getParentId()) {
                if(sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it, treeNodes));
            }
        }

        return sysMenu;

    }
}
