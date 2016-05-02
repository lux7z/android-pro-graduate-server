package com.crown.bean;

public class Product {
	public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getpTypeId() {
        return pTypeId;
    }

    public void setpTypeId(int pTypeId) {
        this.pTypeId = pTypeId;
    }

    public String getpImgName() {
        return pImgName;
    }

    public void setpImgName(String pImgName) {
        this.pImgName = pImgName;
    }

    public double getpDiscount() {
        return pDiscount;
    }

    public void setpDiscount(double pDiscount) {
        this.pDiscount = pDiscount;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpShopId() {
		return pShopId;
	}
    
    public void setpShopId(int pShopId) {
		this.pShopId = pShopId;
	}
    
    private int pId;
    private int pTypeId;
    private String pName;
    private double pPrice;
    private double pDiscount;
    private String pImgName;
    private int pShopId;
    private String pDiscription;
    private String pArea;

    public String getpArea() {
        return pArea;
    }

    public void setpArea(String pArea) {
        this.pArea = pArea;
    }

    public String getpDiscription() {
        return pDiscription;
    }

    public void setpDiscription(String pDiscription) {
        this.pDiscription = pDiscription;
    }
}
