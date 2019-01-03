package com.example.a104168.wechatexample.OkHttp;

import com.example.a104168.wechatexample.expandablelistview.ChildrenGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil extends OkHttpClient {
    //请求联系人列表php文件地址
    private final String Users_url = "http://123.207.85.214/chat/member.php";

    private OkHttpClient client;
    private ChildrenGroup group;
    /** 获取联系人列表
     * @param id    一级列表的id
     * @param name 一级列表的标题
     **/
    public ChildrenGroup httpGetUsers(Integer id,String name){
        client  = new OkHttpUtil();
        group   = new ChildrenGroup(id,name);
        final Request request = new Request.Builder()
                .url(Users_url)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                  Response response = client.newCall(request).execute();
                  //获取返回的json数组
                  String json = response.body().string();
                  //解析json数组
                  JSONArray jsonArray = new JSONArray(json);
                  for (int i = 0; i < jsonArray.length(); i++) {
                      JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                      group.addChild(i,jsonObject.getString("name"));
                  }

                } catch (IOException e) {
                    e.printStackTrace();
                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        return group;
    }
}
