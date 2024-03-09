package com.degs.econtacts;

public class HomeItemModel {
    int id;
    public String item_title;
    public int item_image;

    public HomeItemModel(int id, String item_title, int item_image) {
        this.id = id;
        this.item_title = item_title;
        this.item_image = item_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }
}
