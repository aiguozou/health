package cn.skunk.service;

import cn.skunk.dao.MemberPCDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Member;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = MemberPCService.class)
@Transactional
public class MemberPCServiceImpl implements MemberPCService {
    @Autowired
    private MemberPCDao memberPCDao;

    //查询会员信息
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage, pageSize);
        Page<Member> page = memberPCDao.pageQuery(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    //添加新会员
    @Override
    public void add(Member member) {
        memberPCDao.add(member);
    }

    //根据ID查询会员信息
    @Override
    public Member findById(Integer id) {
        return memberPCDao.findById(id);
    }

    //修改会员信息
    @Override
    public void edit(Member member) {
        memberPCDao.edit(member);
    }

    //删除会员
    @Override
    public void deleteById(Integer id) {
        deleteByMemberIdAndOrder(id);
        memberPCDao.deleteById(id);
    }

    //查询预约套餐会员信息

    public PageResult findUpload(QueryPageBean queryPage) {
        Integer currentPage = queryPage.getCurrentPage();
        Integer pageSize = queryPage.getPageSize();
        String queryString = queryPage.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Map<String,Object>> page = memberPCDao.findUpload(queryString);
         List<Map<String,Object>> list = page.getResult();
        long total = page.getTotal();
        return new PageResult(total, list);
    }

    //删除会员与套餐的关系
    public void deleteByMemberIdAndOrder(Integer id) {
        memberPCDao.deleteByMemberIdAndOrder(id);
    }
}
