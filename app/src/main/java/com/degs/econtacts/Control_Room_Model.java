package com.degs.econtacts;

import java.util.ArrayList;

public class Control_Room_Model {
    int id;
    String name_eng,name_hi,shift;
    ArrayList<Officer_Model> controlRoomsOfficers;

    public Control_Room_Model(int id, String name_eng, String name_hi, String shift, ArrayList<Officer_Model> controlRoomsOfficers) {
        this.id = id;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.shift = shift;
        this.controlRoomsOfficers = controlRoomsOfficers;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public ArrayList<Officer_Model> getControlRoomsOfficers() {
        return controlRoomsOfficers;
    }

    public void setControlRoomsOfficers(ArrayList<Officer_Model> controlRoomsOfficers) {
        this.controlRoomsOfficers = controlRoomsOfficers;
    }
}
