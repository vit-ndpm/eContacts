package com.degs.econtacts;

import java.util.ArrayList;

public class Nodal_Model {
    int id;
    String name_eng,name_hi,jobs;
    ArrayList<Officer_Model>officerModelArrayList;

    public Nodal_Model(int id, String name_eng, String name_hi, String jobs, ArrayList<Officer_Model> officerModelArrayList) {
        this.id = id;
        this.name_eng = name_eng;
        this.name_hi = name_hi;
        this.jobs = jobs;
        this.officerModelArrayList = officerModelArrayList;
    }
}
