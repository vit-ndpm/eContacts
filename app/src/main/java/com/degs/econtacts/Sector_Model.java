package com.degs.econtacts;

import java.util.ArrayList;

public class Sector_Model {
    int id,assembly_id,sector_no;
    String name_eng,name_hi,assembly_name_eng;
    ArrayList<Officer_Model> sectorOfficersList;

    public Sector_Model(int id, int assembly_id, int sector_no, String name_eng, String name_hi, String assembly_name_eng, ArrayList<Officer_Model> sectorOfficersList) {
        this.id = id;
        this.assembly_id = assembly_id;
        this.sector_no = sector_no;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.assembly_name_eng = assembly_name_eng;
        this.sectorOfficersList = sectorOfficersList;
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

    public int getSector_no() {
        return sector_no;
    }

    public void setSector_no(int sector_no) {
        this.sector_no = sector_no;
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

    public String getAssembly_name_eng() {
        return assembly_name_eng;
    }

    public void setAssembly_name_eng(String assembly_name_eng) {
        this.assembly_name_eng = assembly_name_eng;
    }

    public ArrayList<Officer_Model> getSectorOfficersList() {
        return sectorOfficersList;
    }

    public void setSectorOfficersList(ArrayList<Officer_Model> sectorOfficersList) {
        this.sectorOfficersList = sectorOfficersList;
    }
}
