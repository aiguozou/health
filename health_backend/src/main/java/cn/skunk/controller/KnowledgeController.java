package cn.skunk.controller;
import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Knowledge;
import cn.skunk.service.KnowledgeService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Reference
    private KnowledgeService knowledgeService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {

        PageResult pageResult= knowledgeService.findPage(queryPageBean);

        return pageResult;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Knowledge knowledge){

        try {
            knowledgeService.add(knowledge);
            return new Result(true, MessageConstant.ADD_KNOWLEDGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_KNOWLEDGE_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Knowledge knowledge=  knowledgeService.findById(id);
            System.out.println(knowledge);

        return new Result(true,MessageConstant.QUERY_KNOWLEDGE_SUCCESS,knowledge);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_KNOWLEDGE_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Knowledge knowledge){
        try {
            knowledgeService.edit(knowledge);
        return  new Result(true,MessageConstant.EDIT_KNOWLEDGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.EDIT_KNOWLEDGE_FAIL);
        }
    }

    @RequestMapping("/delete")

    public  Result delete(Integer id){
        try {
            knowledgeService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_KNOWLEDGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.DELETE_KNOWLEDGE_FAIL);
        }

    }
}
