package cn.skunk.service;

import cn.skunk.dao.MenuDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Menu;
import cn.skunk.pojo.OBJECT_Menu;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Menu> page = menuDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public void add(Menu menu) {
        menuDao.add(menu);
    }

    /*
    [
                {
                    "path": "1",
                    "title": "工作台",
                    "icon": "fa-dashboard",
                    "children": []
                },
                {
                    "path": "2",
                    "title": "会员管理",
                    "icon": "fa-user-md",
                    "children": [
                        {
                            "path": "/2-1",
                            "title": "会员档案",
                            "linkUrl": "member.html",
                            "children": []
                        },
                        {
                            "path": "/2-2",
                            "title": "体检上传",
                            "children": []
                        },
                        {
                            "path": "/2-3",
                            "title": "会员统计",
                            "linkUrl": "all-item-list.html",
                            "children": []
                        },
                    ]
                }
                ]
     */
    @Override
    public List<OBJECT_Menu > getMenu() {
        List<OBJECT_Menu > list = new ArrayList<>();
        Integer[] levelRoot = menuDao.selectLevelRoot();//查出根节点的所有Id
        for (Integer i : levelRoot) {
            OBJECT_Menu item = menuDao.selectMenuById(i);//根据根节点Menu
            list.add(item);
        }
        return list;
    }

    @Override
    public void delete(Integer id) {
        menuDao.delete(id);
        menuDao.deleteMenuByParentId(id);

    }


//    public void add(Menu menu, Integer[] roleIds) {
//        menuDao.add(menu);
//        Integer menuId = menu.getId();
//        if (roleIds!=null&&roleIds.length>0) {
//            for (Integer roleId : roleIds) {
//                Map<String, Integer> map = new HashMap<>();
//                map.put("menuId",menuId);
//                map.put("roleId",roleId);
//                menuDao.setMenuAndRole(map);
//            }
//        }
//    }

}
