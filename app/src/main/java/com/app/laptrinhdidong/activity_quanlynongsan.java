package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.app.laptrinhdidong.alladapter.quanlybaidangAdapter;
import com.app.laptrinhdidong.allclass.danhmucClass;
import com.app.laptrinhdidong.allclass.nongsanClass;

import java.util.ArrayList;
import java.util.List;

public class activity_quanlynongsan extends AppCompatActivity {

    List<danhmucClass> dm;
    ListView lvQuanLyNongSan;
    quanlybaidangAdapter lvbaidangAdapter;
    List<nongsanClass> nongsan;
    ImageButton themnongsan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlynongsan);
        Nopvao();
        lvbaidangAdapter = new quanlybaidangAdapter(this, R.layout.lv_baidang, nongsan);
        lvQuanLyNongSan.setAdapter(lvbaidangAdapter);


        themnongsan = (ImageButton) findViewById(R.id.themmoibaidang);


        themnongsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_quanlynongsan.this, activity_themnongsan.class));
            }
        });
        lvQuanLyNongSan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(activity_quanlynongsan.this, activity_chitietnongsan.class));
            }
        });

    }
    private void Nopvao(){
        dm = new ArrayList<>();
        nongsan = new ArrayList<>();
      dm.add(new danhmucClass("Trai cay tuoi", "Rau sach", "Thit" , "Ca"));
        lvQuanLyNongSan = findViewById(R.id.listviewnongsan);

        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.traidau));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.traidau));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.traidau));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.traidau));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.traidau));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.traidau));
    }
}