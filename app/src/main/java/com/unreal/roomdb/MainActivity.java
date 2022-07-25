package com.unreal.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unreal.roomdb.adapters.CustomAdapter;
import com.unreal.roomdb.database.MainData;
import com.unreal.roomdb.database.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<MainData> datalist = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    RecyclerView rv;
    EditText input;
    Button add, reset;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recycler_view);
        input = findViewById(R.id.et);
        add = findViewById(R.id.btn_add);
        reset = findViewById(R.id.btn_reset);
        adapter = new CustomAdapter(this, datalist);
        linearLayoutManager = new LinearLayoutManager(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(linearLayoutManager);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sText = input.getText().toString().trim();
                if (!sText.isEmpty()){
                    MainData data = new MainData();
                    data.setText(sText);
                    database.mainDao().insert(data);
                    input.setText("");
                    datalist.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Field empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        reset.setOnClickListener(view -> {
            database.mainDao().reset(datalist);
            datalist.clear();
            datalist.addAll(database.mainDao().getAll());
            adapter.notifyDataSetChanged();
        });
    }
}