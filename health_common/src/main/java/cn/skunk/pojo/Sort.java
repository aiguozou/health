package cn.skunk.pojo;

import java.io.Serializable;

public class Sort implements Serializable {
    private Integer id;
    private String sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
