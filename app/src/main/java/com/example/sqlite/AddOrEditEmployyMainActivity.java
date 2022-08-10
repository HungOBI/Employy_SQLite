package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.model.Employy;
import com.example.sqlite.sqlite.EmployyDAO;

public class AddOrEditEmployyMainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private EditText etEmployyId,etName,etSalary;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_employy_main);
        etEmployyId = findViewById(R.id.etEmployeeId);
        etName=findViewById(R.id.etName);
        etSalary=findViewById(R.id.etSalary);

        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        findViewById(R.id.btnListEmployees).setOnClickListener(this);
        readEmployee();
    }
    private void readEmployee(){
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        if(bundle==null){
            return;
        }

        String id = bundle.getString("id");
        EmployyDAO dao = new EmployyDAO(this);
        Employy emp = dao.getById(id);

        etEmployyId.setText(emp.getId());
        etName.setText(emp.getName());
        etSalary.setText(""+ emp.getSalary());

        btnSave.setText("Update");
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        switch (view.getId()){
            case R.id.btnSave:
                EmployyDAO dao = new EmployyDAO(this);
                Employy emp= new Employy();
                emp.setId(etEmployyId.getText().toString());
                emp.setName(etName.getText().toString());
                emp.setSalary(Float.parseFloat(etSalary.getText().toString()));
                if(btnSave.getText().equals("Save")){
                    dao.insert(emp);
                }else{
                    dao.update(emp);
                }
                dao.insert(emp);
                Toast.makeText(this,"new employee has been saved!!",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnListEmployees:
                startActivity(intent);
                break;

        }
    }
}