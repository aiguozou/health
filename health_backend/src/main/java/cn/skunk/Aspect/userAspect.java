package cn.skunk.Aspect;


import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.Result;
import cn.skunk.log.MyLog;
import cn.skunk.pojo.UserLog;
import cn.skunk.service.UserLogService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class userAspect {
    @Reference
    private UserLogService userLogService;

    @Pointcut("@annotation(cn.skunk.log.MyLog)")
    public void logPointCut() {
    }

    @Autowired
    private HttpServletRequest request;

    //切面 配置增强
    @AfterReturning("logPointCut()")
    public void saveUserLog(JoinPoint joinPoint) {
        UserLog userLog = new UserLog();

        //从切面织入点处通过反射获取织入点方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        MyLog myLog = method.getAnnotation(MyLog.class);
        //获取具体操作 存入对象
        if (myLog != null) {
            String value = myLog.value();
            userLog.setOperation(value);
        }
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();

        userLog.setMethod(className + ":" + methodName);
        //格式化时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());

        userLog.setCreateTime(time);

        //获取用户名
        /*User user = (User) request.getSession().getAttribute("user");*/
        org.springframework.security.core.userdetails.User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user!=null) {
            String username = user.getUsername();
            userLog.setUsername(username);
        }


        //获取用户IP
        userLog.setIp(getClientIp());
        //调用service保存实体到数据库
        System.out.println(userLog.getIp());
        userLogService.save(userLog);
    }

    private String getClientIp() {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
