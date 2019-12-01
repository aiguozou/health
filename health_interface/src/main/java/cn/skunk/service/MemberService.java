package cn.skunk.service;

import cn.skunk.pojo.Member;

import java.util.List;

public interface MemberService {
    public Member findByTelephone(String telephone);
    public void add(Member member);
    public List<Integer> findMemberCountByMonth(List<String> months);
}
