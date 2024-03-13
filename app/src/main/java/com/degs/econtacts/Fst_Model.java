package com.degs.econtacts;

import java.util.ArrayList;

public class Fst_Model {
    int id,assembly_id;
    String name_eng,name_hi,shift,assembly_name_eng,assebly_name_hi;
    ArrayList<Officer_Model> fstOfficersList;

    public Fst_Model(int id, int assembly_id, String name_eng, String name_hi, String shift, String assembly_name_eng, String assebly_name_hi, ArrayList<Officer_Model> fstOfficersList) {
        this.id = id;
        this.assembly_id = assembly_id;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.shift = shift;
        this.assembly_name_eng = assembly_name_eng;
        this.assebly_name_hi = assebly_name_hi;
        this.fstOfficersList = fstOfficersList;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getAssembly_name_eng() {
        return assembly_name_eng;
    }

    public void setAssembly_name_eng(String assembly_name_eng) {
        this.assembly_name_eng = assembly_name_eng;
    }

    public String getAssebly_name_hi() {
        return assebly_name_hi;
    }

    public void setAssebly_name_hi(String assebly_name_hi) {
        this.assebly_name_hi = assebly_name_hi;
    }

    public ArrayList<Officer_Model> getSstOfficersList() {
        return fstOfficersList;
    }

    public void setSstOfficersList(ArrayList<Officer_Model> fstOfficersList) {
        this.fstOfficersList = fstOfficersList;
    }
}
