package com.degs.econtacts;

public class ARO_Office_Model {
    int id;
    String name_eng,name_hi,mobile,email,dept_name,post_name,assembly_name,role_name;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public ARO_Office_Model(int id, String name_eng, String name_hi, String mobile, String email, String dept_name, String post_name, String assembly_name, String role_name) {
        this.id = id;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.mobile = mobile;
        this.email = email;
        this.dept_name = dept_name;
        this.post_name = post_name;
        this.assembly_name = assembly_name;
        this.role_name = role_name;
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

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getAssembly_name() {
        return assembly_name;
    }

    public void setAssembly_name(String assembly_name) {
        this.assembly_name = assembly_name;
    }
}
