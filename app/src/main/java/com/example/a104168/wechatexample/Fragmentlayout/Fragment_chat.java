package com.example.a104168.wechatexample.Fragmentlayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a104168.wechatexample.Beans.ChatBenas;
import com.example.a104168.wechatexample.Dao.ChatDaoImp;
import com.example.a104168.wechatexample.MainActivity;
import com.example.a104168.wechatexample.MyAdapter.MyListViewAdapter;
import com.example.a104168.wechatexample.OkHttp.OkHttpUtil;
import com.example.a104168.wechatexample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Fragment_chat extends Fragment {
    private EditText            et_chat;
    private Button              btn_send;
    private ChatBenas           chatBenas;
    private List<ChatBenas>     chats       = new ArrayList<>();
    private ListView            chat_list;
    private Handler             handler;
    private ChatDaoImp          chatDaoImp;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        chatDaoImp  = new ChatDaoImp(getActivity());
        et_chat = view.findViewById(R.id.chat_et_chat);
        btn_send = view.findViewById(R.id.chat_btn_send);
        chat_list = view.findViewById(R.id.chat_listview);
        initListView(((MainActivity)getActivity()).getName());//初始化聊天室界面
//        更新listview
        handler = new Handler(){
            String active_name =  ((MainActivity)getActivity()).getName();
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    MyListViewAdapter myListViewAdapter = new MyListViewAdapter(chats,getActivity(),active_name);
                    chat_list.setAdapter(myListViewAdapter);
                }
            }
        };
        //监听发送按钮
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.chat_btn_send:
                         String chat =  et_chat.getText().toString().trim();
                        if(chat.isEmpty()){
                            Toast.makeText(getActivity(), "不能发送空信息", Toast.LENGTH_SHORT).show();
                            return;
                        }
                         String active_user = ((MainActivity)getActivity()).getUser();
                                OkHttpUtil.httpPostChat(active_user, chat, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                    }
                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String json = response.body().string();
                                        showUI(json);
                                        chats.clear();
                                    }
                                });

                        et_chat.setText("");
                        break;
                }
            }
        });
        return view;
    }
//    解析json数据并通知handler
    public void showUI(final String json){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = jsonArray.length()-1; i >=0; i--) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        chatBenas = new ChatBenas();
                        chatBenas.setChat(jsonObject.getString("chat"));
                        chatBenas.setName(jsonObject.getString("name"));
                        chatBenas.setTime(jsonObject.getString("time"));
                        chats.add(chatBenas);
                    }
//                    聊天信息保存到数据库
                    chatDaoImp.deleteAll();
                    chatDaoImp.insertChat(chats);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void initListView(final String active_name){
        final List<ChatBenas> chatBenas = chatDaoImp.queryChats();
       new Thread(new Runnable() {
           @Override
           public void run() {
               if(chatBenas == null){
                   return;
               }else{
                   MyListViewAdapter myListViewAdapter = new MyListViewAdapter(chatBenas,getActivity(),active_name);
                   chat_list.setAdapter(myListViewAdapter);
               }
           }
       }).start();
    }
}