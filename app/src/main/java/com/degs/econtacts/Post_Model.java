package com.degs.econtacts;

public class Post_Model {
    int id,rank;
    String name_eng;
    String name_hi;
    String short_name_eng;
    String short_name_hi;
    String department_short_name_eng;

    public Post_Model(int id, int rank, String name_eng, String name_hi, String short_name_eng, String short_name_hi, String department_short_name_eng, String department_short_name_hi) {
        this.id = id;
        this.rank = rank;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.short_name_eng = short_name_eng;
        this.short_name_hi = short_name_hi;
        this.department_short_name_eng = department_short_name_eng;
        this.department_short_name_hi = department_short_name_hi;
    }

    public String getDepartment_short_name_eng() {
        return department_short_name_eng;
    }

    public void setDepartment_short_name_eng(String department_short_name_eng) {
        this.department_short_name_eng = department_short_name_eng;
    }

    public String getDepartment_short_name_hi() {
        return department_short_name_hi;
    }

    public void setDepartment_short_name_hi(String department_short_name_hi) {
        this.department_short_name_hi = department_short_name_hi;
    }

    String department_short_name_hi;

    public Post_Model(int id, int rank, String name_eng, String name_hi, String short_name_eng, String short_name_hi) {
        this.id = id;
        this.rank = rank;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.short_name_eng = short_name_eng;
        this.short_name_hi = short_name_hi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public String getShort_name_eng() {
        return short_name_eng;
    }

    public void setShort_name_eng(String short_name_eng) {
        this.short_name_eng = short_name_eng;
    }

    public String getShort_name_hi() {
        return short_name_hi;
    }

    public void setShort_name_hi(String short_name_hi) {
        this.short_name_hi = short_name_hi;
    }
}
