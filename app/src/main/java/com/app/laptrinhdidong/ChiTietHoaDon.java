package com.app.laptrinhdidong;

class ChiTietHoaDon  {
    String ten,moTa;
    int soLuong;
    double donGia,tong;


    public ChiTietHoaDon(String ten, String moTa, int soLuong, double donGia) {
        this.ten = ten;
        this.moTa = moTa;

        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tong = soLuong*donGia;
    }


    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }



    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getTong() {
        return tong;
    }

    public void setTong(double tong) {
        this.tong = tong;
    }
}
