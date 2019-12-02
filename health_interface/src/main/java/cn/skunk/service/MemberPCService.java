package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Member;



public interface MemberPCService {
    PageResult pageQuery(QueryPageBean queryPageBean);

    void add(Member member);

    Member findById(Integer id);

    void edit(Member member);

    void deleteById(Integer id);
}
