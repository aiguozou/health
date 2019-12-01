package cn.skunk;

import cn.skunk.constant.RedisConstant;
import cn.skunk.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;
    public void clearImg(){
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set!=null) {
            for (String ImgName : set) {
                QiniuUtils.deleteFileFromQiniu(ImgName);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,ImgName);
                System.out.println("清理垃圾图片"+ImgName);
            }
        }
    }
}
