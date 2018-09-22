package com.example.student.solitedatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private EditText etId, etName,etArea;
   private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initView();
    }

    private void initView() {
        etId=findViewById(R.id.etId);
        etName =findViewById(R.id.etName);
        etArea=findViewById(R.id.etArea);
        result=findViewById(R.id.result);

    }

    public void save(View view) {
        String id = etId.getText().toString().trim();
        String name= etName.getText().toString().trim();
        String area= etArea.getText().toString().trim();
        if (id.isEmpty()){
            etId.setError("please enter id");
            etId.requestFocus();
            return;
        }

        if(name.isEmpty()){
            etName.setError("please enter id");
            etName.requestFocus();
            return;
        }

        if(area.isEmpty()){
            etArea.setError("please enter id");
            etArea.requestFocus();
            return;
        }

        MyDBClass myDBClass = new MyDBClass(this);
        if(myDBClass.insertData(id, name, area)){
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show();

    }

    public void viewData(View view) {
        MyDBClass myDBClass = new MyDBClass(this);
        Cursor cursor = myDBClass.viewData();

        StringBuilder builder= new StringBuilder();
        if(cursor== null){
            result.setText("no data found");
            return;
        }
        cursor.moveToFirst();

        do{
            builder.append("Id : ").append(cursor.getString(0))
                    .append(" , Name: ").append(cursor.getString(1)).append(" . ")
                    .append("Area : ").append(cursor.getString(2)).append("\n\n");


        }while (cursor.moveToNext());
        result.setText(builder);

    }
}
