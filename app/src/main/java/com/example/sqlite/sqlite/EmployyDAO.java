package com.example.sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite.model.Employy;

import java.util.ArrayList;
import java.util.List;

public class EmployyDAO {
    private  SQLiteDatabase db;

    public EmployyDAO(Context context) {
        DBHelper helper= new DBHelper(context);
        db=helper.getReadableDatabase();
    }

    public EmployyDAO() {

    }

    public List<Employy> get(String sql,String ...selectArgs){
        List<Employy> list= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,selectArgs);

        while (cursor.moveToNext()){
            Employy emp=new Employy();
            emp.setId(cursor.getString(cursor.getColumnIndex("id")));
            emp.setName(cursor.getString(cursor.getColumnIndex("name")));
            emp.setSalary(cursor.getFloat(cursor.getColumnIndex("salary")));

            list.add(emp);
        }
        return list;
    }
    public List<Employy> getAll(){
        String sql="SELECT * FROM nhanvien";
        return get(sql);
    }
    public Employy getById(String id){
        String sql="SELECT * FROM nhanvien WHERE id = ?";
        List<Employy> list=get(sql,id);
        return list.get(0);
    }
    public long insert(Employy emp){
        ContentValues values=new ContentValues();
        values.put("id",emp.getId());
        values.put("name",emp.getName());
        values.put("salary",emp.getSalary());
        return db.insert("nhanvien",null,values);
    }
    public long update(Employy emp){
        ContentValues values=new ContentValues();
        values.put("name",emp.getName());
        values.put("salary",emp.getSalary());
        return db.update("nhanvien",values,"id=?",new String[]{emp.getId()});

    }
    public int delete(String id){
        return db.delete("nhanvien","id=?",new String[]{id});
    }


}
