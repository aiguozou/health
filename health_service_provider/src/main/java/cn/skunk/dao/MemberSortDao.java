package cn.skunk.dao;


import cn.skunk.pojo.OBJECT_MEMBER;
import cn.skunk.pojo.Sort;
import com.github.pagehelper.Page;


import java.util.List;
import java.util.Map;

public interface MemberSortDao {
    Page<OBJECT_MEMBER> findPage(String queryString);

    List<Sort> findAll();

    OBJECT_MEMBER findById(Integer id);

    void edit(OBJECT_MEMBER object_member);



    void delete_sortByMemberId(Integer id);

    void insert_sort(Map map);

}
