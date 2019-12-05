package cn.skunk.service;

import cn.skunk.dao.MemberSortDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.OBJECT_MEMBER;
import cn.skunk.pojo.Sort;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service(interfaceClass = MemberSortService.class)
@Transactional
public class MemberSortServiceImpl implements MemberSortService {
    @Autowired
    private MemberSortDao memberSortDao;

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<OBJECT_MEMBER> page = memberSortDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public List<Sort> findAll() {
        return memberSortDao.findAll();
    }

    @Override
    public OBJECT_MEMBER findById(Integer id) {
        return memberSortDao.findById(id);
    }

    @Override
    public void edit(OBJECT_MEMBER object_member, Integer[] checkList) {
        memberSortDao.delete_sortByMemberId(object_member.getId());
        memberSortDao.edit(object_member);
        HashMap map;
        for (Integer integer : checkList) {
            map = new HashMap<>();
            map.put("member_id", object_member.getId());
            map.put("sort_id", integer);
            memberSortDao.insert_sort(map);
        }
    }


}
