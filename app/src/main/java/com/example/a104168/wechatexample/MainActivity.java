package com.example.a104168.wechatexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.a104168.wechatexample.fragmentlayout.fragment_chat;
import com.example.a104168.wechatexample.fragmentlayout.fragment_my;
import com.example.a104168.wechatexample.fragmentlayout.fragment_users;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_menu_users,tv_menu_chat,tv_menu_my;        //底部导航监听控件

    protected fragment_users fragmentUsers  = new fragment_users(); //联系人fragment实例
    protected fragment_chat  fragmentChat   = new fragment_chat();  //聊天室fragment实例
    protected fragment_my    fragmentMy     = new fragment_my();    //个人页fragment实例
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        initView();
        initFragmentView();
    }
    //初始化fragment容器
    private void initFragmentView(){
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rl_main,fragmentUsers)
                .add(R.id.rl_main,fragmentChat)
                .add(R.id.rl_main,fragmentMy)
                .hide(fragmentChat)
                .hide(fragmentMy)
                .show(fragmentUsers)
                .commit();
        tv_menu_users.setSelected(true);
    }
    //初始化页面
    private void initView(){
        tv_menu_chat = findViewById(R.id.tv_nav_chat);
        tv_menu_my = findViewById(R.id.tv_nav_my);
        tv_menu_users = findViewById(R.id.tv_nav_users);
        tv_menu_chat.setOnClickListener(this);
        tv_menu_my.setOnClickListener(this);
        tv_menu_users.setOnClickListener(this);
    }
    private void selected(){
        tv_menu_users.setSelected(false);
        tv_menu_chat.setSelected(false);
        tv_menu_my.setSelected(false);
    }
    @Override
    public void onClick(View v) {
        selected();
        switch (v.getId()){
            case R.id.tv_nav_chat:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(fragmentUsers)
                        .hide(fragmentMy)
                        .show(fragmentChat)
                        .commit();
                tv_menu_chat.setSelected(true);
                break;
            case R.id.tv_nav_users:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(fragmentChat)
                        .hide(fragmentMy)
                        .show(fragmentUsers)
                        .commit();
                tv_menu_users.setSelected(true);
                break;
            case R.id.tv_nav_my:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(fragmentChat)
                        .hide(fragmentUsers)
                        .show(fragmentMy)
                        .commit();
                tv_menu_my.setSelected(true);
                break;
        }
    }
}
