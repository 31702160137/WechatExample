package com.example.a104168.wechatexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a104168.wechatexample.Beans.LoginBenas;
import com.example.a104168.wechatexample.OkHttp.OkHttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginAcivity extends AppCompatActivity implements View.OnClickListener {
    //实现用户登陆
    private EditText et_user,et_password;
    private Button btn_login,btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_acivity);
        initView();
    }
//    初始化控件
    private void initView(){
        et_user     = findViewById(R.id.et_user);
        et_password = findViewById(R.id.et_password);
        btn_login   = findViewById(R.id.login);
        btn_register= findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //登陆按钮监听
            case R.id.login :
                //获取输入的账号，密码
                String user = et_user.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if(user.isEmpty()){
                    Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if(password.isEmpty()){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //异步请求登陆页面
                 OkHttpUtil.httpPostlogin(user, password, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        LoginBenas loginBenas = new Gson().fromJson(response.body().string(),LoginBenas.class);
                        ShowUI(loginBenas);
                    }
                });
                break;
            case R.id.btn_register:
                startActivity(new Intent(this,RegisterActivity.class));
                finish();
                break;
        }
    }
//    登陆提示并跳转
    public void ShowUI(final LoginBenas loginBenas){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if("登陆成功".equals(loginBenas.getStatus())) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("user", loginBenas.getUser());
                    intent.putExtra("name", loginBenas.getName());
                    startActivity(intent);
                    LoginAcivity.this.finish();
                }else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginAcivity.this)
                            .setTitle("提示")
                            .setMessage("账号或密码错误！")
                            .setPositiveButton("确定",null);
                    builder.show();

                }
            }
        });
    }
}
