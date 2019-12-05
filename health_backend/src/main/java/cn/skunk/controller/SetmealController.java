package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.constant.RedisConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Setmeal;
import cn.skunk.service.SetmealService;
import cn.skunk.utils.QiniuUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){
        String originalFilename = imgFile.getOriginalFilename();
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(lastIndexOf - 1);
        String fileName = UUID.randomUUID().toString() + substring;
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        try {
            setmealService.add(setmeal,checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.findPage(queryPageBean);
    }
    //通过ID查询 回显套餐基本信息
    @RequestMapping("/findById")
    public  Result findById(Integer id){

        try {
            Setmeal setmeal = setmealService.findById(id);
            return  new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }
    //查询检查组基本信息回显
    @RequestMapping("/checkgroupIdsBysetmealId")
    public Result checkgroupIdsBysetmealId(Integer id){
        try {
            List<Integer> checkgroupIds= setmealService.findCheckgroupIds(id);
            return  new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    //编辑套餐

    @RequestMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        try {
            setmealService.edit(setmeal,checkgroupIds);
            return  new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.EDIT_SETMEAL_FAIL);
        }

    }

    //删除套餐

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            setmealService.delete(id);
            return  new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }
}
