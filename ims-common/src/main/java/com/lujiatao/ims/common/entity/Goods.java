package com.lujiatao.ims.common.entity;


public class Goods extends BasePO {

    private int id;
    private String brand;
    private String model;
    private String desc;
    private int count;
    private int goodsCategoryId;

    public Goods(int id, String brand, String model, String desc, int count, int goodsCategoryId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.desc = desc;
        this.count = count;
        this.goodsCategoryId = goodsCategoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

}
