package com.example.lifestyle.Models;

public class ArticleModel {
     int Id;
     int Image;
     String Header;
     String Description;
     String Type;

    public ArticleModel(int id, int image,String header, String description, String type) {
        Id = id;
        Header = header;
        Description = description;
        Image = image;
        Type = type;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public int getImage() {
        return Image;
    }
    public void setImage(int image) {
        Image = image;
    }

    public String getHeader() {
        return Header;
    }
    public void setHeader(String header) {
        Header = header;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }

}


