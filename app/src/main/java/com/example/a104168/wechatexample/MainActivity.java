package com.example.a104168.wechatexample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a104168.wechatexample.Fragmentlayout.Fragment_chat;
import com.example.a104168.wechatexample.Fragmentlayout.Fragment_find;
import com.example.a104168.wechatexample.Fragmentlayout.Fragment_my;
import com.example.a104168.wechatexample.Fragmentlayout.Fragment_users;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    
    private TextView tv_menu_users,tv_menu_chat,tv_menu_my,tv_menu_find;        //底部导航监听控件
    private  String name,user,num;

    protected Fragment_users fragmentUsers  = new Fragment_users(); //联系人fragment实例
    protected Fragment_chat fragmentChat    = new Fragment_chat();  //聊天室fragment实例
    protected Fragment_my fragmentMy        = new Fragment_my();    //个人页fragment实例
    protected Fragment_find fragmentFind    = new Fragment_find();   //发现页fragment实例
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();
        initFragmentView();
        getData();
    }
    //初始化fragment容器
    private void initFragmentView(){
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rl_main,fragmentUsers)
                .add(R.id.rl_main,fragmentChat)
                .add(R.id.rl_main,fragmentMy)
                .add(R.id.rl_main,fragmentFind)
                .hide(fragmentChat)
                .hide(fragmentMy)
                .hide(fragmentFind)
                .show(fragmentUsers)
                .commit();
        tv_menu_users.setSelected(true);
    }
    //初始化页面
    private void initView(){
        tv_menu_find    = findViewById(R.id.tv_nav_find);
        tv_menu_chat    = findViewById(R.id.tv_nav_chat);
        tv_menu_my      = findViewById(R.id.tv_nav_my);
        tv_menu_users   = findViewById(R.id.tv_nav_users);
        tv_menu_chat    .setOnClickListener(this);
        tv_menu_my      .setOnClickListener(this);
        tv_menu_users   .setOnClickListener(this);
        tv_menu_find    .setOnClickListener(this);
    }
    //清除底部菜单选中状态
    private void selected(){
        tv_menu_find    .setSelected(false);
        tv_menu_users   .setSelected(false);
        tv_menu_chat    .setSelected(false);
        tv_menu_my      .setSelected(false);
    }
    @Override
    //监听底部导航切换
    public void onClick(View v) {
        selected();
        switch (v.getId()){
            case R.id.tv_nav_chat:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(fragmentUsers)
                        .hide(fragmentMy)
                        .hide(fragmentFind)
                        .show(fragmentChat)
                        .commit();
                tv_menu_chat.setSelected(true);
                break;
            case R.id.tv_nav_users:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(fragmentChat)
                        .hide(fragmentMy)
                        .hide(fragmentFind)
                        .show(fragmentUsers)
                        .commit();
                tv_menu_users.setSelected(true);
                break;
            case R.id.tv_nav_my:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(fragmentChat)
                        .hide(fragmentUsers)
                        .hide(fragmentFind)
                        .show(fragmentMy)
                        .commit();
                tv_menu_my.setSelected(true);
                break;
            case R.id.tv_nav_find:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(fragmentChat)
                        .hide(fragmentUsers)
                        .hide(fragmentMy)
                        .show(fragmentFind)
                        .commit();
                tv_menu_find.setSelected(true);
                break;
        }
    }
    //获取登陆页传递过来的数据
    private void getData(){
        if(getIntent() != null){
            user = getIntent().getStringExtra("user");
            name = getIntent().getStringExtra("name");
            num  = getIntent().getStringExtra("num");
        }
    }
    //用于fragment获取数据
    public String getName() {
        return name;
    }
    public String getUser() {
        return user;
    }
    public String getNum(){return num;}


}
