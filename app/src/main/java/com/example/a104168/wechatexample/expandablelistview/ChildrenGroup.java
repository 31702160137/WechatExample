package com.example.a104168.wechatexample.expandablelistview;

import java.util.ArrayList;
import java.util.List;

public class ChildrenGroup {
    private Integer id;
    private String name;

    //二级列表集合
    public List<ChildrenItem> ChildrenItems = new ArrayList<>();
    public ChildrenGroup(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    // 添加数据源对象
    public void addChild(ChildrenItem ChildrenItem) {
        ChildrenItem.setPid(getId());
        ChildrenItems.add(ChildrenItem);
    }
    // 指定数据值
    public void addChild(Integer id, String name) {
        ChildrenItem childrenItem = new ChildrenItem(id, name);
        childrenItem.setPid(getId());
        ChildrenItems.add(childrenItem);
    }
    public ChildrenGroup() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ChildrenItem> getChildrenItems() {
        return ChildrenItems;
    }
    public void setChildrenItems(List<ChildrenItem> ChildrenItems) {
        this.ChildrenItems = ChildrenItems;
    }
}


