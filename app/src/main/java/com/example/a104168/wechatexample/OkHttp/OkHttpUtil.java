package com.example.a104168.wechatexample.OkHttp;

import com.example.a104168.wechatexample.MyAdapter.ChildrenGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil extends OkHttpClient {
    //请求地址
    private static String Users_url     = "http://123.207.85.214/chat/member.php";
    private static String Login_url     = "http://123.207.85.214/chat/login.php";
    private static String Chat_url      = "http://123.207.85.214/chat/chat1.php";
    private static String Register_url  = "http://123.207.85.214/chat/register.php";
    private OkHttpClient client;
    private ChildrenGroup group;
    /** 获取联系人列表
     * @param id    一级列表的id
     * @param name 一级列表的标题
     **/
    public ChildrenGroup httpGetUsers(String id,String name){
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
                  if(response.isSuccessful()){
                      //获取返回的json数组
                      String json = response.body().string();
                      //解析json数组
                      JSONArray jsonArray = new JSONArray(json);
                      for (int i = 0; i < jsonArray.length(); i++) {
                          JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                          group.addChild(""+i,jsonObject.getString("name"));
                      }
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

    /**
     * @param user 账号
     * @param password  密码
     * @param callback 回调函数
     */
    public static void httpPostlogin(String user, String password,Callback callback) {
        OkHttpClient client = new OkHttpClient();
        //post表单内容
        RequestBody body = new FormBody.Builder()
                .add("user", user)
                .add("password", password)
                .build();
        //访问请求
        final Request request = new Request.Builder()
                .url(Login_url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     *
     * @param user  加密user
     * @param chat  发送的聊天内容
     * @param callback  回调
     */
    public static void httpPostChat(String user,String chat,Callback callback){
        OkHttpClient client = new OkHttpClient();
        //post表单提交数据
        RequestBody body = new FormBody.Builder()
                .add("user",user)
                .add("chat",chat)
                .build();
        Request request = new Request.Builder()
                .url(Chat_url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public  static String httpPostRegister(String name,String user,String password) throws IOException{
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("name",name)
                .add("user",user)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url(Register_url)
                .post(body)
                .build();

        Response response =  client.newCall(request).execute();
        return  response.body().string();
    }
}
