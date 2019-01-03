package com.example.a104168.wechatexample.fragmentlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.a104168.wechatexample.OkHttp.OkHttpUtil;
import com.example.a104168.wechatexample.R;
import com.example.a104168.wechatexample.expandablelistview.ChildrenGroup;
import com.example.a104168.wechatexample.expandablelistview.ChildrenItem;
import com.example.a104168.wechatexample.expandablelistview.ExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class fragment_users extends Fragment{
    /*
    * 用于管理联系人页面
    * */
    private ChildrenGroup               group;          //一级列表（存储二级用户数据）
    private List<ChildrenGroup>         childrenGroups; //一级列表集合
    private ExpandableListView          expandableListView;//可扩容列表视图
    private ExpandableListViewAdapter   expandableListViewAdapter; //自定义视图适配器
    private Context    mcontext   = getActivity();       //获取activity上下文
    private OkHttpUtil okHttpUtil = new OkHttpUtil();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users,container,false);
        //实例化一级列表集合
        childrenGroups = new ArrayList<ChildrenGroup>();
        //定义一级列表名称并请求到用户数据
        group = okHttpUtil.httpGetUsers(1,"用户列表");
        childrenGroups.add(group);
        group = new ChildrenGroup(2,"联系人");
        group.addChild(1,"大3");
        group.addChild(2,"大3");
        childrenGroups.add(group);
        //将数据显示到页面上
        expandableListView = view.findViewById(R.id.exlist_user);
        expandableListViewAdapter = new ExpandableListViewAdapter(childrenGroups,getActivity());
        expandableListView.setAdapter(expandableListViewAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            //用户列表项点击事件监听
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mcontext, expandableListViewAdapter.getChild(groupPosition,childPosition).toString(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return view;
    }
}
