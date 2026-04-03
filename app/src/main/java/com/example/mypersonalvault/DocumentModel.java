package com.example.mypersonalvault;

public class DocumentModel {

    String name;
    String url;

    public DocumentModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() { return name; }
    public String getUrl() { return url; }

    public void setName(String name) { this.name = name; }
}