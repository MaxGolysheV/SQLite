package com.example.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText name, phone, dateofBirth;
    Button insert, update, delete, select;
    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtNumber);
        dateofBirth = findViewById(R.id.txtDate);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        select = findViewById(R.id.btnSelect);
        databaseHelper = new DataBaseHelper(this);

        insert.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.insert(name.getText().toString(), phone.getText().toString(),
                    dateofBirth.getText().toString());
            if (checkInsertData){
                Toast.makeText(getApplicationContext(), "Успешно добавлено!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Ошибка при добавлении!", Toast.LENGTH_LONG).show();
            }
        });

        update.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.Update(name.getText().toString(), phone.getText().toString(),
                    dateofBirth.getText().toString());
            if (checkInsertData){
                Toast.makeText(getApplicationContext(), "Успешное изменение!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Ошибка при изменении !", Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.Delete(name.getText().toString());
            if (checkInsertData){
                Toast.makeText(getApplicationContext(), "Успешное удаление!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Ошибка при удалении!", Toast.LENGTH_LONG).show();
            }
        });

        select.setOnClickListener(view -> {
            Cursor res = databaseHelper.getdata();
            //Проверка на наличие данных
            if(res.getCount() == 0){
                Toast.makeText(MainActivity.this, "Данные отсутствуют!", Toast.LENGTH_LONG).show();
                return;
            }
            //Цикл для перебора и объединения данных
            StringBuilder builder = new StringBuilder();
            while(res.moveToNext()){
                builder.append("Имя: ").append(res.getString(0)).append("\n");
                builder.append("Тел. номер: ").append(res.getString(1)).append("\n");
                builder.append("Дата рождения: ").append(res.getString(2)).append("\n");
            }
            //Диалоговое окно
            AlertDialog.Builder _builder = new AlertDialog.Builder(MainActivity.this);
            _builder.setCancelable(true);
            _builder.setTitle("ПОЛЬЗОВАТЕЛИ:");
            _builder.setMessage(builder.toString());
            _builder.show();
        });

    }
}