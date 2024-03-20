package com.degs.econtacts;

public class Category {
    int id;
    String name_eng;
    String name_hi;
    String img;


    public Category(int id, String name_eng, String name_hi, String img) {
        this.id = id;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_eng() {
        return name_eng;
    }

    public void setName_eng(String name_eng) {
        this.name_eng = name_eng;
    }

    public String getName_hi() {
        return name_hi;
    }

    public void setName_hi(String name_hi) {
        this.name_hi = name_hi;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
