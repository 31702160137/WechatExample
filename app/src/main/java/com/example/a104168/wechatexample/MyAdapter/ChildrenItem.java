package com.example.a104168.wechatexample.MyAdapter;

public class ChildrenItem {
    private String id;
    private String name;
    private String pid;

    public ChildrenItem() {
    }
    public ChildrenItem(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    @Override
    public String toString() {
        return "num=" + id +
                ", name = '" + name + '\'';
    }
}

