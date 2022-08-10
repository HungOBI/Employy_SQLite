package com.example.sqlite.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlite.R;
import com.example.sqlite.model.Employy;

import java.util.List;

public class EmloyeeAdapter extends BaseAdapter {
    private Context context;
    private List<Employy> list;

    public EmloyeeAdapter(List<Employy> list) {
        this.list = list;
    }

    public EmloyeeAdapter(Context context, List<Employy> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int iPosition) {
        return list.get(iPosition);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.layout_emoloyeeitem, null);
        }
        TextView tvName=view.findViewById(R.id.tvName);
        TextView tvSalary=view.findViewById(R.id.tvSalary);
        Employy emp=list.get(i);
        tvName.setText(emp.getName());
        tvSalary.setText(" " + emp.getSalary());

        return view;
    }
}
