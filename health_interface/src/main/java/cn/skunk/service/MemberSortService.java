package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.OBJECT_MEMBER;
import cn.skunk.pojo.Sort;

import java.util.List;

public interface MemberSortService {

    PageResult findPage(QueryPageBean queryPageBean);

    List<Sort> findAll();

    OBJECT_MEMBER findById(Integer id);



    void edit(OBJECT_MEMBER object_member, Integer[] checkList);
}
