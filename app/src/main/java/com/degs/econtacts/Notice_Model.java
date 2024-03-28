package com.degs.econtacts;

public class Notice_Model {
    int id,category_id;
    String title,description,category_name;

    public Notice_Model(int id, int category_id, String title, String description, String category_name) {
        this.id = id;
        this.category_id = category_id;
        this.title = title;
        this.description = description;
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
