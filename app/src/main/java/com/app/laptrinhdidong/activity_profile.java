package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.KhachHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_profile extends AppCompatActivity {

    Button editProfile;
    Button hoaDon;
    TextView tvName;
    Button btnLogut;
    public static KhachHang khachHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
        editProfile = (Button) findViewById(R.id.editProfile);
        hoaDon = (Button) findViewById(R.id.hoadon);
        tvName = (TextView) findViewById(R.id.name);
        btnLogut = (Button) findViewById(R.id.dangxuat);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        startActivity(new Intent(activity_profile.this, DanhMucActivity.class));
                        break;
                    case R.id.card :
                        startActivity(new Intent(activity_profile.this, GioHangActivity.class));
                        break;
                    case R.id.search:
                        startActivity(new Intent(activity_profile.this, activity_search.class));
                        break;
                    case R.id.profile:
                        break;
                }
                return true;
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_profile.this, com.app.laptrinhdidong.editProfile.class)
                .putExtra("username", tvName.getText().toString()));
            }
        });

        hoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_profile.this, DanhSachHoaDonActivity.class));
            }
        });

        btnLogut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("idKhachHang");
                editor.commit();
                activity_profile.khachHang = null;
                startActivity(new Intent(activity_profile.this, DanhMucActivity.class));
            }
        });

        if (preferences.contains("idKhachHang")) {
            String idKhachHang = preferences.getString("idKhachHang", "");
            getKhachHangById(idKhachHang);
        } else if (!preferences.contains("idKhachHang")) {
            startActivity(new Intent(activity_profile.this, Activity_dangnhap.class));
        }

        Intent intentEditProfile = getIntent();
        if (intentEditProfile.getStringExtra("name") != null) {
            tvName.setText(intentEditProfile.getStringExtra("name"));
        }
    }


    public void getKhachHangById(String khachhangId) {
        ApiService.apiService.getKhachHangById(khachhangId).enqueue(
                new Callback<KhachHang>() {
                    @Override
                    public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                        if (response.isSuccessful()) {
                            activity_profile.khachHang = response.body();
                            tvName.setText(khachHang.getHoTenKH());
                        }
                    }

                    @Override
                    public void onFailure(Call<KhachHang> call, Throwable t) {

                    }
                }
        );
    }
}