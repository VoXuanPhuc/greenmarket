package com.app.laptrinhdidong.API;

import com.app.laptrinhdidong.model.ChiTietHoaDon;
import com.app.laptrinhdidong.model.DanhMuc;
import com.app.laptrinhdidong.model.HoaDon;
import com.app.laptrinhdidong.model.KhachHang;
import com.app.laptrinhdidong.model.NongSan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService= new Retrofit.Builder()
            .baseUrl("https://androidgreenmarketphuc.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()
            .create(ApiService.class);


    @GET("api/danh-mucs")
    Call<ArrayList<DanhMuc>> convertDanhMuc();

    @GET("api/khach-hangs/{id}")
    Call<KhachHang> getKhachHangById(@Path("id") long idUser);

    @GET("api/nong-sans")
    Call<ArrayList<NongSan>> convertTatcaNongSan();

    @GET("api/hoa-dons")
    Call<ArrayList<HoaDon>> convertTatCaHoaDon();

    @GET("api/chi-tiet-hoa-dons")
    Call<ArrayList<ChiTietHoaDon>> convertTatChaChiTietHoaDon();

}