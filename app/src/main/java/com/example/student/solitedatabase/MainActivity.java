package com.example.student.solitedatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etId, etName, etArea, etNumber;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initView();
    }

    private void initView() {
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etArea = findViewById(R.id.etArea);
        etNumber = findViewById(R.id.etNumber);
        result = findViewById(R.id.result);

    }

    public void save(View view) {
        String id = etId.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String area = etArea.getText().toString().trim();
        String number = etNumber.getText().toString().trim();
        if (id.isEmpty()) {
            etId.setError("please enter id");
            etId.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            etName.setError("please enter id");
            etName.requestFocus();
            return;
        }

        if (area.isEmpty()) {
            etArea.setError("please enter id");
            etArea.requestFocus();
            return;
        }

        if (number.isEmpty()) {
            etNumber.setError("please enter id");
            etNumber.requestFocus();
            return;
        }

        MyDBClass myDBClass = new MyDBClass(this);
        if (myDBClass.insertData(id, name, area)) {
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show();

    }

    public void viewData(View view) {
        MyDBClass myDBClass = new MyDBClass(this);
        Cursor cursor = myDBClass.viewData();

        StringBuilder builder = new StringBuilder();
        if (cursor == null) {
            result.setText("no data found");
            return;
        }

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                builder.append("Id : ").append(cursor.getString(0))
                        .append(" , Name: ").append(cursor.getString(1)).append(" . ")
                        .append("Area : ").append(cursor.getString(2)).append("Number : ").append(cursor.getString(3)).append("\n\n");
                cursor.moveToNext();

            }

            result.setText(builder);
        } else {
            result.setText("no data found");
        }


    }

    public void updateData(View view) {
        String id = etId.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String area = etArea.getText().toString().trim();
        String number = etNumber.getText().toString().trim();

        MyDBClass myDBClass = new MyDBClass(this);
        if(myDBClass.updateData(id, name, area, number) == -1){
            Toast.makeText(this, "failed to updated", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
            emptyText();
        }
    }

    private void emptyText() {
        etId.setText("");
        etNumber.setText("");
        etArea.setText("");
        etName.setText("");

    }

    public void Delete(View view) {
        String id = etId.getText().toString().trim();

        if(id.isEmpty()){
            etId.setError("enter id");
            etId.requestFocus();
            return;
        }
        MyDBClass myDBClass = new MyDBClass(this);
        if(myDBClass.deleteData(id)== -1){
            Toast.makeText(this, "failed to delete", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
            emptyText();
        }
    }

    public void search(View view) {
        String id = etId.getText().toString().trim();
        if(id.isEmpty()){
            etId.setError("enter id");
            etId.requestFocus();
            return;
        }
        MyDBClass myDBClass = new MyDBClass(this);
        Cursor cursor = myDBClass.searchItem(id);
        if(cursor.getCount() >0 ){
            cursor.moveToFirst();
            etName.setText(cursor.getString(1));
            etArea.setText(cursor.getString(2));
            etNumber.setText(cursor.getString(3));

        }
        else{
            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();
        }

    }
}
