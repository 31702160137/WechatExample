package com.example.a104168.wechatexample.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a104168.wechatexample.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context             context;
    private LayoutInflater      layoutInflater;
    private List<ChildrenGroup> childrenGroups; // 一级列表数据集合


    public ExpandableListViewAdapter(List<ChildrenGroup> childrenGroups,Context context){
        this.childrenGroups  = childrenGroups;
        this.context         = context;
        this.layoutInflater  = LayoutInflater.from(context);
    }
    @Override
    // 获取一级列表的个数
    public int getGroupCount() {
        return childrenGroups.size();
    }

    @Override
    //获取指定一级列表内的子选项的个数
    public int getChildrenCount(int groupPosition) {
        return childrenGroups.get(groupPosition).getChildrenItems().size();
    }
    @Override
    //获取指定的一级列表数据
    public Object getGroup(int groupPosition) {
        return childrenGroups.get(groupPosition);
    }

    @Override
    //获取指定一级列表内指定的子选项数据
    public Object getChild(int groupPosition, int childPosition) {
        return childrenGroups.get(groupPosition).getChildrenItems().get(childPosition);
    }

    @Override
    //获取指定一级选项的id
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    //获取指定二级选项的id
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        FatherViewHolder fatherViewHolder = null;
        if(convertView == null){
            convertView      = layoutInflater.inflate(R.layout.users_father,parent,false);
            fatherViewHolder = new FatherViewHolder();
            fatherViewHolder.textView   = convertView.findViewById(R.id.tv_father);
            fatherViewHolder.imageView  = convertView.findViewById(R.id.img_select);
            convertView.setTag(fatherViewHolder);
        }else{
            fatherViewHolder = (FatherViewHolder) convertView.getTag();
        }
        ChildrenGroup childrenGroup = childrenGroups.get(groupPosition);
        fatherViewHolder.textView.setText(childrenGroup.getName());
        fatherViewHolder.imageView.setSelected(isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if(convertView == null){
            convertView     = layoutInflater.inflate(R.layout.users_children,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.textView   = convertView.findViewById(R.id.tv_child);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        ChildrenItem childrenItem = childrenGroups.get(groupPosition).getChildrenItems().get(childPosition);
        childViewHolder.textView.setText(childrenItem.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class FatherViewHolder{
        TextView textView;
        ImageView imageView;
    }
    class ChildViewHolder{
        TextView textView;
    }
}
