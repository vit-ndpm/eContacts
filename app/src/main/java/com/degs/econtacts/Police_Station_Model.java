package com.degs.econtacts;

public class Police_Station_Model {
    int id,assembly_id;
    String name_eng,name_hi,address,landline,email,officer_name,officer_mobile;

    public Police_Station_Model(int id, int assembly_id, String name_eng, String name_hi, String address, String landline, String email, String officer_name, String officer_mobile) {
        this.id = id;
        this.assembly_id = assembly_id;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.address = address;
        this.landline = landline;
        this.email = email;
        this.officer_name = officer_name;
        this.officer_mobile = officer_mobile;
//        this.assembly_name = assembly_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssembly_id() {
        return assembly_id;
    }

    public void setAssembly_id(int assembly_id) {
        this.assembly_id = assembly_id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficer_name() {
        return officer_name;
    }

    public void setOfficer_name(String officer_name) {
        this.officer_name = officer_name;
    }

    public String getOfficer_mobile() {
        return officer_mobile;
    }

    public void setOfficer_mobile(String officer_mobile) {
        this.officer_mobile = officer_mobile;
    }


}
