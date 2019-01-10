package com.example.a104168.wechatexample.Fragmentlayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a104168.wechatexample.R;

public class Fragment_find extends Fragment {
    private LinearLayout pig_pyq;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find,container,false);
        pig_pyq=view.findViewById(R.id.my_pig_pyq);

        pig_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "对方不是你的猪友，请扫码添加该联系人", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
