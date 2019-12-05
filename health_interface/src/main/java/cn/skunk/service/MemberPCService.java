package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Member;

import java.util.List;
import java.util.Map;


public interface MemberPCService {
    PageResult pageQuery(QueryPageBean queryPageBean);

    void add(Member member);

    Member findById(Integer id);

    void edit(Member member);

    void deleteById(Integer id);


    PageResult findUpload(QueryPageBean queryPageBean);
}
