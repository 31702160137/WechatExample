package com.example.a104168.wechatexample.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a104168.wechatexample.Beans.ChatBenas;
import com.example.a104168.wechatexample.R;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {
    private List<ChatBenas>  messages; //聊天信息集合
    private String          name;     //当前用户的name值
    private LayoutInflater layoutInflater;
    public MyListViewAdapter(List<ChatBenas> messages, Context context, String name){
        this.messages = messages;
        this.name     = name;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessagesHolder messagesHolder;
                //判断当前用户使用不同的信息视图
                if(name.equals(messages.get(position).getName())) {
                        convertView = layoutInflater.inflate(R.layout.chat_list_item_right, null);
                        messagesHolder = new MessagesHolder();
                        messagesHolder.time = convertView.findViewById(R.id.chat_tv_time);
                        messagesHolder.chat = convertView.findViewById(R.id.chat_tv_chat);
                        messagesHolder.name = convertView.findViewById(R.id.chat_tv_name);
                        messagesHolder.head = convertView.findViewById(R.id.chat_img);
                        messagesHolder.head.setImageResource(R.drawable.tx2);
                }else{
                        convertView = layoutInflater.inflate(R.layout.chat_list_item_left, null);
                        messagesHolder = new MessagesHolder();
                        messagesHolder.time = convertView.findViewById(R.id.chat_tv_time);
                        messagesHolder.chat = convertView.findViewById(R.id.chat_tv_chat);
                        messagesHolder.name = convertView.findViewById(R.id.chat_tv_name);
                        messagesHolder.head = convertView.findViewById(R.id.chat_img);
                         messagesHolder.head.setImageResource(R.drawable.icon_head);
                }
                messagesHolder.name.setText(messages.get(position).getName());
                messagesHolder.chat.setText(messages.get(position).getChat());
                messagesHolder.time.setText(messages.get(position).getTime());

                return convertView;
    }
    class MessagesHolder{
        TextView time;
        TextView name;
        TextView chat;
        ImageView head;
    }
}
