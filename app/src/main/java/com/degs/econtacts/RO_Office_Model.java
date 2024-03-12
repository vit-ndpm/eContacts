package com.degs.econtacts;

public class RO_Office_Model {
    int id,rank,assembly_id,tehsil_id,sub_division_id,local_body_id,department_id,post_id,role_id,posting_level_id;
    String name_eng,name_hi,mobile,email,landline;

    public RO_Office_Model(int id, int rank, int assembly_id, int tehsil_id, int sub_division_id, int local_body_id, int department_id, int post_id, int role_id, int posting_level_id, String name_eng, String name_hi, String mobile, String email, String landline) {
        this.id = id;
        this.rank = rank;
        this.assembly_id = assembly_id;
        this.tehsil_id = tehsil_id;
        this.sub_division_id = sub_division_id;
        this.local_body_id = local_body_id;
        this.department_id = department_id;
        this.post_id = post_id;
        this.role_id = role_id;
        this.posting_level_id = posting_level_id;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.mobile = mobile;
        this.email = email;
        this.landline = landline;
    }

    public RO_Office_Model(int id, String name_eng, String name_hi, String mobile, String email) {
        this.id = id;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.mobile = mobile;
        this.email = email;
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

    public int getAssembly_id() {
        return assembly_id;
    }

    public void setAssembly_id(int assembly_id) {
        this.assembly_id = assembly_id;
    }

    public int getTehsil_id() {
        return tehsil_id;
    }

    public void setTehsil_id(int tehsil_id) {
        this.tehsil_id = tehsil_id;
    }

    public int getSub_division_id() {
        return sub_division_id;
    }

    public void setSub_division_id(int sub_division_id) {
        this.sub_division_id = sub_division_id;
    }

    public int getLocal_body_id() {
        return local_body_id;
    }

    public void setLocal_body_id(int local_body_id) {
        this.local_body_id = local_body_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getPosting_level_id() {
        return posting_level_id;
    }

    public void setPosting_level_id(int posting_level_id) {
        this.posting_level_id = posting_level_id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }
}
