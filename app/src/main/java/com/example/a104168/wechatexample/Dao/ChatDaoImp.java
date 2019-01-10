package com.example.a104168.wechatexample.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a104168.wechatexample.Beans.ChatBeans;

import java.util.ArrayList;
import java.util.List;

public class ChatDaoImp implements ChatDao {
    MyHelper myHelper;
    public ChatDaoImp(Context context){
        myHelper = new MyHelper(context);
    }
    /**插入聊天信息
     * @param list 聊天信息集合
     * @return
     * */
    @Override
    public boolean insertChat(List list) {
        List<ChatBeans> chatBenas = list;
        SQLiteDatabase db = myHelper.getWritableDatabase();
        for (int i = 0; i <= chatBenas.size()-1; i++) {
            ContentValues values = new ContentValues();
            values.put("name",chatBenas.get(i).getName());
            values.put("time",chatBenas.get(i).getTime());
            values.put("chat",chatBenas.get(i).getChat());
            db.insert("tb_chats",null,values);
        }
        db.close();
        return true;
    }
    @Override
    public boolean deleteAll() {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        db.execSQL("delete from tb_chats");
        return true;
    }

    /**查询聊天信息
     *
     * @return list 查询到的聊天集合
     */
    @Override
    public List queryChats() {
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor =  db.query("tb_chats",null,null,null,null,null,null,null);
        List<ChatBeans> list = new ArrayList<>();
        ChatBeans chatBeans;
        if(cursor.getCount() == 0){
            return null;
        }else{
            cursor.moveToFirst();
            chatBeans = new ChatBeans();
            chatBeans.setName(cursor.getString(1));
            chatBeans.setTime(cursor.getString(2));
            chatBeans.setChat(cursor.getString(3));
            list.add(chatBeans);
        }
        while (cursor.moveToNext()){
            chatBeans = new ChatBeans();
            chatBeans.setName(cursor.getString(1));
            chatBeans.setTime(cursor.getString(2));
            chatBeans.setChat(cursor.getString(3));
            list.add(chatBeans);
        }
       return list;
    }
}
