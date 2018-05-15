package com.guochaojava.util;

import com.guochaojava.model.Permission;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author guochao
 * @since 1.0.0
 */
public class TreeObjectUtil {
    /**
     * @param list 需要转换的list（包含parentId的无限极list数据）
     * @return List<MenuObject>
     * @throws
     * @Title: getChildMenuObjects  获取树形结构list
     * @Description: TODO
     */
    public List<Permission> getChildMenuObjects(List<Permission> list) {
        List<Permission> returnList = new ArrayList<Permission>();
        for (Iterator<Permission> iterator = list.iterator(); iterator.hasNext(); ) {
            Permission t = (Permission) iterator.next();
            //根据父级ID 遍历childen list
            if (t instanceof Permission) {

                if (t.getPid().equals(0)) {
                    recursionFn(list, t);
                    returnList.add(t);
                }
            }

        }
        return returnList;
    }

    /**
     * @param list
     * @param t
     * @return void
     * @throws
     * @Title: recursionFn
     * @Description: TODO 递归处理childen数据
     */
    private void recursionFn(List<Permission> list, Permission t) {
        List<Permission> childList = getChildList(list, t);
        t.setSub(childList);
        for (Permission tChild : childList) {
            if (hasChild(list, tChild)) {
                Iterator<Permission> it = childList.iterator();
                while (it.hasNext()) {
                    Permission n = (Permission) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }


    /**
     * @param list
     * @param t
     * @return List<MenuObject>
     * @throws
     * @Title: getChildList
     * @Description: TODO 获取子节点list数据
     */
    private List<Permission> getChildList(List<Permission> list, Permission t) {

        List<Permission> tlist = new ArrayList<Permission>();
        Iterator<Permission> it = list.iterator();
        while (it.hasNext()) {
            Permission n = (Permission) it.next();
            if (n.getPid().equals(t.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }


    /**
     * @param list
     * @param t
     * @return boolean
     * @throws
     * @Title: hasChild
     * @Description: TODO 判断是否有子节点
     */
    private boolean hasChild(List<Permission> list, Permission t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}