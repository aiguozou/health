package cn.skunk.pojo;

import java.io.Serializable;
import java.util.List;

public class OBJECT_MEMBER extends Member implements Serializable {

    private List<String> sort;

    public List<String> getSort() {
        return sort;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }
}
