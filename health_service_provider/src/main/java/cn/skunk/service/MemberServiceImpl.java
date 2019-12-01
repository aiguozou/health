package cn.skunk.service;

import cn.skunk.dao.MemberDao;
import cn.skunk.pojo.Member;
import cn.skunk.utils.MD5Utils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    public void add(Member member) {
        String password = member.getPassword();
        if (password!=null) {
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberDao.add(member);
    }

    public List<Integer> findMemberCountByMonth(List<String> months) {
        List<Integer> memberCount=new ArrayList<>();
        for (String month : months) {
            String date = month + ".31";
            Integer count = memberDao.findMemberCountBeforeDate(date);
            memberCount.add(count);
        }
        return memberCount;
    }
}
