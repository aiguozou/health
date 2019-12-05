package cn.skunk.service;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Knowledge;

public interface KnowledgeService {
    PageResult findPage(QueryPageBean queryPageBean);

    void add(Knowledge knowledge);

    Knowledge findById(Integer id);

    void edit(Knowledge knowledge);

    void deleteById(Integer id);
}
