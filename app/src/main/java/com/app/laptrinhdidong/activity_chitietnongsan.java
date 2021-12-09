package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.AnhNongSan;
import com.app.laptrinhdidong.model.ItemGioHang;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_chitietnongsan extends AppCompatActivity {
    TextView tenNSTV;
    TextView moTaNSTV;
    TextView giaTV;
    ImageView imageIG;

    ArrayList<ItemGioHang> itemGioHangs;
    ImageButton add;
    ImageButton minus;
    ImageButton love;
    TextView number;
    Integer sl;
    int tym1;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietnongsan);

        preferences = getApplicationContext().getSharedPreferences("loginPref", MODE_PRIVATE);
        editor = preferences.edit();

        ObjectMapper objectMapper = new ObjectMapper();
        try {

            itemGioHangs = (ArrayList<ItemGioHang>) objectMapper.readValue(preferences.getString("giohang", "[]"), new TypeReference<ArrayList<ItemGioHang>>() {
            });

            System.out.println("ket qua : Thanh cong"+itemGioHangs);
        } catch (Exception e) {
            System.out.println("ket qua : That bai");
        }



        tym1 = R.drawable.tym_icon;
        add = (ImageButton) findViewById(R.id.them);
        minus = (ImageButton) findViewById(R.id.giam);
        love = (ImageButton) findViewById(R.id.yeuthichbaidang);
        number = (TextView) findViewById(R.id.soluongctns);
        Intent intent = getIntent();

        String ten = intent.getStringExtra("tenNS");
        String moTaNs = intent.getStringExtra("moTaNS");
        int gia = intent.getIntExtra("gia",0);
        int idNongSan = intent.getIntExtra("maNS",0);

        tenNSTV  =  findViewById(R.id.tenNongsanctns);
        tenNSTV.setText(ten);

        moTaNSTV = findViewById(R.id.danhmucnongsanctns);
        moTaNSTV.setText(moTaNs);

        giaTV = findViewById(R.id.gianongsanctns);
        giaTV.setText(String.valueOf(gia)+" đ");


        imageIG = findViewById(R.id.hinhAnhNongSan);
//        if(url.length() != 0){
//
//            Picasso.with(activity_chitietnongsan.this).load(url)
//                    .placeholder(R.drawable.chuoi)
//                    .into(imageIG);
//        }

        try {
            ApiService.apiService.getAnhNongSanByIdKhachHang(idNongSan).enqueue(new Callback<ArrayList<AnhNongSan>>() {
                @Override
                public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
                    ArrayList<AnhNongSan> anhNongSans = response.body();

                    if (anhNongSans.size() != 0)
                        Picasso.with(activity_chitietnongsan.this).load(anhNongSans.get(0).getTen())
                                .placeholder(R.drawable.chuoi)
                                .into(imageIG);
                }

                @Override
                public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }





        sl = Integer.parseInt(number.getText().toString());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl += 1;
                number.setText(sl.toString());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sl > 1) {
                    sl -= 1;
                    number.setText(sl.toString());
                } else {
                    Toast.makeText(activity_chitietnongsan.this, "Số lượng tối thiểu là 1", Toast.LENGTH_SHORT).show();
                }

            }
        });
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tym1 == R.drawable.tym_icon) {
                    tym1 = R.drawable.traitim2;
                } else {
                    tym1 = R.drawable.tym_icon;
                }
                love.setImageResource(tym1);
            }
        });



        Button btnThemGioHang = findViewById(R.id.btnThemGioHang);
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemGioHang itemGioHang = new ItemGioHang();
                itemGioHang.setId(intent.getIntExtra("ID",0));
                itemGioHang.setSoLuong(Integer.parseInt(number.getText().toString()));
                itemGioHang.setGia(gia);
                itemGioHangs.add(itemGioHang);

                Gson gson = new Gson();
                editor.putString("giohang", gson.toJson(itemGioHangs));// or put anything you want in this with String type
                editor.apply();
                finish();
            }
        });
    }

    public void finish(View view) {
        finish();
    }
}