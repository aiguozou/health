
/**
 * 运动知识库
 */
package cn.skunk.pojo;

import java.io.Serializable;

public class Knowledge implements Serializable {

  private int id;
  private  String sportname; //运动名称
  private  String   sporttime;//运动时间
  private  String sportrate;//运动频率
  private  String age;//适用年龄
  private  String attention;//注意事项
  private  String sex;//性别

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname;
    }

    public String getSporttime() {
        return sporttime;
    }

    public void setSporttime(String sporttime) {
        this.sporttime = sporttime;
    }

    public String getSportrate() {
        return sportrate;
    }

    public void setSportrate(String sportrate) {
        this.sportrate = sportrate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
