package cn.skunk.dao;
import cn.skunk.pojo.Knowledge;
import com.github.pagehelper.Page;


public interface KnowledgeDao {
    Page<Knowledge> selectByCondition(String queryString);
    void add(Knowledge knowledge);

    Knowledge findById(Integer id);

    void edit(Knowledge knowledge);

    void deleteById(Integer id);

}
