package com.degs.econtacts;

public class Booth_Model {
    int id,assembly_id;
    int booth_no;
    String booth_name_eng,booth_name_hi,booth_address;
    String blo_name_eng,blo_name_hi;
    String blo_mobile;
    String sector_name_eng,sector_name_hi;
    String ac_name_eng,ac_name_hi;
    String lat,lng;
    int webcasting,cctv,videography,critical,vulnarable,call_network,data_network;

    public Booth_Model(int id, int assembly_id, int booth_no, String booth_name_eng, String booth_name_hi, String booth_address, String blo_name_eng, String blo_name_hi, String blo_mobile, String sector_name_eng, String sector_name_hi, String ac_name_eng, String ac_name_hi, String lat, String lng, int webcasting, int cctv, int videography, int critical, int vulnarable, int call_network, int data_network) {
        this.id = id;
        this.assembly_id = assembly_id;
        this.booth_no = booth_no;
        this.booth_name_eng = booth_name_eng;
        this.booth_name_hi = booth_name_hi;
        this.booth_address = booth_address;
        this.blo_name_eng = blo_name_eng;
        this.blo_name_hi = blo_name_hi;
        this.blo_mobile = blo_mobile;
        this.sector_name_eng = sector_name_eng;
        this.sector_name_hi = sector_name_hi;
        this.ac_name_eng = ac_name_eng;
        this.ac_name_hi = ac_name_hi;
        this.lat = lat;
        this.lng = lng;
        this.webcasting = webcasting;
        this.cctv = cctv;
        this.videography = videography;
        this.critical = critical;
        this.vulnarable = vulnarable;
        this.call_network = call_network;
        this.data_network = data_network;
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

    public int getBooth_no() {
        return booth_no;
    }

    public void setBooth_no(int booth_no) {
        this.booth_no = booth_no;
    }

    public String getBooth_name_eng() {
        return booth_name_eng;
    }

    public void setBooth_name_eng(String booth_name_eng) {
        this.booth_name_eng = booth_name_eng;
    }

    public String getBooth_name_hi() {
        return booth_name_hi;
    }

    public void setBooth_name_hi(String booth_name_hi) {
        this.booth_name_hi = booth_name_hi;
    }

    public String getBooth_address() {
        return booth_address;
    }

    public void setBooth_address(String booth_address) {
        this.booth_address = booth_address;
    }

    public String getBlo_name_eng() {
        return blo_name_eng;
    }

    public void setBlo_name_eng(String blo_name_eng) {
        this.blo_name_eng = blo_name_eng;
    }

    public String getBlo_name_hi() {
        return blo_name_hi;
    }

    public void setBlo_name_hi(String blo_name_hi) {
        this.blo_name_hi = blo_name_hi;
    }

    public String getBlo_mobile() {
        return blo_mobile;
    }

    public void setBlo_mobile(String blo_mobile) {
        this.blo_mobile = blo_mobile;
    }

    public String getSector_name_eng() {
        return sector_name_eng;
    }

    public void setSector_name_eng(String sector_name_eng) {
        this.sector_name_eng = sector_name_eng;
    }

    public String getSector_name_hi() {
        return sector_name_hi;
    }

    public void setSector_name_hi(String sector_name_hi) {
        this.sector_name_hi = sector_name_hi;
    }

    public String getAc_name_eng() {
        return ac_name_eng;
    }

    public void setAc_name_eng(String ac_name_eng) {
        this.ac_name_eng = ac_name_eng;
    }

    public String getAc_name_hi() {
        return ac_name_hi;
    }

    public void setAc_name_hi(String ac_name_hi) {
        this.ac_name_hi = ac_name_hi;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getWebcasting() {
        return webcasting;
    }

    public void setWebcasting(int webcasting) {
        this.webcasting = webcasting;
    }

    public int getCctv() {
        return cctv;
    }

    public void setCctv(int cctv) {
        this.cctv = cctv;
    }

    public int getVideography() {
        return videography;
    }

    public void setVideography(int videography) {
        this.videography = videography;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getVulnarable() {
        return vulnarable;
    }

    public void setVulnarable(int vulnarable) {
        this.vulnarable = vulnarable;
    }

    public int getCall_network() {
        return call_network;
    }

    public void setCall_network(int call_network) {
        this.call_network = call_network;
    }

    public int getData_network() {
        return data_network;
    }

    public void setData_network(int data_network) {
        this.data_network = data_network;
    }
}
