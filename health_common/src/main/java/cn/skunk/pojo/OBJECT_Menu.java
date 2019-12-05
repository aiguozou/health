package cn.skunk.pojo;

import java.io.Serializable;
import java.util.List;

public class OBJECT_Menu  implements Serializable {
    //    "path": "/2-3",
////            "title": "会员统计",
////            "linkUrl": "all-item-list.html",
////            "children": []
    String path;
    String title;
    String icon;
    String linkUrl;
    List<OBJECT_Menu> children;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public List<OBJECT_Menu> getChildren() {
        return children;
    }

    public void setChildren(List<OBJECT_Menu> children) {
        this.children = children;
    }
}
