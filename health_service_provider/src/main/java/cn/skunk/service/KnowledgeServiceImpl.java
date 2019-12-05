package cn.skunk.service;
import cn.skunk.dao.KnowledgeDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Knowledge;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeDao knowledgeDao;

    public PageResult findPage(QueryPageBean queryPageBean) {

        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);

        Page<Knowledge> page = knowledgeDao.selectByCondition(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(Knowledge knowledge) {
        knowledgeDao.add(knowledge);
    }

    @Override
    public Knowledge findById(Integer id) {
        return knowledgeDao.findById(id);
    }

    @Override
    public void edit(Knowledge knowledge) {
        knowledgeDao.edit(knowledge);
    }

    @Override
    public void deleteById(Integer id) {
        knowledgeDao.deleteById(id);
    }
}
