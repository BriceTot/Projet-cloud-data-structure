package com.example.webtodolist;

public class ToDo {
    int id;
    String description;

    public ToDo(String description){
        this.description=description;
    }

    public ToDo(int id, String description){
        this.id=id;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
