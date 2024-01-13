package oah.project.system.utils;

import oah.project.model.system.SysDept;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DeptHelper
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.19 20:43
 * @Version 1.0
 */
public class DeptHelper {


    // 构建树形结构
    public static List<SysDept> buildTree(List<SysDept> sysDeptList) {
        // 创建集合封装最终数据
        ArrayList<SysDept> trees = new ArrayList<>();

        // 遍历所有菜单集合
        for(SysDept sysDept : sysDeptList) {
            // 找到递归的入口， parentId = 0
            if(sysDept.getParentId().longValue() == 0) {
                trees.add(findChildren(sysDept, sysDeptList));
            }
        }
        return trees;
    }

    // 从根节点进行递归查询，查询子节点
    // 判断 id = parentId 是否相同，如果相同是子节点，进行数据封装
    private static SysDept findChildren(SysDept sysDept, List<SysDept> treeNodes) {
        // 数据初始化
        sysDept.setChildren(new ArrayList<SysDept>());

        // 遍历递归查找
        for(SysDept it : treeNodes) {
            // 获取当前菜单id
            // 比对
            if(Long.parseLong(sysDept.getId()) == it.getParentId()) {
                if(sysDept.getChildren() == null) {
                    sysDept.setChildren(new ArrayList<>());
                }
                sysDept.getChildren().add(findChildren(it, treeNodes));
            }
        }

        return sysDept;

    }
}
