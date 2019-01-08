package com.example.a104168.wechatexample.fragmentlayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a104168.wechatexample.LoginAcivity;
import com.example.a104168.wechatexample.R;

public class fragment_my extends Fragment implements View.OnClickListener {
    private TextView tv_exit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        initView(view);
        return view;
    }
    private void initView(View view){
        tv_exit = view.findViewById(R.id.my_tv_exit);
        tv_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_tv_exit:
                showDialog();
                break;
        }
    }
    private void showDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage("是否要退出当前账号?");
        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getActivity(),LoginAcivity.class));
                    getActivity().finish();
                }
            });
        alertDialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        alertDialog.show();
    }
}
