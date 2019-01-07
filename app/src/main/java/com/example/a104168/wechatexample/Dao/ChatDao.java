package com.example.a104168.wechatexample.Dao;

import com.example.a104168.wechatexample.Beans.ChatBenas;

import java.util.List;

public interface ChatDao<T> {

    /**
     * 插入一条记录
     *
     * @param list
     * @return
     */
    boolean insertChat(List<T> list);

    /**
     * 删除全部记录
     * @return
     */
    boolean deleteAll();
    /**
     * 查询记录
     * @return
     */
    List<T> queryChats();


}
