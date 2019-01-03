package com.example.a104168.wechatexample.expandablelistview;

public class ChildrenItem {
    private Integer id;
    private String name;
    private Integer pid;

    public ChildrenItem() {
    }
    public ChildrenItem(Integer id, String name) {
        this.id = id;
        this.name = name;
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
    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    @Override
    public String toString() {
        return "ChildrenItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                '}';
    }
}

