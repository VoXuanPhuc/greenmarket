package com.app.laptrinhdidong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChiTietHoaDonAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ChiTietHoaDon> chiTietHoaDons;

    public ChiTietHoaDonAdapter(Context context, int layout, List<ChiTietHoaDon> chiTietHoaDons) {
        this.context = context;
        this.layout = layout;
        this.chiTietHoaDons = chiTietHoaDons;
    }

    @Override
    public int getCount() {
        return chiTietHoaDons.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView ten = (TextView) convertView.findViewById(R.id.tenSanPham_CTHD);
        TextView moTa = (TextView)  convertView.findViewById(R.id.moTa_CTHD);
        TextView soLuong = (TextView)  convertView.findViewById(R.id.soLuowng_CTHD);
        TextView donGia = (TextView)  convertView.findViewById(R.id.donGia_CTHD);
        TextView tong = (TextView)  convertView.findViewById(R.id.tong_CTHD);

        ten.setText(chiTietHoaDons.get(position).getTen());
        moTa.setText(chiTietHoaDons.get(position).getMoTa());
        soLuong.setText(String.valueOf(chiTietHoaDons.get(position).getSoLuong()));
        donGia.setText(String.valueOf(chiTietHoaDons.get(position).getDonGia()));
        tong.setText(String.valueOf(chiTietHoaDons.get(position).getTong()));



        return convertView;
    }
}