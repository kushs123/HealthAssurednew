package com.example.healthassured;

public class mDoctorslist {
    private String Name;
    private String Desc;
    private String Exp;
    private String Specialization;
    private String Image;

    public mDoctorslist(String toString, String toString1, String toString2, String toString3, String url) {

    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setExp(String exp) {
        Exp = exp;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getName() {
        return Name;
    }

    public String getDesc() {
        return Desc;
    }

    public String getExp() {
        return Exp;
    }

    public String getSpecialization() {
        return Specialization;
    }
    public mDoctorslist(){

    }
}
