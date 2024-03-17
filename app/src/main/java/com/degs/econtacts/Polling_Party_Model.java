package com.degs.econtacts;

public class Polling_Party_Model {
    int id, assembly_id, booth_id, booth_no, assembly_code;
    String booth_name, assembly_name;
    String po_name, p1_name, p2_name, p3_name;
    String po_mobile, p1_mobile, p2_mobile, p3_mobile;

    public Polling_Party_Model(int id, int assembly_id, int booth_id, int booth_no, int assembly_code,
                               String booth_name, String assembly_name, String po_name, String p1_name, String p2_name, String p3_name,
                               String po_mobile, String p1_mobile, String p2_mobile, String p3_mobile) {
        this.id = id;
        this.assembly_id = assembly_id;
        this.booth_id = booth_id;
        this.booth_no = booth_no;

        this.assembly_code = assembly_code;
        this.booth_name = booth_name;
        this.assembly_name = assembly_name;


        this.po_name = po_name;
        this.p1_name = p1_name;
        this.p2_name = p2_name;
        this.p3_name = p3_name;


        this.po_mobile = po_mobile;
        this.p1_mobile = p1_mobile;
        this.p2_mobile = p2_mobile;
        this.p3_mobile = p3_mobile;
        
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

    public int getBooth_id() {
        return booth_id;
    }

    public void setBooth_id(int booth_id) {
        this.booth_id = booth_id;
    }

    public int getBooth_no() {
        return booth_no;
    }

    public void setBooth_no(int booth_no) {
        this.booth_no = booth_no;
    }

    public int getAssembly_code() {
        return assembly_code;
    }

    public void setAssembly_code(int assembly_code) {
        this.assembly_code = assembly_code;
    }

    public String getBooth_name() {
        return booth_name;
    }

    public void setBooth_name(String booth_name) {
        this.booth_name = booth_name;
    }

    public String getAssembly_name() {
        return assembly_name;
    }

    public void setAssembly_name(String assembly_name) {
        this.assembly_name = assembly_name;
    }

    public String getPo_name() {
        return po_name;
    }

    public void setPo_name(String po_name) {
        this.po_name = po_name;
    }

    public String getP1_name() {
        return p1_name;
    }

    public void setP1_name(String p1_name) {
        this.p1_name = p1_name;
    }

    public String getP2_name() {
        return p2_name;
    }

    public void setP2_name(String p2_name) {
        this.p2_name = p2_name;
    }

    public String getP3_name() {
        return p3_name;
    }

    public void setP3_name(String p3_name) {
        this.p3_name = p3_name;
    }

    public String getPo_mobile() {
        return po_mobile;
    }

    public void setPo_mobile(String po_mobile) {
        this.po_mobile = po_mobile;
    }

    public String getP1_mobile() {
        return p1_mobile;
    }

    public void setP1_mobile(String p1_mobile) {
        this.p1_mobile = p1_mobile;
    }

    public String getP2_mobile() {
        return p2_mobile;
    }

    public void setP2_mobile(String p2_mobile) {
        this.p2_mobile = p2_mobile;
    }

    public String getP3_mobile() {
        return p3_mobile;
    }

    public void setP3_mobile(String p3_mobile) {
        this.p3_mobile = p3_mobile;
    }
}
