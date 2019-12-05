package cn.skunk.dao;

import cn.skunk.pojo.Member;
import com.github.pagehelper.Page;

import java.util.List;

public interface MemberPCDao {
    Page<Member> pageQuery(String queryString);

    void add(Member member);

    Member findById(Integer id);

    void edit(Member member);

    void deleteById(Integer id);

    void deleteByMemberIdAndOrder(Integer id);
    
}
