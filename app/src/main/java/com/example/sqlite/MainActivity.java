package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite.adapter.EmloyeeAdapter;
import com.example.sqlite.model.Employy;
import com.example.sqlite.sqlite.DBHelper;
import com.example.sqlite.sqlite.EmployyDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EmloyeeAdapter emloyeeAdapter;
    private ListView lvEmployees;
    private String employeeId;
    private List<Employy> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database= dbHelper.getReadableDatabase();
        database.close();*/
        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnInsert).setOnClickListener(this);

        lvEmployees= findViewById(R.id.lvEmployees);
        EmployyDAO dao = new EmployyDAO(this);
        list=dao.getAll();
        emloyeeAdapter = new EmloyeeAdapter(this,list);
        lvEmployees.setAdapter(emloyeeAdapter);
        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employy emp = list.get(i);
                employeeId=emp.getId();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EmployyDAO dao = new EmployyDAO(this);
        List<Employy>updateList= dao.getAll();
        list.clear();
        updateList.forEach(item->list.add(item));
        emloyeeAdapter.notifyDataSetChanged();
    }

    public void onClick(View view){
        Intent intent=new Intent(this,AddOrEditEmployyMainActivity.class);
        switch ( view.getId()){
            case R.id.btnEdit:
                if(employeeId==null){
                    Toast.makeText(this, "Employee id must be selected ",Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle= new Bundle();
                bundle.putString("id",employeeId);
                intent.putExtra("data",bundle);
                startActivity(intent);
                break;
            case R.id.btnInsert:
                startActivity(intent);
                break;
            case R.id.btnDelete:
                if(employeeId==null){
                    Toast.makeText(this,"Emp,oyee id must be selected",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                EmployyDAO dao = new EmployyDAO(this);
                dao.delete(employeeId);
                employeeId=null;
                onResume();

                Toast.makeText(this,"employee was deketed",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}