package com.example.a104168.wechatexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a104168.wechatexample.OkHttp.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name,et_user,et_password;
    private Button btn_register;
    private ImageView btn_back;
    private String name,user,password;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i("2","handlemesage");
            if(msg.what == 1){
                Log.i("3","handler执行了");
                String json = (String) msg.obj;
                showUI(json);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initView();
    }

    //初始化控件
    private void initView(){
        et_name     = findViewById(R.id.register_et_name);
        et_user     = findViewById(R.id.register_et_user);
        et_password = findViewById(R.id.register_et_password);
        btn_register= findViewById(R.id.register_btn_register);
        btn_back    = findViewById(R.id.register_btn_back);
        btn_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }
    //获取数据
    private void getData(){
        this.name     = et_name.getText().toString().trim();
        this.user     = et_user.getText().toString().trim();
        this.password = et_password.getText().toString().trim();
    }
    private Boolean checkInput(){
        if(name.equals("")||user.equals("")||password.equals("")){
            Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn_register:
                getData();
                if(checkInput()){
                    sendMsg();
                }
                break;
            case R.id.register_btn_back:
                startActivity(new Intent(this,LoginAcivity.class));
                RegisterActivity.this.finish();
                break;
        }
    }
    private void sendMsg(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json =  OkHttpUtil.httpPostRegister(name,user,password);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = json;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showUI(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            String status = jsonObject.optString("status");
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage(status)
                    .setPositiveButton("确定",null)
                    .show();
            if("注册成功".equals(status)){
                startActivity(new Intent(this,LoginAcivity.class));
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
